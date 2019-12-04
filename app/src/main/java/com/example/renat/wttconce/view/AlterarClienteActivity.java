package com.example.renat.wttconce.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.renat.wttconce.R;
import com.example.renat.wttconce.dbs.ControllerCliente;
import com.example.renat.wttconce.model.Cliente;


public class AlterarClienteActivity extends AppCompatActivity {

    EditText editTextId, editTextNome, editTextCpf, editTextLogin, editTextSenha, editTextSituacao;
    Button btnAlterar, btnBuscar;
    Cliente cli;
    ControllerCliente conCli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_cliente);

        editTextId = (EditText) findViewById(R.id.editTextCliAltIdCliente);
        editTextNome = (EditText) findViewById(R.id.editTextCliAltNome);
        editTextCpf = (EditText) findViewById(R.id.editTextCliAltCpf);
        editTextLogin = (EditText) findViewById(R.id.editTextCliAltLogin);
        editTextSenha = (EditText) findViewById(R.id.editTextCliAltSenha);
        editTextSituacao = (EditText) findViewById(R.id.editTextCliAltSituacao);

        btnBuscar = (Button) findViewById(R.id.btnCliAltBuscar);
        btnAlterar = (Button) findViewById(R.id.btnCliAltAlterar);


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cli = new Cliente();
                conCli = new ControllerCliente(AlterarClienteActivity.this);

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
                        Toast.makeText(getApplicationContext(), "Sucesso ao buscar!" + cliRetorno.getNome(), Toast.LENGTH_SHORT).show();
                        editTextId.setText(String.valueOf(cliRetorno.getIdPessoa()));
                        editTextNome.setText(cliRetorno.getNome());
                        editTextCpf.setText(cliRetorno.getCpf());
                        editTextLogin.setText(cliRetorno.getLogin());
                        editTextSenha.setText(cliRetorno.getSenha());
                        editTextSituacao.setText(cliRetorno.getSituacao());
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
                cli = new Cliente();
                conCli = new ControllerCliente(AlterarClienteActivity.this);

                cli.setIdPessoa(Long.parseLong(editTextId.getText().toString()));
                cli.setNome(editTextNome.getText().toString());
                cli.setCpf(editTextCpf.getText().toString());
                cli.setLogin(editTextLogin.getText().toString());
                cli.setSenha(editTextSenha.getText().toString());
                cli.setSituacao(editTextSituacao.getText().toString());

                int ver = 0;
                ver = conCli.alterar(cli);

                if (ver == -1) {
                    Toast.makeText(getApplicationContext(), "Erro ao alterar cliente", Toast.LENGTH_SHORT).show();
                } else {
                        Toast.makeText(getApplicationContext(), "Cliente alterar com sucesso! ID = " + cli.getIdPessoa() + " Nome = " + cli.getNome(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
