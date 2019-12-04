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


public class CadastroClienteActivity extends AppCompatActivity {
    EditText editTextNome, editTextCpf, editTextLogin, editTextSenha, editTextSituacao;
    Button btnCadastrar;
    Cliente cli;
    ControllerCliente conCli;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        editTextNome = (EditText) findViewById(R.id.editTextCliCadNome);
        editTextCpf = (EditText) findViewById(R.id.editTextCliCadCpf);
        editTextLogin = (EditText) findViewById(R.id.editTextCliCadLogin);
        editTextSenha = (EditText) findViewById(R.id.editTextCliCadSenha);
        editTextSituacao = (EditText) findViewById(R.id.editTextCliCadSituacao);

        btnCadastrar = (Button) findViewById(R.id.btnCliCadCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cli = new Cliente();
                conCli = new ControllerCliente(CadastroClienteActivity.this);

                cli.setNome(editTextNome.getText().toString());
                cli.setCpf(editTextCpf.getText().toString());
                cli.setLogin(editTextLogin.getText().toString());
                cli.setSenha(editTextSenha.getText().toString());
                cli.setSituacao(editTextSituacao.getText().toString());

                int ver = 0;
                ver = conCli.inserir(cli);

                if (ver == -1) {
                    Toast.makeText(getApplicationContext(), "Erro ao cadastrar cliente", Toast.LENGTH_SHORT).show();
                } else {
                    Cliente verificaCli = new Cliente();
                    verificaCli = conCli.getUltimoCliente(getApplicationContext());
                    if (verificaCli != null){
                        Toast.makeText(getApplicationContext(), "Cliente cadastrado com sucesso! ID = " + verificaCli.getIdPessoa() + " Nome = " + verificaCli.getNome(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }
}
