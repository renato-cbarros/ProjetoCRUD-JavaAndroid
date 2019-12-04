package com.example.renat.wttconce.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.renat.wttconce.R;
import com.example.renat.wttconce.dbs.ControllerCliente;
import com.example.renat.wttconce.dbs.ControllerPedido;
import com.example.renat.wttconce.model.Cliente;
import com.example.renat.wttconce.model.Pedido;

public class AlterarPedidoActivity extends AppCompatActivity {

    EditText editTextId, editTextDt, editTextValor, editTextObs, editTextStatus, editTextModelo;
    Button btnAlterar, btnBuscar;
    Pedido ped;
    ControllerPedido conPed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_pedido);

        editTextId = (EditText) findViewById(R.id.editTextPedAltId);
        editTextDt = (EditText) findViewById(R.id.editTextPedAltData);
        editTextValor = (EditText) findViewById(R.id.editTextPedAltValor);
        editTextObs = (EditText) findViewById(R.id.editTextPedAltObser);
        editTextStatus = (EditText) findViewById(R.id.editTextPedAltStatus);
        editTextModelo = (EditText) findViewById(R.id.editTextPedAltModelo);

        btnBuscar = (Button) findViewById(R.id.btnPedAltBuscar);
        btnAlterar = (Button) findViewById(R.id.btnPedAltAlterar);


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ped = new Pedido();
                conPed = new ControllerPedido(AlterarPedidoActivity.this);

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
                        editTextDt.setText(pedRetorno.getDtCompra());
                        editTextValor.setText(pedRetorno.getValor());
                        editTextObs.setText(pedRetorno.getObservacao());
                        editTextStatus.setText(pedRetorno.getStatus());
                        editTextModelo.setText(pedRetorno.getModeloCarro());

                    } else {
                        Toast.makeText(getApplicationContext(), "Erro ao buscar!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao buscar!", Toast.LENGTH_SHORT).show();
                }


            }
        });


        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ped = new Pedido();
                conPed = new ControllerPedido(AlterarPedidoActivity.this);

                ped.setIdPedido(Long.parseLong(editTextId.getText().toString()));
                ped.setDtCompra(editTextDt.getText().toString());
                ped.setValor(editTextValor.getText().toString());
                ped.setObservacao(editTextObs.getText().toString());
                ped.setStatus(editTextStatus.getText().toString());
                ped.setModeloCarro(editTextModelo.getText().toString());

                int ver = 0;
                ver = conPed.alterar(ped);

                if (ver == -1) {
                    Toast.makeText(getApplicationContext(), "Erro ao alterar pedido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Pedido alterado com sucesso! ID = " + ped.getIdPedido() + " Obsevação = " + ped.getObservacao(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}

