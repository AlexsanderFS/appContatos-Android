package com.example.appcontatos.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.appcontatos.R;
import com.example.appcontatos.database.DBHelper;

public class InformacaoContatoActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    Intent i;
    DBHelper db;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacao_contato);

        //Adicionando um ViewHolder para os componentes do contato

        mViewHolder.bt_modificar = findViewById(R.id.bt_detalhesContato);
        mViewHolder.bt_editar = findViewById(R.id.bt_detalhesContato_editar);
        mViewHolder.bt_cancelar = findViewById(R.id.bt_detalhesContato_cancelar);
        mViewHolder.bt_deletar = findViewById(R.id.bt_detalhesContato_deletar);
        mViewHolder.ll_botoes = findViewById(R.id.ll_detalhesContato);
        mViewHolder.et_nome = findViewById(R.id.et_detalhesContato_nome);
        mViewHolder.et_endereco = findViewById(R.id.et_detalhesContato_endereco);
        mViewHolder.et_telefone = findViewById(R.id.et_detalhesContato_telefone);
        mViewHolder.et_email = findViewById(R.id.et_detalhesContato_email);
        mViewHolder.bt_ligar = findViewById(R.id.bt_ligar);

        //Botão deletar contato
        mViewHolder.bt_deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //VOLTAR AO MENU ANTERIOR APOS DELETAR REGISTRO
                long res = db.Delete_Contato(id);
                if(res>0) {
                    Toast.makeText(InformacaoContatoActivity.this, "O Contato foi Deletado", Toast.LENGTH_SHORT).show();
                    setResult(1, i);
                    finish();
                }else{
                    Toast.makeText(InformacaoContatoActivity.this, "Erro ao deletar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Botão cancelar ação
        mViewHolder.bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DisabledEdicao();
                CarregarDadosContato();
            }
        });

        //Botão editar contato
        mViewHolder.bt_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = mViewHolder.et_nome.getText().toString();
                String endereco = mViewHolder.et_endereco.getText().toString();
                String telefone = mViewHolder.et_telefone.getText().toString();
                String email = mViewHolder.et_email.getText().toString();

                long res = db.Update_Contato(id, nome, endereco, telefone, email);

                if(res>0){
                    Toast.makeText(InformacaoContatoActivity.this, "Contato editado com Sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InformacaoContatoActivity.this, "Erro ao editar", Toast.LENGTH_SHORT).show();
                }

                DisabledEdicao();
            }
        });

        //Botão modificar modo de exibição
        mViewHolder.bt_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               EnabledEdicao();
            }
        });

        //Botão efetuar chamada
        mViewHolder.bt_ligar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numTel = mViewHolder.et_telefone.getText().toString().trim();
                if(!numTel.isEmpty()){
                    Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + numTel));
                    startActivity(i);
                }
            }
        });

        db = new DBHelper(this);

        i = getIntent();
        id = i.getExtras().getInt("id");

        CarregarDadosContato();

        //Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();

    }

    //Método para carregar dados dos Contatos
    private void CarregarDadosContato(){
        Cursor c = db.SelectByID_Contato(id);
        c.moveToFirst();
        if(c.getCount()==1){
            @SuppressLint("Range") String nome = c.getString(c.getColumnIndex("nome"));
            @SuppressLint("Range") String endereco = c.getString(c.getColumnIndex("endereco"));
            @SuppressLint("Range") String telefone = c.getString(c.getColumnIndex("telefone"));
            @SuppressLint("Range") String email = c.getString(c.getColumnIndex("email"));

            mViewHolder.et_nome.setText(nome);
            mViewHolder.et_endereco.setText(endereco);
            mViewHolder.et_telefone.setText(telefone);
            mViewHolder.et_email.setText(email);

        }else{
            Toast.makeText(this, "Não foi possível carregar os dados", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    //Função para desabilitar edição do contato

    private void DisabledEdicao(){

        mViewHolder.et_nome.setEnabled(false);
        mViewHolder.et_endereco.setEnabled(false);
        mViewHolder.et_telefone.setEnabled(false);
        mViewHolder.et_email.setEnabled(false);

        mViewHolder.ll_botoes.setVisibility(View.GONE);
        mViewHolder.bt_modificar.setVisibility(View.VISIBLE);
    }

    //Função para habilitar edição do contato

    private void EnabledEdicao(){

        mViewHolder.et_nome.setEnabled(true);
        mViewHolder.et_endereco.setEnabled(true);
        mViewHolder.et_telefone.setEnabled(true);
        mViewHolder.et_email.setEnabled(true);

        mViewHolder.ll_botoes.setVisibility(View.VISIBLE);
        mViewHolder.bt_modificar.setVisibility(View.GONE);

    }

    //Método para identificar o ViewHolder
    private class ViewHolder{
        EditText et_nome, et_endereco, et_telefone, et_email;
        Button bt_editar, bt_deletar, bt_cancelar, bt_modificar;
        Button bt_ligar;
        LinearLayout ll_botoes;
    }
}