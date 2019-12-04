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
import com.example.renat.wttconce.model.Cliente;


public class DeletarClienteActivity extends AppCompatActivity {

    EditText editTextId;
    TextView textViewNome, textViewCpf;
    Button btnDeletar, btnBuscar;
    Cliente cli;
    ControllerCliente conCli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar_cliente);

        editTextId = (EditText) findViewById(R.id.editTextCliDelIdCliente);
        textViewNome = (TextView) findViewById(R.id.textViewCliDelNomeBus);
        textViewCpf = (TextView) findViewById(R.id.textViewCliDelCpfBus);

        btnBuscar = (Button) findViewById(R.id.btnCliDelBuscar);
        btnDeletar = (Button) findViewById(R.id.btnCliDelDeletar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cli = new Cliente();
                conCli = new ControllerCliente(DeletarClienteActivity.this);

                if (editTextId != null && !editTextId.getText().toString().equals("")) {
                    String idCpf = editTextId.getText().toString();
                    try {
                        cli.setIdPessoa(Long.parseLong(idCpf));
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Busca CPF!", Toast.LENGTH_SHORT).show();
                    }
                    cli.setCpf(idCpf);

                    Cliente cliRetorno = conCli.buscarCliEsp(cli);

                    if (cliRetorno != null) {
                        Toast.makeText(getApplicationContext(), "Sucesso ao buscar! " + cliRetorno.getNome(), Toast.LENGTH_SHORT).show();
                        editTextId.setText(String.valueOf(cliRetorno.getIdPessoa()));
                        textViewNome.setText(cliRetorno.getNome());
                        textViewCpf.setText(cliRetorno.getCpf());
                        cli = cliRetorno;

                    } else {
                        Toast.makeText(getApplicationContext(), "Erro ao buscar!", Toast.LENGTH_SHORT).show();
                        cli = null;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao buscar!", Toast.LENGTH_SHORT).show();
                    cli = null;
                }

            }
        });


        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conCli = new ControllerCliente(DeletarClienteActivity.this);

                if (cli != null) {
                    int ver = 0;
                    ver = conCli.deletar(cli);

                    if (ver == -1) {
                        Toast.makeText(getApplicationContext(), "Erro ao deletar cliente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Cliente deletado com sucesso! ID = " + cli.getIdPessoa() + " Nome = " + cli.getNome(), Toast.LENGTH_SHORT).show();
                    }
                }else{

                    Toast.makeText(getApplicationContext(), "Erro ao deletar cliente", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }
}
