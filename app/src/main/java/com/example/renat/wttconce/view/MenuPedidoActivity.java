package com.example.renat.wttconce.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.renat.wttconce.R;

public class MenuPedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pedido);


        Button btnCadastroPed = (Button) findViewById(R.id.btnPedCad);
        btnCadastroPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPedidoActivity.this, CadastrarPedidoActivity.class);
                startActivity(intent);
            }
        });

        Button btnAlterarPed = (Button) findViewById(R.id.btnPedAlt);
        btnAlterarPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPedidoActivity.this, AlterarPedidoActivity.class);
                startActivity(intent);
            }
        });

        Button btnDeletarPed = (Button) findViewById(R.id.btnPedDel);
        btnDeletarPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPedidoActivity.this, DeletarPedidoActivity.class);
                startActivity(intent);
            }
        });

        Button btnListarPed = (Button) findViewById(R.id.btnPedList);
        btnListarPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPedidoActivity.this, ListarPedidoActivity.class);
                startActivity(intent);
            }
        });

        Button btnBuscarPed = (Button) findViewById(R.id.btnPedBuscPed);
        btnBuscarPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPedidoActivity.this, BuscarPedidoActivity.class);
                startActivity(intent);
            }
        });


    }
}
