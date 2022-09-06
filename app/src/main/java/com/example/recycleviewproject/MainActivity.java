package com.example.recycleviewproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtNome, edtEmail;
    //EditText edtUpdateNome, edtUpdateEmail;
    Button btnAdicionar;
    RecyclerView recyclerView;
    String nome, email;
    List<Usuario> list = new ArrayList<>(); //guarda os atributos nome e email do usuário


    @Override
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

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
    }
}