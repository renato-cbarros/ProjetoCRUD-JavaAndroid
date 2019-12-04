package com.example.renat.wttconce.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.renat.wttconce.R;

public class MenuClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);

        Button btnCadastroCli = (Button) findViewById(R.id.btnCliCad);
        btnCadastroCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuClienteActivity.this, CadastroClienteActivity.class);
                startActivity(intent);
            }
        });

        Button btnAlterarCli = (Button) findViewById(R.id.btnCliAlt);
        btnAlterarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuClienteActivity.this, AlterarClienteActivity.class);
                startActivity(intent);
            }
        });

        Button btnDeletarCli = (Button) findViewById(R.id.btnCliDel);
        btnDeletarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuClienteActivity.this, DeletarClienteActivity.class);
                startActivity(intent);
            }
        });

        Button btnListarCli = (Button) findViewById(R.id.btnCLiList);
        btnListarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuClienteActivity.this, ListarClienteActivity.class);
                startActivity(intent);
            }
        });

        Button btnBuscarCli = (Button) findViewById(R.id.btnCliBuscCliEsp);
        btnBuscarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuClienteActivity.this, BuscarClienteEspecificoActivity.class);
                startActivity(intent);
            }
        });

    }
}
