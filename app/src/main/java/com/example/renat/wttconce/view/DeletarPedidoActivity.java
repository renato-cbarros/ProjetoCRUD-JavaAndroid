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
import com.example.renat.wttconce.model.Pedido;

public class DeletarPedidoActivity extends AppCompatActivity {

    EditText editTextId;
    TextView textViewDt, textViewValor, textViewObser;
    Button btnDeletar, btnBuscar;
    Pedido ped;
    ControllerPedido conPed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar_pedido);

        editTextId = (EditText) findViewById(R.id.editTextPedDelId);

        textViewDt = (TextView) findViewById(R.id.textViewPedDelDtBusc);
        textViewValor = (TextView) findViewById(R.id.textViewPedDelValorBusc);
        textViewObser = (TextView) findViewById(R.id.textViewPedDelObserBusc);


        btnBuscar = (Button) findViewById(R.id.btnPedDelBuscar);
        btnDeletar = (Button) findViewById(R.id.btnPedDelDeletar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ped = new Pedido();
                conPed = new ControllerPedido(DeletarPedidoActivity.this);

                if (editTextId != null && !editTextId.getText().toString().equals("")) {
                    String idObs = editTextId.getText().toString();
                    try {
                        ped.setIdPedido(Long.parseLong(idObs));
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Busca Observação!", Toast.LENGTH_SHORT).show();
                    }
                    ped.setObservacao(idObs);

                    Pedido pedRetorno = conPed.buscarPedEsp(ped);

                    if (pedRetorno != null) {
                        Toast.makeText(getApplicationContext(), "Sucesso ao buscar! " + pedRetorno.getModeloCarro(), Toast.LENGTH_SHORT).show();
                        editTextId.setText(String.valueOf(pedRetorno.getIdPedido()));
                        textViewDt.setText(pedRetorno.getDtCompra());
                        textViewValor.setText(pedRetorno.getValor());
                        textViewObser.setText(pedRetorno.getObservacao());

                    } else {
                        Toast.makeText(getApplicationContext(), "Erro ao buscar!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao buscar!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                conPed = new ControllerPedido(DeletarPedidoActivity.this);

                if (ped != null) {
                    int ver = 0;
                    ver = conPed.deletar(ped);

                    if (ver == -1) {
                        Toast.makeText(getApplicationContext(), "Erro ao deletar pedido", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Pedido deletado com sucesso! ID = " + ped.getIdPedido() + " Observação = " + ped.getObservacao(), Toast.LENGTH_SHORT).show();
                    }
                }else{

                    Toast.makeText(getApplicationContext(), "Erro ao deletar pedido", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
}
