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

public class CadastrarPedidoActivity extends AppCompatActivity {
    EditText editTextDt, editTextValor, editTextObs, editTextStatus, editTextModelo;
    Button btnCadastrar;
    Pedido ped;
    ControllerPedido conPed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pedido);


        editTextDt = (EditText) findViewById(R.id.editTextPedCadDt);
        editTextValor = (EditText) findViewById(R.id.editTextPedCadValor);
        editTextObs = (EditText) findViewById(R.id.editTextPedCadObser);
        editTextStatus = (EditText) findViewById(R.id.editTextPedCadStatus);
        editTextModelo = (EditText) findViewById(R.id.editTextPedCadModeloCArro);

        btnCadastrar = (Button) findViewById(R.id.btnPedCadCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ped = new Pedido();
                conPed = new ControllerPedido(CadastrarPedidoActivity.this);

                ped.setDtCompra(editTextDt.getText().toString());
                ped.setValor(editTextValor.getText().toString());
                ped.setObservacao(editTextObs.getText().toString());
                ped.setStatus(editTextStatus.getText().toString());
                ped.setModeloCarro(editTextModelo.getText().toString());

                int ver = 0;
                ver = conPed.inserir(ped);

                if (ver == -1) {
                    Toast.makeText(getApplicationContext(), "Erro ao cadastrar pedido", Toast.LENGTH_SHORT).show();
                } else {
                    Pedido verificaPed = new Pedido();
                    verificaPed = conPed.getUltimoPedido(getApplicationContext());
                    if (verificaPed != null){
                        Toast.makeText(getApplicationContext(), "Pedido cadastrado com sucesso! ID = " + verificaPed.getIdPedido() + " Status = " + verificaPed.getStatus(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}
