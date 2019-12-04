package com.example.renat.wttconce.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renat.wttconce.R;
import com.example.renat.wttconce.dbs.ControllerCliente;
import com.example.renat.wttconce.dbs.ControllerClientePedido;
import com.example.renat.wttconce.dbs.ControllerPedido;
import com.example.renat.wttconce.model.Cliente;
import com.example.renat.wttconce.model.ClientePedido;
import com.example.renat.wttconce.model.Pedido;

public class CadastrarClientePedidoActivity extends AppCompatActivity {
    EditText editTextIdCli, editTextIdPed;
    TextView textViewNome, textViewObs, textViewModelo;
    Button btnBuscarCli, btnBuscarPed, btnCadastrar;
    ClientePedido cliPed;
    ControllerClientePedido conCliPed;
    Cliente cli;
    ControllerCliente conCli;
    Pedido ped;
    ControllerPedido conPed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente_pedido);

        editTextIdCli = (EditText) findViewById(R.id.editTextCliPedCadIDCliente);
        editTextIdPed = (EditText) findViewById(R.id.editTextCliPedCadIDPedido);

        textViewNome = (TextView) findViewById(R.id.textViewCliPedCadNomeBusc);
        textViewObs = (TextView) findViewById(R.id.textViewCliPedCadObsBusc);
        textViewModelo = (TextView) findViewById(R.id.textViewCliPedCadModBusc);

        btnBuscarCli = (Button) findViewById(R.id.btnCliPedCadBuscIDClien);
        btnBuscarPed = (Button) findViewById(R.id.btnCliPedCadBuscIDPed);
        btnCadastrar = (Button) findViewById(R.id.btnCliPedCadCadastrar);

        btnBuscarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cli = new Cliente();
                conCli = new ControllerCliente(CadastrarClientePedidoActivity.this);

                if (editTextIdCli != null && !editTextIdCli.getText().toString().equals("")) {
                    String idCpf = editTextIdCli.getText().toString();
                    try {
                        cli.setIdPessoa(Long.parseLong(idCpf));
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Busca CPF!", Toast.LENGTH_SHORT).show();
                    }
                    cli.setCpf(idCpf);

                    Cliente cliRetorno = conCli.buscarCliEsp(cli);

                    if (cliRetorno != null) {
                        Toast.makeText(getApplicationContext(), "Sucesso ao buscar!" + cliRetorno.getNome(), Toast.LENGTH_SHORT).show();
                        textViewNome.setText(cliRetorno.getNome());
                    } else {
                        Toast.makeText(getApplicationContext(), "Erro ao buscar!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao buscar!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnBuscarPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ped = new Pedido();
                conPed = new ControllerPedido(CadastrarClientePedidoActivity.this);

                if (editTextIdPed != null && !editTextIdPed.getText().toString().equals("")) {
                    String idObs = editTextIdPed.getText().toString();
                    try {
                        ped.setIdPedido(Long.parseLong(idObs));
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Busca Observação!", Toast.LENGTH_SHORT).show();
                    }
                    ped.setObservacao(idObs);

                    Pedido pedRetorno = conPed.buscarPedEsp(ped);

                    if (pedRetorno != null) {
                        Toast.makeText(getApplicationContext(), "Sucesso ao buscar! " + pedRetorno.getModeloCarro(), Toast.LENGTH_SHORT).show();
                        textViewObs.setText(pedRetorno.getObservacao());
                        textViewModelo.setText(pedRetorno.getModeloCarro());
                    } else {
                        Toast.makeText(getApplicationContext(), "Erro ao buscar!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao buscar!", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cli.setIdPessoa(Long.parseLong(editTextIdCli.getText().toString()));
                ped.setIdPedido(Long.parseLong(editTextIdPed.getText().toString()));

                cliPed = new ClientePedido(null, cli.getIdPessoa(), ped.getIdPedido());
                conCliPed = new ControllerClientePedido(CadastrarClientePedidoActivity.this);

                int ver = 0;
                ver = conCliPed.inserir(cliPed);

                if (ver == -1) {
                    Toast.makeText(getApplicationContext(), "Erro ao cadastrar pedido", Toast.LENGTH_SHORT).show();
                } else {
                    ClientePedido verificaCliPed = new ClientePedido();
                    verificaCliPed = conCliPed.getUltimoClientePedido(getApplicationContext());
                    if (verificaCliPed != null){
                        Toast.makeText(getApplicationContext(), "Cliente Pedido cadastrado com sucesso! = " + verificaCliPed.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}
