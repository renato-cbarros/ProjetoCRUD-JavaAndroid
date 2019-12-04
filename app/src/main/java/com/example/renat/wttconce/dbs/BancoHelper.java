package com.example.renat.wttconce.dbs;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "concessionaria_wtt";
    public static final int VERSION = 1;



    public BancoHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String tableCliente = "CREATE TABLE cliente (" +
                "id_pessoa INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nome TEXT," +
                "CPF TEXT," +
                "login TEXT," +
                "senha TEXT," +
                "situacao TEXT" +
                ");";

        sqLiteDatabase.execSQL(tableCliente);

        String tablePedido = "CREATE TABLE pedido (" +
                "id_pedido INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "dt_compra TEXT," +
                "valor TEXT," +
                "observacao TEXT," +
                "status TEXT," +
                "modelo_carro TEXT" +
                ");";

        sqLiteDatabase.execSQL(tablePedido);

        String tableClientePedido = "CREATE TABLE cliente_pedido (" +
                "id_cliente_pedido INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "id_pessoa INTEGER," +
                "id_pedido INTEGER," +
                "FOREIGN KEY(id_pessoa) REFERENCES cliente (id_pessoa)," +
                "FOREIGN KEY(id_pedido) REFERENCES pedido (id_pedido)" +
                ");";

        sqLiteDatabase.execSQL(tableClientePedido);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String verificaTableCliente = "DROP TABLE IF EXISTS cliente";

        sqLiteDatabase.execSQL(verificaTableCliente);

        String verificaTablePedido = "DROP TABLE IF EXISTS pedido";

        sqLiteDatabase.execSQL(verificaTablePedido);

        String verificaTableClientePedido = "DROP TABLE IF EXISTS cliente_pedido";

        sqLiteDatabase.execSQL(verificaTableClientePedido);

    }

}
