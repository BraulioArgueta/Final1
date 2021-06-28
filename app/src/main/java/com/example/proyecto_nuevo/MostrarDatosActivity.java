package com.example.proyecto_nuevo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MostrarDatosActivity extends AppCompatActivity {

    RecyclerView recyclerViewTareas;
    TareasAdapter mAdapter;
    FirebaseFirestore miFirestore;



    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);

        recyclerViewTareas=findViewById(R.id.recyclerTareas);
        recyclerViewTareas.setLayoutManager(new LinearLayoutManager(this));
        miFirestore=FirebaseFirestore.getInstance();

        Query query = miFirestore.collection("Tareas-Pendientes");
        FirestoreRecyclerOptions<Tareas> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Tareas>().setQuery(query, Tareas.class).build();

        mAdapter= new TareasAdapter(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        recyclerViewTareas.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }



}