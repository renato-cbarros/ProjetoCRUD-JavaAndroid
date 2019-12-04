package com.example.renat.wttconce.dbs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.renat.wttconce.model.Cliente;

import java.sql.DriverManager;
import java.util.ArrayList;

public class ControllerCliente {

    private static BancoHelper dbHelper = null;
    private static SQLiteDatabase db = null;
    private static String TABELA = "cliente";

    public ControllerCliente(Context context) {
        if (dbHelper == null) {
            dbHelper = new BancoHelper(context);
        }
    }

    //valida login cliente
    public Cliente validaLogin(Cliente cliEnt) {

        Cliente cli = new Cliente();
        // Cria um SQLiteDatabase para leitura
        db = dbHelper.getWritableDatabase();

        StringBuilder select = new StringBuilder();

        select.append("SELECT * FROM cliente WHERE login = '" + cliEnt.getLogin() + "' AND senha = '" + cliEnt.getSenha() + "'");

        Cursor dados = db.rawQuery(select.toString(), null);

        // Verifica se retornou ao menos um registro
        if (dados.getCount() != 0) {
            // Instancia um Objeto do Tipo Usuario para armazenar os dados
            cli = new Cliente();

            // Move o cursor para o próximo registro
            dados.moveToNext();

            // Preenche o Objeto usuario com os dados do cursor
            cli.setIdPessoa(dados.getLong(0));
            cli.setNome(dados.getString(1));
            cli.setCpf(dados.getString(2));
            cli.setLogin(dados.getString(3));
            cli.setSenha(dados.getString(4));
            cli.setSituacao(dados.getString(5));
        } else {
            cli = null;
        }
        // Retorna o usuário que contém os dados do ultimo registro inserido no banco
        dados.close();
        db.close();
        return cli;

    }

    //inseri cliente
    public int inserir(Cliente cli) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores;
        long resultado;
        String retorno;
        valores = new ContentValues();
        valores.put("nome", cli.getNome());
        valores.put("CPF", cli.getCpf());
        valores.put("login", cli.getLogin());
        valores.put("senha", cli.getSenha());
        valores.put("situacao", cli.getSituacao());
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
    public int alterar(Cliente cli) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores;
        long resultado;

        String where = "id_pessoa = " + cli.getIdPessoa();
        valores = new ContentValues();
        valores.put("nome", cli.getNome());
        valores.put("CPF", cli.getCpf());
        valores.put("login", cli.getLogin());
        valores.put("senha", cli.getSenha());
        valores.put("situacao", cli.getSituacao());
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
    public int deletar(Cliente cli) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores;
        long resultado;

        String where = "id_pessoa = " + cli.getIdPessoa();
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
    public Cliente buscarCliEsp(Cliente cli) {
        // Cria um SQLiteDatabase para leitura
        db = dbHelper.getWritableDatabase();


        StringBuilder select = new StringBuilder();

        if (cli.getIdPessoa() == null) {
            select.append("SELECT * FROM cliente WHERE CPF = '" + cli.getCpf() + "'");
        } else {
            select.append("SELECT * FROM cliente WHERE id_pessoa = " + cli.getIdPessoa());
        }


        // Executa a Query e armazena os dados retornados em um Cursor
        Cursor dados = db.rawQuery(select.toString(), null);

        // Verifica se retornou ao menos um registro
        if (dados.getCount() != 0) {
            // Instancia um Objeto do Tipo Usuario para armazenar os dados
            cli = new Cliente();

            // Move o cursor para o próximo registro
            dados.moveToNext();

            // Preenche o Objeto usuario com os dados do cursor
            cli.setIdPessoa(dados.getLong(0));
            cli.setNome(dados.getString(1));
            cli.setCpf(dados.getString(2));
            cli.setLogin(dados.getString(3));
            cli.setSenha(dados.getString(4));
            cli.setSituacao(dados.getString(5));
        } else {
            cli = null;
        }
        // Retorna o usuário que contém os dados do ultimo registro inserido no banco
        dados.close();
        db.close();
        return cli;

    }

    //busca ultimo cliente inserido
    public Cliente getUltimoCliente(Context context) {
        // Cria um SQLiteDatabase para leitura
        db = dbHelper.getWritableDatabase();

        StringBuilder select = new StringBuilder();
        Cliente cli = new Cliente();

        // Query para buscar o ultimo registro inserido no Banco de Dados
        select.append("SELECT * FROM cliente WHERE id_pessoa = (SELECT MAX(id_pessoa) FROM cliente)");

        // Executa a Query e armazena os dados retornados em um Cursor
        Cursor dados = db.rawQuery(select.toString(), null);

        // Verifica se retornou ao menos um registro
        if (dados.getCount() != 0) {
            // Instancia um Objeto do Tipo Usuario para armazenar os dados
            cli = new Cliente();

            // Move o cursor para o próximo registro
            dados.moveToNext();

            // Preenche o Objeto usuario com os dados do cursor
            cli.setIdPessoa(dados.getLong(0));
            cli.setNome(dados.getString(1));
            cli.setCpf(dados.getString(2));
            cli.setLogin(dados.getString(3));
            cli.setSenha(dados.getString(4));
            cli.setSituacao(dados.getString(5));
        }
        // Retorna o usuário que contém os dados do ultimo registro inserido no banco
        dados.close();
        db.close();
        return cli;
    }

    //lista cliente
    public ArrayList<Cliente> getLista() {
        String[] columns = {"id_pessoa", "nome", "CPF", "login", "senha", "situacao"};
        Cursor cursor = dbHelper.getWritableDatabase().query(TABELA, columns, null, null, null, null, null, null);
        ArrayList<Cliente> clientes = new ArrayList<>();

        while (cursor.moveToNext()) {
            Cliente cli = new Cliente();
            cli.setIdPessoa(cursor.getLong(0));
            cli.setNome(cursor.getString(1));
            cli.setCpf(cursor.getString(2));
            cli.setLogin(cursor.getString(3));
            cli.setSenha(cursor.getString(4));
            cli.setSituacao(cursor.getString(5));
            clientes.add(cli);
        }

        return clientes;
    }

}

