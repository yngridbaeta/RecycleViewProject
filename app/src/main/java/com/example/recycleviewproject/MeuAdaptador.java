package com.example.recycleviewproject;

import android.annotation.SuppressLint;
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

    //clicar no item e pegar o evento dele
    ItemClickListener itemClickListener;

    //construtor da classe
    public MeuAdaptador(List<Usuario> list) {
        this.listaUsuario = list; //passa a listaUsuario para o parametro do construtor
    }

    @NonNull
    @Override
    //para podermos criar uma view
    public MeuAdaptador.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //um objeto do tipo view, vai ser o item row do xml, que tem o nome, email e lixo do xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuAdaptador.MyHolder holder, @SuppressLint("RecyclerView") int position) {
        final Usuario userData = listaUsuario.get(position);

        holder.tvNomeItem.setText(userData.getNome());
        holder.tvEmailItem.setText(userData.getEmail());

        //esa parte será acionada somente quando um dos botoes forem "acionados"
        holder.itemView.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.OnItemClick(position, userData);
            }
        }));

        holder.tvDeleteItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                listaUsuario.remove(position);
                notifyDataSetChanged();
            }});
        }

    //retorna quantos elementos tem-ne na lista de usuários
    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        TextView tvNomeItem, tvEmailItem, tvDeleteItem;

        public MyHolder(View itemView) {
            //super é o construtor da classe que esta herdando
            super(itemView); //esta mandando o itemView pra cá

            tvNomeItem = itemView.findViewById(R.id.tvNomeItem);
            tvEmailItem = itemView.findViewById(R.id.tvEmailItem);
            tvDeleteItem = itemView.findViewById(R.id.tvDeleteItem);

        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public void UpdateData(int position, Usuario userData){
        listaUsuario.remove(position);
        listaUsuario.add(userData);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }
 }
