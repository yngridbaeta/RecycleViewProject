package com.example.recycleviewproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//MyHolder é um tipo, esta criando um adaptador de nome, email e o lixinho
 class MeuAdaptador extends RecyclerView.Adapter<MeuAdaptador.MyHolder> {

     //defina uma lista de usuario
     List<Usuario> listaUsuario = new ArrayList<>(); //cria uma listaUsuario

     ItemClickListener itemClickListener;

     //construtor da classe
     public MeuAdaptador(List<Usuario> list){
         this.listaUsuario = list; //passa a listaUsuario para o parametro do construtor
     }

     @NonNull
     @Override
     public MeuAdaptador.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
         return new MyHolder(view);
     }

     @Override
     public void onBindViewHolder(@NonNull MeuAdaptador.MyHolder holder, int position) {

     }

     @Override
     public int getItemCount() {
         return 0;
     }

     public static class MyHolder extends RecyclerView.ViewHolder{
         TextView tvNomeItem, tvEmailItem, tvDeleteItem;

         public MyHolder(View itemView){
             //super é o construtor da classe que esta herdando
             super(itemView); //esta mandando o itemView pra cá

             tvNomeItem = itemView.findViewById(R.id.tvNomeItem);
             tvEmailItem = itemView.findViewById(R.id.tvEmailItem);
             tvDeleteItem = itemView.findViewById(R.id.tvDeleteItem);

         }
     }
 }
