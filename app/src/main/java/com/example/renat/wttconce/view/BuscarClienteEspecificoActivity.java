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
import com.example.renat.wttconce.model.Cliente;

public class BuscarClienteEspecificoActivity extends AppCompatActivity {
    EditText editTextCpfId;
    TextView textViewNome, textViewId, textViewCpf;
    Button btnBuscar;
    Cliente cli;
    ControllerCliente conCli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_cliente_especifico);

        editTextCpfId = (EditText) findViewById(R.id.editTextCliBusEspIdCpf);

        btnBuscar = (Button) findViewById(R.id.btnCliBuscEspBuscar);

        textViewNome = (TextView) findViewById(R.id.textViewCliBusEspNomeBus);
        textViewId = (TextView) findViewById(R.id.textViewCliBusEspIdBusc);
        textViewCpf = (TextView) findViewById(R.id.textViewCliBusEspCpfBusc);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cli = new Cliente();
                conCli = new ControllerCliente(BuscarClienteEspecificoActivity.this);

                if (editTextCpfId != null && !editTextCpfId.getText().toString().equals("")) {
                    String idCpf = editTextCpfId.getText().toString();
                    try {
                        cli.setIdPessoa(Long.parseLong(idCpf));
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Busca CPF!", Toast.LENGTH_SHORT).show();
                    }
                    cli.setCpf(idCpf);

                    Cliente cliRetorno = conCli.buscarCliEsp(cli);

                    if (cliRetorno != null) {
                        Toast.makeText(getApplicationContext(), "Sucesso ao buscar!" + cliRetorno.getNome(), Toast.LENGTH_SHORT).show();
                        textViewNome.setText(cliRetorno.getNome());
                        textViewId.setText(String.valueOf(cliRetorno.getIdPessoa()));
                        textViewCpf.setText(cliRetorno.getCpf());
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
