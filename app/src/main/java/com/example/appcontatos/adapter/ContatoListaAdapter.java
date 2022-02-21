package com.example.appcontatos.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcontatos.activity.InformacaoContatoActivity;
import com.example.appcontatos.model.Contato;
import com.example.appcontatos.viewholder.ContatoListaViewHolder;
import com.example.appcontatos.R;

import java.util.List;

public class ContatoListaAdapter extends RecyclerView.Adapter<ContatoListaViewHolder> {

    private List<Contato> listaContatos;
    private View contatoView;

    public ContatoListaAdapter(List<Contato> listaContatos){
        this.listaContatos = listaContatos;
    }

    @NonNull
    @Override
    public ContatoListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        contatoView = inflater.inflate(R.layout.row_contato_lista, parent, false);
        return new ContatoListaViewHolder(contatoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContatoListaViewHolder holder, int position) {
        Contato contato = listaContatos.get(position);
        holder.bindData(contato);

        //Metodo para mostrar os detalhes do contato ao selecionar o item na lista
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(contatoView.getContext(), InformacaoContatoActivity.class);
                i.putExtra("id", contato.getId());
                contatoView.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaContatos.size();
    }
}
