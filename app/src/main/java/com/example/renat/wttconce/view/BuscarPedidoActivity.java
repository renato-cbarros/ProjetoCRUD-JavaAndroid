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
import com.example.renat.wttconce.dbs.ControllerPedido;
import com.example.renat.wttconce.model.Cliente;
import com.example.renat.wttconce.model.Pedido;

public class BuscarPedidoActivity extends AppCompatActivity {

    EditText editTextIdObser;
    TextView textViewDt, textViewValor, textViewObserv;
    Button btnBuscar;
    Pedido ped;
    ControllerPedido conPed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_pedido);

        editTextIdObser = (EditText) findViewById(R.id.editTextPedBuscIdObser);

        btnBuscar = (Button) findViewById(R.id.btnPedBuscBuscar);

        textViewDt = (TextView) findViewById(R.id.textViewPedBuscDtBusc);
        textViewValor = (TextView) findViewById(R.id.textViewPedBuscValorBusc);
        textViewObserv = (TextView) findViewById(R.id.textViewPedBuscObserBusc);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ped = new Pedido();
                conPed = new ControllerPedido(BuscarPedidoActivity.this);

                if (editTextIdObser != null && !editTextIdObser.getText().toString().equals("")) {
                    String idObs = editTextIdObser.getText().toString();
                    try {
                        ped.setIdPedido(Long.parseLong(idObs));
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Busca Observação!", Toast.LENGTH_SHORT).show();
                    }
                    ped.setObservacao(idObs);

                    Pedido pedRetorno = conPed.buscarPedEsp(ped);

                    if (pedRetorno != null) {
                        Toast.makeText(getApplicationContext(), "Sucesso ao buscar! " + pedRetorno.getModeloCarro(), Toast.LENGTH_SHORT).show();
                        textViewDt.setText(pedRetorno.getDtCompra());
                        textViewValor.setText(pedRetorno.getValor());
                        textViewObserv.setText(pedRetorno.getObservacao());
                    } else {
                        Toast.makeText(getApplicationContext(), "Erro ao buscar!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao buscar!", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}

