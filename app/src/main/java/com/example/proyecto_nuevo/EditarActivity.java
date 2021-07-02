package com.example.proyecto_nuevo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditarActivity extends AppCompatActivity {

    EditText medDocente;
    EditText medMateria;
    Button mbtnActualizar;

    private String tareaId;
    private FirebaseFirestore miFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        medDocente=findViewById(R.id.edDocente);
        medMateria=findViewById(R.id.edMateria);
        mbtnActualizar=findViewById(R.id.btnActualizar);

        miFirestore = FirebaseFirestore.getInstance();
        tareaId=getIntent().getStringExtra("tareaId");

        mbtnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarDatos();
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Los datos se han actualizado correctamente", Toast.LENGTH_SHORT);

                toast1.show();
            }
        });

    }

    private void actualizarDatos(){

        String Docente=medDocente.getText().toString();
        String Materia=medMateria.getText().toString();

        Map<String,Object> map= new HashMap<>();
        map.put("Docente", Docente);
        map.put("Materia", Materia);
       miFirestore.collection("Tareas-Pendientes").document(tareaId).update(map);

    }
}