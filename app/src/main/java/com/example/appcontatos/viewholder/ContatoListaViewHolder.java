package com.example.appcontatos.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcontatos.R;
import com.example.appcontatos.model.Contato;

public class ContatoListaViewHolder extends RecyclerView.ViewHolder {

    private TextView tv_nome;
    private TextView tv_telefone;

    public ContatoListaViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_nome = itemView.findViewById(R.id.rv_item_nome);
        tv_telefone = itemView.findViewById(R.id.rv_item_telefone);
    }

    //Metodo para mostrar apenas os elementos nome e telefone na lista de contatos
    public void bindData(Contato contato) {
        tv_nome.setText(contato.getNome());
        tv_telefone.setText(contato.getTelefone());
    }
}
