package com.example.appcontatos.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcontatos.ContatoMock;
import com.example.appcontatos.R;
import com.example.appcontatos.adapter.ContatoListaAdapter;
import com.example.appcontatos.database.DBHelper;
import com.example.appcontatos.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ListaContatosActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private DBHelper db;
    private List<Contato> listaContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        mViewHolder.bt_novo = findViewById(R.id.bt_listaContatos_novo);
        mViewHolder.rv = findViewById(R.id.rv_listaContatos);

        db = new DBHelper(this);

        //ContatoMock contatoMock = new ContatoMock();
        listaContatos = new ArrayList<>();
        //listaContatos.addAll(contatoMock.getListaContatos());

        listarContatos();

        ContatoListaAdapter contatoListaAdapter = new ContatoListaAdapter(listaContatos);
        mViewHolder.rv.setAdapter(contatoListaAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mViewHolder.rv.setLayoutManager(linearLayoutManager);

        //Metodo que acionar o botão para ir a tela de novo contato
        mViewHolder.bt_novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaContatosActivity.this, NovoContatoActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    //Metodo que lista os contatos da base de dados
    private void listarContatos() {
        Cursor c = db.SelectAll_Contato();
        c.moveToFirst();
        listaContatos.clear();
        if(c.getCount()>0){
            do{
                @SuppressLint("Range") int id = c.getInt(c.getColumnIndex("id"));
                @SuppressLint("Range") String nome = c.getString(c.getColumnIndex("nome"));
                @SuppressLint("Range") String endereco = c.getString(c.getColumnIndex("endereco"));
                @SuppressLint("Range") String telefone = c.getString(c.getColumnIndex("telefone"));
                @SuppressLint("Range") String email = c.getString(c.getColumnIndex("email"));

                listaContatos.add(new Contato(id, nome, endereco, telefone, email));

            }while(c.moveToNext());
        }

        ContatoListaAdapter contatoListaAdapter = new ContatoListaAdapter(listaContatos);
        mViewHolder.rv.setAdapter(contatoListaAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mViewHolder.rv.setLayoutManager(linearLayoutManager);
    }

    //Metodo ViewHolder

    private class ViewHolder{
        Button bt_novo;
        RecyclerView rv;
    }

    //Metodo override para fazer as verificações da modicações da lista
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==1 && data != null){
            //NOVO REGISTRO CRIADO
            listarContatos();
        }else if(requestCode==1 && resultCode==0 && data != null){
            //NOVO REGISTRO CANCELADO
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        listarContatos();
    }
}