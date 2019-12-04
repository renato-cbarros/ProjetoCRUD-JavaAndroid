package com.example.renat.wttconce.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.renat.wttconce.R;
import com.example.renat.wttconce.dbs.ControllerCliente;
import com.example.renat.wttconce.dbs.ControllerPedido;
import com.example.renat.wttconce.model.Cliente;
import com.example.renat.wttconce.model.Pedido;

import java.util.ArrayList;

public class ListarPedidoActivity extends AppCompatActivity {

    ListView lista;
    ControllerPedido conPed;
    ArrayList<Pedido> pedArr;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pedido);

        lista = (ListView) findViewById(R.id.listViewPedList);

    }

    protected void onResume(){
        super.onResume();
        carregarPedidos();
    }

    public void carregarPedidos(){
        conPed = new ControllerPedido(ListarPedidoActivity.this);
        pedArr = conPed.getLista();

        if (pedArr != null){
            adapter = new ArrayAdapter<Pedido>(ListarPedidoActivity.this, android.R.layout.simple_list_item_1, pedArr);
            lista.setAdapter(adapter);
        }

    }
}
