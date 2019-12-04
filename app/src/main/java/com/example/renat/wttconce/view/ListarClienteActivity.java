package com.example.renat.wttconce.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.renat.wttconce.R;
import com.example.renat.wttconce.dbs.ControllerCliente;
import com.example.renat.wttconce.model.Cliente;

import java.util.ArrayList;


public class ListarClienteActivity extends AppCompatActivity {

    ListView lista;
    ControllerCliente conCli;
    ArrayList<Cliente> cliArr;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cliente);

        lista = (ListView) findViewById(R.id.listClilist);

    }

    protected void onResume(){
        super.onResume();
        carregarClientes();
    }

    public void carregarClientes(){
        conCli = new ControllerCliente(ListarClienteActivity.this);
        cliArr = conCli.getLista();

        if (cliArr != null){
            adapter = new ArrayAdapter<Cliente>(ListarClienteActivity.this, android.R.layout.simple_list_item_1, cliArr);
            lista.setAdapter(adapter);
        }
        //finish();

    }

}
