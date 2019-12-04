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

public class BuscarClientePedidoActivity extends AppCompatActivity {
    EditText editTextIdCliPed, editTextIdCli, editTextIdPed;
    Button btnBuscarIdCliPed, btnBuscarIdCli, btnBuscarIdPed;
    TextView textViewIDCliPed, textViewIdCli, textViewPed;
    ClientePedido cliPed;
    ControllerClientePedido conCliPed;
    Cliente cli;
    Pedido ped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_cliente_pedido);

        textViewIDCliPed = (TextView) findViewById(R.id.textViewCliPedBuscIdCliPedBusc);
        textViewIdCli = (TextView) findViewById(R.id.textViewCliPedBuscIdCliBusc);
        textViewPed = (TextView) findViewById(R.id.textViewCliPedBuscIdPedBusc);

        editTextIdCliPed = (EditText) findViewById(R.id.editTextCliPedBuscIDCLiPEd);
        editTextIdCli = (EditText) findViewById(R.id.editTextCliPedBuscIDCLi);
        editTextIdPed = (EditText) findViewById(R.id.editTextCliPedBuscIDPEd);

        btnBuscarIdCliPed = (Button) findViewById(R.id.btnCliPedBusIdCliPed);
        btnBuscarIdCli = (Button) findViewById(R.id.btnCliPedBusIdCli);
        btnBuscarIdPed = (Button) findViewById(R.id.btnCliPedBusIdPed);

        btnBuscarIdCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cliPed = new ClientePedido();
                conCliPed = new ControllerClientePedido(BuscarClientePedidoActivity.this);

                if (editTextIdCli != null && !editTextIdCli.getText().toString().equals("")) {
                    String idCpf = editTextIdCli.getText().toString();
                    try {
                        cli = new Cliente();
                        cli.setIdPessoa(Long.parseLong(idCpf));
                        cliPed.setIdCliente(cli);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Erro " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    ClientePedido cliPedRetorno = conCliPed.getClientePedidoIdCliente(cliPed);

                    if (cliPedRetorno != null) {
                        try {
                            textViewIDCliPed.setText(String.valueOf(cliPedRetorno.getIdClientePedido()));
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

        btnBuscarIdPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cliPed = new ClientePedido();
                conCliPed = new ControllerClientePedido(BuscarClientePedidoActivity.this);

                if (editTextIdPed != null && !editTextIdPed.getText().toString().equals("")) {
                    String idCpf = editTextIdPed.getText().toString();
                    try {
                        ped = new Pedido();
                        ped.setIdPedido(Long.parseLong(idCpf));
                        cliPed.setIdPedido(ped);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Erro " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    ClientePedido cliPedRetorno = conCliPed.getClientePedidoIdPedido(cliPed);

                    if (cliPedRetorno != null) {
                        try {
                            textViewIDCliPed.setText(String.valueOf(cliPedRetorno.getIdClientePedido()));
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

        btnBuscarIdCliPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cliPed = new ClientePedido();
                conCliPed = new ControllerClientePedido(BuscarClientePedidoActivity.this);

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
                            textViewIDCliPed.setText(String.valueOf(cliPedRetorno.getIdClientePedido()));
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

    }
}
