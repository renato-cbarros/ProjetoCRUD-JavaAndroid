package com.example.renat.wttconce.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.renat.wttconce.R;
import com.example.renat.wttconce.dbs.ControllerCliente;
import com.example.renat.wttconce.model.Cliente;

public class MainLoginActivity extends AppCompatActivity {

    EditText editTextLogin, editTextSenha;
    Button btnLogar, btnCadatrar;
    Cliente cli;
    ControllerCliente conCli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        editTextSenha = (EditText) findViewById(R.id.editTextLoginSenha);

        btnLogar = (Button) findViewById(R.id.btnLogar);
        btnCadatrar = (Button) findViewById(R.id.btnNaoTemCadastro);

        btnCadatrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainLoginActivity.this, CadastroClienteActivity.class);
                startActivity(intent);
            }
        });

        btnLogar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                cli = new Cliente();
                Cliente cliSai = new Cliente();
                conCli = new ControllerCliente(MainLoginActivity.this);

                if (!editTextLogin.getText().toString().equals("") && editTextLogin != null && !editTextSenha.getText().toString().equals("") && editTextSenha != null ) {
                    cli.setLogin(editTextLogin.getText().toString());
                    cli.setSenha(editTextSenha.getText().toString());

                    cliSai = conCli.validaLogin(cli);

                }else{
                    cliSai = null;
                    Toast.makeText(getApplicationContext(), "Erro no Login!", Toast.LENGTH_SHORT).show();
                }

                if (cliSai != null){
                    Toast.makeText(getApplicationContext(), "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainLoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Erro no Login!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
