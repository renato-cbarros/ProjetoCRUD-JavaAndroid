package com.example.renat.wttconce.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.renat.wttconce.R;
import com.example.renat.wttconce.dbs.ControllerCliente;
import com.example.renat.wttconce.dbs.ControllerClientePedido;
import com.example.renat.wttconce.model.Cliente;
import com.example.renat.wttconce.model.ClientePedido;

import java.util.ArrayList;

public class ListarClientePedidoActivity extends AppCompatActivity {
    ListView lista;
    ControllerClientePedido conCliPed;
    ArrayList<ClientePedido> cliPedArr;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cliente_pedido);

        lista = (ListView) findViewById(R.id.listViewCliPedList);

    }

    protected void onResume(){
        super.onResume();
        carregarClientesPedidos();
    }

    public void carregarClientesPedidos(){
        conCliPed = new ControllerClientePedido(ListarClientePedidoActivity.this);
        cliPedArr = conCliPed.getLista();

        if (cliPedArr != null){
            adapter = new ArrayAdapter<ClientePedido>(ListarClientePedidoActivity.this, android.R.layout.simple_list_item_1, cliPedArr);
            lista.setAdapter(adapter);
        }
        //finish();

    }

}

