package com.example.renat.wttconce.dbs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.renat.wttconce.model.Cliente;
import com.example.renat.wttconce.model.Pedido;

import java.util.ArrayList;

public class ControllerPedido {

    private static BancoHelper dbHelper = null;
    private static SQLiteDatabase db = null;
    private static String TABELA = "pedido";

    public ControllerPedido(Context context) {
        if (dbHelper == null ) {
            dbHelper = new BancoHelper(context);
        }
    }

    //inseri pedido
    public int inserir(Pedido ped) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores;
        long resultado;

        valores = new ContentValues();
        valores.put("dt_compra", ped.getDtCompra());
        valores.put("valor", ped.getValor());
        valores.put("observacao", ped.getObservacao());
        valores.put("status", ped.getStatus());
        valores.put("modelo_carro", ped.getModeloCarro());
        resultado = db.insert(TABELA, null, valores);
        db.close();

        int ver = 0;
        if (resultado == -1) {
            ver = -1;
        } else {
            ver = 0;
        }
        return ver;
    }

    //alterar cliente
    public int alterar(Pedido ped){
        db = dbHelper.getWritableDatabase();
        ContentValues valores;
        long resultado;

        String where = "id_pedido = " + ped.getIdPedido();
        valores = new ContentValues();
        valores.put("dt_compra", ped.getDtCompra());
        valores.put("valor", ped.getValor());
        valores.put("observacao", ped.getObservacao());
        valores.put("status", ped.getStatus());
        valores.put("modelo_carro", ped.getModeloCarro());
        resultado = db.update(TABELA, valores, where, null);
        db.close();

        int ver = 0;
        if (resultado == -1) {
            ver = -1;
        } else {
            ver = 0;
        }
        return ver;
    }

    //deletar cliente
    public int deletar(Pedido ped){
        db = dbHelper.getWritableDatabase();
        long resultado;

        String where = "id_pedido = " + ped.getIdPedido();
        db = dbHelper.getReadableDatabase();
        resultado = db.delete(TABELA, where, null);
        db.close();

        int ver = 0;
        if (resultado == -1) {
            ver = -1;
        } else {
            ver = 0;
        }
        return ver;
    }

    //busca cliente especifico
    public Pedido buscarPedEsp(Pedido ped){
        // Cria um SQLiteDatabase para leitura
        db = dbHelper.getWritableDatabase();


        StringBuilder select = new StringBuilder();

        if (ped.getIdPedido() == null) {
            select.append("SELECT * FROM pedido WHERE observacao = '" + ped.getObservacao() + "'");
        }else{
            select.append("SELECT * FROM pedido WHERE id_pedido = " + ped.getIdPedido());
        }


        // Executa a Query e armazena os dados retornados em um Cursor
        Cursor dados = db.rawQuery(select.toString(), null);

        // Verifica se retornou ao menos um registro
        if (dados.getCount() != 0) {
            // Instancia um Objeto do Tipo Usuario para armazenar os dados
            ped = new Pedido();

            // Move o cursor para o próximo registro
            dados.moveToNext();

            // Preenche o Objeto usuario com os dados do cursor
            ped.setIdPedido(dados.getLong(0));
            ped.setDtCompra(dados.getString(1));
            ped.setValor(dados.getString(2));
            ped.setObservacao(dados.getString(3));
            ped.setStatus(dados.getString(4));
            ped.setModeloCarro(dados.getString(5));
        }else {
            ped = null;
        }
        // Retorna o usuário que contém os dados do ultimo registro inserido no banco
        dados.close();
        db.close();
        return ped;

    }

    //busca ultimo cliente inserido
    public Pedido getUltimoPedido(Context context) {
        // Cria um SQLiteDatabase para leitura
        db = dbHelper.getWritableDatabase();

        StringBuilder select = new StringBuilder();
        Pedido ped = new Pedido();

        // Query para buscar o ultimo registro inserido no Banco de Dados
        select.append("SELECT * FROM pedido WHERE id_pedido = (SELECT MAX(id_pedido) FROM pedido)");

        // Executa a Query e armazena os dados retornados em um Cursor
        Cursor dados = db.rawQuery(select.toString(), null);

        // Verifica se retornou ao menos um registro
        if (dados.getCount() != 0) {
            // Instancia um Objeto do Tipo Usuario para armazenar os dados
            ped = new Pedido();

            // Move o cursor para o próximo registro
            dados.moveToNext();

            // Preenche o Objeto usuario com os dados do cursor
            ped.setIdPedido(dados.getLong(0));
            ped.setDtCompra(dados.getString(1));
            ped.setValor(dados.getString(2));
            ped.setObservacao(dados.getString(3));
            ped.setStatus(dados.getString(4));
            ped.setModeloCarro(dados.getString(5));
        }
        // Retorna o usuário que contém os dados do ultimo registro inserido no banco
        dados.close();
        db.close();
        return ped;
    }

    //lista pedido
    public ArrayList<Pedido> getLista(){
        String[] columns = {"id_pedido", "dt_compra", "valor", "observacao", "status", "modelo_carro"};
        Cursor cursor = dbHelper.getWritableDatabase().query(TABELA, columns, null, null, null, null, null, null);
        ArrayList<Pedido> pedidos = new ArrayList<>();

        while (cursor.moveToNext()) {
            Pedido ped = new Pedido();
            ped.setIdPedido(cursor.getLong(0));
            ped.setDtCompra(cursor.getString(1));
            ped.setValor(cursor.getString(2));
            ped.setObservacao(cursor.getString(3));
            ped.setStatus(cursor.getString(4));
            ped.setModeloCarro(cursor.getString(5));
            pedidos.add(ped);
        }

        return pedidos;
    }
}
