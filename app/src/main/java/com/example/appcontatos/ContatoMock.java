//OBS: Essa classe foi usada apenas para testes ao adicionar os items da lista antes de ter a base
//de dados

package com.example.appcontatos;

import com.example.appcontatos.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoMock {

    private List<Contato> listaContatos;

    public ContatoMock(){
        setListaContatos(new ArrayList<>());
        getListaContatos().add(new Contato(1, "Alexsander", "Rua 21", "91496386", "alex@gmail.com"));
        getListaContatos().add(new Contato(2, "Alex", "Rua do Bobo", "91967747","silva@gmail.com"));
        getListaContatos().add(new Contato(3, "Tande", "Rua 13", "91722946", "figueiredo@gmail.com"));
    }

    public List<Contato> getListaContatos() {
        return listaContatos;
    }

    public void setListaContatos(List<Contato> listaContatos) {
        this.listaContatos = listaContatos;
    }

    public Contato getContatoByID(int id){
        for(int i = 0; i<listaContatos.size(); i++){
            if(listaContatos.get(i).getId()==id)
                return listaContatos.get(i);
        }

        return null;
    }




}
