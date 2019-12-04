package com.example.renat.wttconce.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renat.wttconce.R;
import com.example.renat.wttconce.dbs.ControllerClientePedido;
import com.example.renat.wttconce.model.Cliente;
import com.example.renat.wttconce.model.ClientePedido;
import com.example.renat.wttconce.model.Pedido;

public class AlterarClientePedidoActivity extends AppCompatActivity {

    EditText editTextIdCliPed, editTextIdCli, editTextIdPed;
    Button btnBuscarIdCliPed, btnAlterar;
    ClientePedido cliPed;
    ControllerClientePedido conCliPed;
    Cliente cli;
    Pedido ped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_cliente_pedido);

        editTextIdCliPed = (EditText) findViewById(R.id.editTextCliPedAltIdCliPed);
        editTextIdCli = (EditText) findViewById(R.id.editTextCliPedAltIdCli);
        editTextIdPed = (EditText) findViewById(R.id.editTextCliPedAltIdPed);

        btnBuscarIdCliPed = (Button) findViewById(R.id.btnCliPedAltBusc);
        btnAlterar = (Button) findViewById(R.id.btnCliPedAltAlterar);

        btnBuscarIdCliPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cliPed = new ClientePedido();
                conCliPed = new ControllerClientePedido(AlterarClientePedidoActivity.this);

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
                            editTextIdCli.setText(String.valueOf(cli.getIdPessoa()));
                            ped = new Pedido();
                            ped = cliPedRetorno.getIdPedido();
                            editTextIdPed.setText(String.valueOf(ped.getIdPedido()));
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

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!editTextIdCliPed.getText().toString().equals("") && editTextIdCliPed.getText().toString() != null) {
                    cliPed.setIdClientePedido(Long.parseLong(editTextIdCliPed.getText().toString()));
                    cli = new Cliente();
                    cli.setIdPessoa(Long.parseLong(editTextIdCli.getText().toString()));
                    cliPed.setIdCliente(cli);
                    ped = new Pedido();
                    ped.setIdPedido(Long.parseLong(editTextIdPed.getText().toString()));
                    cliPed.setIdPedido(ped);
                } else {
                    cliPed = null;
                }

                cliPed = new ClientePedido(cliPed.getIdClientePedido(), cli.getIdPessoa(), ped.getIdPedido());
                conCliPed = new ControllerClientePedido(AlterarClientePedidoActivity.this);

                int ver = 0;
                ver = conCliPed.alterar(cliPed);

                if (ver == -1) {
                    Toast.makeText(getApplicationContext(), "Erro ao alterar cliente pedido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Cliente Pedido alterado com sucesso! = " + cliPed.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
