package com.example.appcontatos.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcontatos.R;
import com.example.appcontatos.database.DBHelper;

public class NovoContatoActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    DBHelper db;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_contato);

        mViewHolder.et_nome = findViewById(R.id.et_novoContato_nome);
        mViewHolder.et_endereco = findViewById(R.id.et_novoContato_endereco);
        mViewHolder.et_telefone = findViewById(R.id.et_novoContato_telefone);
        mViewHolder.et_email = findViewById(R.id.et_novoContato_email);
        mViewHolder.bt_cancelar = findViewById(R.id.bt_novoContato_cancelar);
        mViewHolder.bt_salvar = findViewById(R.id.bt_novoContato_salvar);

        db = new DBHelper(this);

        i = getIntent();

        mViewHolder.bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(0,i);
                finish();
            }
        });

        mViewHolder.bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SALVAR NA BASE DE DADOS

                String nome = mViewHolder.et_nome.getText().toString();
                String endereco = mViewHolder.et_endereco.getText().toString();
                String telefone = mViewHolder.et_telefone.getText().toString();
                String email = mViewHolder.et_email.getText().toString();

                if(nome.isEmpty() || endereco.isEmpty() || telefone.isEmpty() || email.isEmpty()){
                    Toast.makeText(NovoContatoActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }else{
                    long res = db.Insert_Contato(nome, endereco, telefone, email);
                    if(res>0) {
                        Toast.makeText(NovoContatoActivity.this, "Contato salvo com sucesso", Toast.LENGTH_SHORT).show();
                        setResult(1, i);
                        finish();
                    }else{
                        Toast.makeText(NovoContatoActivity.this, "Erro ao criar Contato", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private class ViewHolder{
        EditText et_nome, et_endereco, et_telefone, et_email;
        Button bt_salvar, bt_cancelar;
    }
}