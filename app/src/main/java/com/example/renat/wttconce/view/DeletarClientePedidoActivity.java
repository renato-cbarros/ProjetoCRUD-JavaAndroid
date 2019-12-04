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
import com.example.renat.wttconce.model.Cliente;
import com.example.renat.wttconce.model.ClientePedido;
import com.example.renat.wttconce.model.Pedido;

public class DeletarClientePedidoActivity extends AppCompatActivity {

    EditText editTextIdCliPed;
    TextView textViewIdCli, textViewPed;
    Button btnBuscarIdCliPed, btnDeletar;
    ClientePedido cliPed;
    ControllerClientePedido conCliPed;
    Cliente cli;
    Pedido ped;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar_cliente_pedido);

        editTextIdCliPed = (EditText) findViewById(R.id.editTextCliPedDelIdCliPed);

        textViewIdCli = (TextView) findViewById(R.id.textViewCliPedDelIDCliBusc);
        textViewPed = (TextView) findViewById(R.id.textViewCliPedDelIDPedBusc);

        btnBuscarIdCliPed = (Button) findViewById(R.id.btnCliePedDelBusc);
        btnDeletar = (Button) findViewById(R.id.btnCliPedDelDeletar);

        btnBuscarIdCliPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cliPed = new ClientePedido();
                conCliPed = new ControllerClientePedido(DeletarClientePedidoActivity.this);

                if (editTextIdCliPed != null && !editTextIdCliPed.getText().toString().equals("")) {
                    String idCpf = editTextIdCliPed.getText().toString();
                    try {
                        cliPed.setIdClientePedido(Long.parseLong(idCpf));
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Erro " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    ClientePedido cliPedRetorno = conCliPed.getClientePedidoIdClientePedido(cliPed);

                    if (cliPedRetorno != null) {

                        try {
                            editTextIdCliPed.setText(String.valueOf(cliPedRetorno.getIdClientePedido()));
                            cli = new Cliente();
                            cli = cliPedRetorno.getIdCliente();
                            textViewIdCli.setText(String.valueOf(cli.getIdPessoa()));
                            ped = new Pedido();
                            ped = cliPedRetorno.getIdPedido();
                            textViewPed.setText(String.valueOf(ped.getIdPedido()));
                            Toast.makeText(getApplicationContext(), "Sucesso ao buscar! ID = " + cliPedRetorno.getIdClientePedido(), Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Erro ao buscar!", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Erro ao buscar!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao buscar!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cliPed = new ClientePedido();
                conCliPed = new ControllerClientePedido(DeletarClientePedidoActivity.this);

                if (!editTextIdCliPed.getText().toString().equals("") && editTextIdCliPed.getText().toString() != null) {
                    cliPed.setIdClientePedido(Long.parseLong(editTextIdCliPed.getText().toString()));
                    cli = new Cliente();
                    cli.setIdPessoa(Long.parseLong(textViewIdCli.getText().toString()));
                    cliPed.setIdCliente(cli);
                    ped = new Pedido();
                    ped.setIdPedido(Long.parseLong(textViewPed.getText().toString()));
                    cliPed.setIdPedido(ped);
                }else{
                    cliPed = null;
                }


                if (cliPed != null) {
                    int ver = 0;
                    ver = conCliPed.deletar(cliPed);

                    if (ver == -1) {
                        Toast.makeText(getApplicationContext(), "Erro ao deletar cliente pedido", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Cliente pedido deletado com sucesso! ID = " + cliPed.getIdClientePedido(), Toast.LENGTH_SHORT).show();
                    }
                }else{

                    Toast.makeText(getApplicationContext(), "Erro ao deletar cliente pedido", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}
