package com.example.renat.wttconce.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.renat.wttconce.R;

public class MenuClientePedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente_pedido);

        Button btnCadastroCliPed = (Button) findViewById(R.id.btnCliPedMenuCad);
        btnCadastroCliPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuClientePedidoActivity.this, CadastrarClientePedidoActivity.class);
                startActivity(intent);
            }
        });

        Button btnAlterarCliPed = (Button) findViewById(R.id.btnCliPedMenuAlt);
        btnAlterarCliPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuClientePedidoActivity.this, AlterarClientePedidoActivity.class);
                startActivity(intent);
            }
        });

        Button btnBuscarCliPed = (Button) findViewById(R.id.btnCliPedMenuBus);
        btnBuscarCliPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuClientePedidoActivity.this, BuscarClientePedidoActivity.class);
                startActivity(intent);
            }
        });

        Button btnDeletarCliPed = (Button) findViewById(R.id.btnCliPedMenuDel);
        btnDeletarCliPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuClientePedidoActivity.this, DeletarClientePedidoActivity.class);
                startActivity(intent);
            }
        });

        Button btnListarCliPed = (Button) findViewById(R.id.btnCliPedMenuList);
        btnListarCliPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuClientePedidoActivity.this, ListarClientePedidoActivity.class);
                startActivity(intent);
            }
        });


    }
}
