package com.example.recycleviewproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtNome, edtEmail; //activity_main xml
    Button btnAdicionar;
    RecyclerView recyclerView;
    String nome, email;

    Button btnUpdateUser, btnUpdateCancel; //caixa de diálogo
    EditText edtUpdateNome, edtUpdateEmail; //caixa de diálogo

    MeuAdaptador adapter; //classe MeuAdaptador

    //nova lista da classe usuário
    List<Usuario> list = new ArrayList<>(); //guarda os atributos nome e email do usuário

    AlertDialog.Builder builder; //pula na frente da activity e enquanto nao der um cancelar ele nao sai
    AlertDialog dialog;


    @Override
    //o metodo oncrite faz aparecer a activity main quando o programa é execultado
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fazendo conexão com activity_main.xml
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //o recyclerView pode ficar na forma de cards, cartões e de várias formas
        //aqui é falado que o recyclerView ficará da forma LinearLayout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MeuAdaptador(list); //cria na memoria e recebe os parametros da lista de usuários
        recyclerView.setAdapter(adapter); //relacina o recyclerView com a classe MeuAdaptador

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //variáveis que recebem o que o usuário escreveu
                nome = edtNome.getText().toString();
                email = edtEmail.getText().toString();

                Usuario userData = new Usuario();

                userData.setNome(nome);
                userData.setEmail(email);

                list.add(userData);

                //primeiro parametro é onde queremos que o toast apareça
                //segundo parametro é o que queremos que apareça
                //terceiro parametro é o tempo
                Toast.makeText(MainActivity.this, "Usuário adicionado. Sucesso!", Toast.LENGTH_LONG).show();

                //limpar o edt
                edtNome.setText("");
                edtEmail.setText("");

            }
        });

        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnItemClick(int position, Usuario userData) {
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Usuário alterado");
                builder.setCancelable(false);
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialogedit, null, false); //caixa de diálogo
                initUpdateDialog(position, view);
                builder.setView(view); //seta a caixa de diálogo
                dialog = builder.create(); //cria a caixa
                dialog.show(); //mostra a caixa de diálogo
            }
        });
    }

    private void initUpdateDialog(int position, View view){ //recebe a posição do item que deve ser alterado

        edtUpdateNome.findViewById(R.id.edtUpdateName);
        edtUpdateEmail.findViewById(R.id.edtUpdateEmail);
        btnUpdateUser.findViewById(R.id.btnUpdateUser);
        btnUpdateCancel.findViewById(R.id.btnUpdateCancel);

        //seta a alteração feita pelo usuário nos edits de diálogo
        edtUpdateNome.setText(nome);
        edtUpdateEmail.setText(email);

        btnUpdateUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                nome = edtUpdateNome.getText().toString();
                email = edtUpdateEmail.getText().toString();

                Usuario userData = new Usuario();

                userData.setNome(nome);
                userData.setEmail(email);

                adapter.UpdateData(position, userData);
                Toast.makeText(MainActivity.this, "Usuário editado.. Sucesso!!!", Toast.LENGTH_LONG).show();
            }
        });

        //botao de dialogo edit
        btnUpdateCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss(); //fecha a caixa de diálogo
            }
        });
    }
}