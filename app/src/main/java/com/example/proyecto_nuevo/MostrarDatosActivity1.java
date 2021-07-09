package com.example.proyecto_nuevo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MostrarDatosActivity1 extends AppCompatActivity {

    RecyclerView recyclerViewTareas1;
    LinksAdapter mAdapter1;
    FirebaseFirestore miFirestore1;



    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos1);

        recyclerViewTareas1=findViewById(R.id.recyclerTareas1);
        recyclerViewTareas1.setLayoutManager(new LinearLayoutManager(this));
        miFirestore1=FirebaseFirestore.getInstance();

        Query query = miFirestore1.collection("Links-Reuniones");
        FirestoreRecyclerOptions<Tareas> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Tareas>().setQuery(query, Tareas.class).build();

        mAdapter1= new LinksAdapter(firestoreRecyclerOptions, this);
        mAdapter1.notifyDataSetChanged();
        recyclerViewTareas1.setAdapter(mAdapter1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter1.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter1.stopListening();
    }



}