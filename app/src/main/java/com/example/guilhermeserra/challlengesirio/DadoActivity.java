package com.example.guilhermeserra.challlengesirio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.guilhermeserra.challlengesirio.model.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DadoActivity extends AppCompatActivity {

    EditText editTextName;
    Spinner spinnerGenres;
    Button buttonAdd;

    DatabaseReference databaseUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        databaseUsuario = FirebaseDatabase.getInstance().getReference("usuarios");

        editTextName = (EditText) findViewById(R.id.editTextName);
        spinnerGenres = (Spinner) findViewById(R.id.spinnerGenero);
        buttonAdd = (Button) findViewById(R.id.btnAdicionarDados);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarDados();
            }
        });
    }

    private void adicionarDados(){
        String name = editTextName.getText().toString().trim();
        String genre = spinnerGenres.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){
            String id = databaseUsuario.push().getKey();

            Usuario usuario = new Usuario(id, name, genre);

            databaseUsuario.child(id).setValue(usuario);
            Toast.makeText(this, "Usuario adicionado", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }
    }
}
