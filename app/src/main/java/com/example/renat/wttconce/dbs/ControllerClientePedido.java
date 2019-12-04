package com.example.renat.wttconce.dbs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.renat.wttconce.model.Cliente;
import com.example.renat.wttconce.model.ClientePedido;
import com.example.renat.wttconce.model.Pedido;

import java.util.ArrayList;

public class ControllerClientePedido {

    private static BancoHelper dbHelper = null;
    private static SQLiteDatabase db = null;
    private static String TABELA = "cliente_pedido";

    public ControllerClientePedido(Context context) {
        if (dbHelper == null ) {
            dbHelper = new BancoHelper(context);
        }
    }

    //inseri cliente pedido
    public int inserir(ClientePedido cliPed) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores;
        long resultado;
        Cliente cliBus = new Cliente();
        Pedido pedBus = new Pedido();

        cliBus = cliPed.getIdCliente();
        pedBus = cliPed.getIdPedido();

        valores = new ContentValues();
        valores.put("id_pessoa", cliBus.getIdPessoa());
        valores.put("id_pedido", pedBus.getIdPedido());
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
    public int alterar(ClientePedido cliPed){
        db = dbHelper.getWritableDatabase();
        ContentValues valores;
        long resultado;
        Cliente cliBus = new Cliente();
        Pedido pedBus = new Pedido();

        cliBus = cliPed.getIdCliente();
        pedBus = cliPed.getIdPedido();

        String where = "id_cliente_pedido = " + cliPed.getIdClientePedido();
        valores = new ContentValues();
        valores.put("id_pessoa", cliBus.getIdPessoa());
        valores.put("id_pedido", pedBus.getIdPedido());
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
    public int deletar(ClientePedido cliPed){
        db = dbHelper.getWritableDatabase();
        long resultado;

        String where = "id_cliente_pedido = " + cliPed.getIdClientePedido();
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

    //busca cliente pedido id cliente pedido
    public ClientePedido getClientePedidoIdClientePedido(ClientePedido cliPed) {
        // Cria um SQLiteDatabase para leitura
        db = dbHelper.getWritableDatabase();

        StringBuilder select = new StringBuilder();

        // Query para buscar o ultimo registro inserido no Banco de Dados
        select.append("SELECT * FROM cliente_pedido WHERE id_cliente_pedido = " + cliPed.getIdClientePedido());

        // Executa a Query e armazena os dados retornados em um Cursor
        Cursor dados = db.rawQuery(select.toString(), null);

        // Verifica se retornou ao menos um registro
        if (dados.getCount() != 0) {
            // Instancia um Objeto do Tipo Usuario para armazenar os dados
            cliPed = new ClientePedido();
            Cliente cliBus = new Cliente();
            Pedido pedBus = new Pedido();

            // Move o cursor para o próximo registro
            dados.moveToNext();

            // Preenche o Objeto usuario com os dados do cursor
            cliPed.setIdClientePedido(dados.getLong(0));
            cliBus.setIdPessoa(dados.getLong(1));
            pedBus.setIdPedido(dados.getLong(2));
/*
            ControllerCliente cliCon = new ControllerCliente(null);
            cliBus = cliCon.buscarCliEsp(cliBus);

            ControllerPedido pedCon = new ControllerPedido(null);
            pedBus = pedCon.buscarPedEsp(pedBus);
            */

            cliPed.setIdCliente(cliBus);
            cliPed.setIdPedido(pedBus);
        }
        // Retorna o usuário que contém os dados do ultimo registro inserido no banco
        dados.close();
        db.close();
        return cliPed;
    }

    //busca cliente pedido id cliente
    public ClientePedido getClientePedidoIdCliente(ClientePedido cliPed) {
        // Cria um SQLiteDatabase para leitura
        db = dbHelper.getWritableDatabase();

        StringBuilder select = new StringBuilder();
        Cliente cli = new Cliente();

        cli = cliPed.getIdCliente();

        // Query para buscar o ultimo registro inserido no Banco de Dados
        select.append("SELECT * FROM cliente_pedido WHERE id_pessoa = " + cli.getIdPessoa());

        // Executa a Query e armazena os dados retornados em um Cursor
        Cursor dados = db.rawQuery(select.toString(), null);

        // Verifica se retornou ao menos um registro
        if (dados.getCount() != 0) {
            // Instancia um Objeto do Tipo Usuario para armazenar os dados
            cliPed = new ClientePedido();
            Cliente cliBus = new Cliente();
            Pedido pedBus = new Pedido();

            // Move o cursor para o próximo registro
            dados.moveToNext();

            // Preenche o Objeto usuario com os dados do cursor
            cliPed.setIdClientePedido(dados.getLong(0));
            cliBus.setIdPessoa(dados.getLong(1));
            pedBus.setIdPedido(dados.getLong(2));
/*
            ControllerCliente cliCon = new ControllerCliente(null);
            cliBus = cliCon.buscarCliEsp(cliBus);

            ControllerPedido pedCon = new ControllerPedido(null);
            pedBus = pedCon.buscarPedEsp(pedBus);
            */

            cliPed.setIdCliente(cliBus);
            cliPed.setIdPedido(pedBus);
        }
        // Retorna o usuário que contém os dados do ultimo registro inserido no banco
        dados.close();
        db.close();
        return cliPed;
    }

    //busca cliente pedido id pedido
    public ClientePedido getClientePedidoIdPedido(ClientePedido cliPed) {
        // Cria um SQLiteDatabase para leitura
        db = dbHelper.getWritableDatabase();

        StringBuilder select = new StringBuilder();
        Pedido ped = new Pedido();

        ped = cliPed.getIdPedido();

        // Query para buscar o ultimo registro inserido no Banco de Dados
        select.append("SELECT * FROM cliente_pedido WHERE id_pedido = " + ped.getIdPedido());

        // Executa a Query e armazena os dados retornados em um Cursor
        Cursor dados = db.rawQuery(select.toString(), null);

        // Verifica se retornou ao menos um registro
        if (dados.getCount() != 0) {
            // Instancia um Objeto do Tipo Usuario para armazenar os dados
            cliPed = new ClientePedido();
            Cliente cliBus = new Cliente();
            Pedido pedBus = new Pedido();

            // Move o cursor para o próximo registro
            dados.moveToNext();

            // Preenche o Objeto usuario com os dados do cursor
            cliPed.setIdClientePedido(dados.getLong(0));
            cliBus.setIdPessoa(dados.getLong(1));
            pedBus.setIdPedido(dados.getLong(2));
/*
            ControllerCliente cliCon = new ControllerCliente(null);
            cliBus = cliCon.buscarCliEsp(cliBus);

            ControllerPedido pedCon = new ControllerPedido(null);
            pedBus = pedCon.buscarPedEsp(pedBus);
            */

            cliPed.setIdCliente(cliBus);
            cliPed.setIdPedido(pedBus);
        }
        // Retorna o usuário que contém os dados do ultimo registro inserido no banco
        dados.close();
        db.close();
        return cliPed;
    }

    //busca ultimo cliente pedido inserido
    public ClientePedido getUltimoClientePedido(Context context) {
        // Cria um SQLiteDatabase para leitura
        db = dbHelper.getWritableDatabase();

        StringBuilder select = new StringBuilder();
        ClientePedido cliPed = new ClientePedido();

        // Query para buscar o ultimo registro inserido no Banco de Dados
        select.append("SELECT * FROM cliente_pedido WHERE id_cliente_pedido = (SELECT MAX(id_cliente_pedido) FROM cliente_pedido)");

        // Executa a Query e armazena os dados retornados em um Cursor
        Cursor dados = db.rawQuery(select.toString(), null);

        // Verifica se retornou ao menos um registro
        if (dados.getCount() != 0) {
            // Instancia um Objeto do Tipo Usuario para armazenar os dados
            cliPed = new ClientePedido();
            Cliente cliBus = new Cliente();
            Pedido pedBus = new Pedido();

            // Move o cursor para o próximo registro
            dados.moveToNext();

            // Preenche o Objeto usuario com os dados do cursor
            cliPed.setIdClientePedido(dados.getLong(0));
            cliBus.setIdPessoa(dados.getLong(1));
            pedBus.setIdPedido(dados.getLong(2));
/*
            ControllerCliente cliCon = new ControllerCliente(null);
            cliBus = cliCon.buscarCliEsp(cliBus);

            ControllerPedido pedCon = new ControllerPedido(null);
            pedBus = pedCon.buscarPedEsp(pedBus);
            */

            cliPed.setIdCliente(cliBus);
            cliPed.setIdPedido(pedBus);
        }
        // Retorna o usuário que contém os dados do ultimo registro inserido no banco
        dados.close();
        db.close();
        return cliPed;
    }

    //lista cliente
    public ArrayList<ClientePedido> getLista(){
        String[] columns = {"id_cliente_pedido", "id_pessoa", "id_pedido"};
        Cursor cursor = dbHelper.getWritableDatabase().query(TABELA, columns, null, null, null, null, null, null);
        ArrayList<ClientePedido> clientesPedidos = new ArrayList<>();

        while (cursor.moveToNext()) {
            ClientePedido cliPed = new ClientePedido();
            Cliente cliBus = new Cliente();
            Pedido pedBus = new Pedido();

            cliPed.setIdClientePedido(cursor.getLong(0));
            cliBus.setIdPessoa(cursor.getLong(1));
            pedBus.setIdPedido(cursor.getLong(2));

            /*
            ControllerCliente cliCon = new ControllerCliente(null);
            cliBus = cliCon.buscarCliEsp(cliBus);
            */

            /*
           ControllerPedido pedCon = new ControllerPedido(null);
           pedBus = pedCon.buscarPedEsp(pedBus);
           */

            cliPed.setIdCliente(cliBus);
            cliPed.setIdPedido(pedBus);

            clientesPedidos.add(cliPed);
        }

        cursor.close();

        return clientesPedidos;
    }



}
