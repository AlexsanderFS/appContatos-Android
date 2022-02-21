package com.example.appcontatos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static int versao = 1;
    private static String nomeDB = "AgendaContatos";

    //Criando a base de dados para guardar os contatos salvos
    String[] sql = {
        "CREATE TABLE contato(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,nome TEXT,endereco TEXT,telefone TEXT,email TEXT);",
        "INSERT INTO contato VALUES(1, 'Julia', '20', '99999999', 'julia@mail.com');"
    };

    public DBHelper(@Nullable Context context) {
        super(context, nomeDB, null, versao);
    }


    //Metodo para pegar o tamanho da base de dados
    @Override
    public void onCreate(SQLiteDatabase db) {
        for(int i=0; i< sql.length; i++)
            db.execSQL(sql[i]);
    }

    //Metodo para verificar a tabela e acionar DROP TABLE
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        versao++;
        db.execSQL("DROP TABLE IF EXISTS contacto; ");
        onCreate(db);
    }

    //Metodo de inserir
    public long Insert_Contato(String nome, String endereco, String telefone, String email){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", nome);
        cv.put("endereco", endereco);
        cv.put("telefone", telefone);
        cv.put("email", email);
        return db.insert("contato", null, cv);
    }

    //Metodo de atualizar
    public long Update_Contato(int id, String nome, String endereco, String telefone, String email){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", nome);
        cv.put("endereco", endereco);
        cv.put("telefone", telefone);
        cv.put("email", email);
        return db.update("contato", cv, "id=?", new String[]{String.valueOf(id)});
    }

    //Metodo deletar contato
    public long Delete_Contato(int id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("contato", "id=?", new String[]{String.valueOf(id)});
    }

    //Selecao de todos os contatos
    public Cursor SelectAll_Contato(){
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM contato", null);
    }

    //Selecao de todos os contatos com base no ID
    public Cursor SelectByID_Contato(int id){
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM contato WHERE id=?", new String[]{String.valueOf(id)});
    }
}
