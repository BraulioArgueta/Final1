package com.example.proyecto_nuevo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import org.jetbrains.annotations.NotNull;

public class TareasAdapter extends FirestoreRecyclerAdapter<Tareas, TareasAdapter.ViewHolder> {
    Activity activity;
    FirebaseFirestore miFirebase;

    public TareasAdapter(@NonNull @NotNull FirestoreRecyclerOptions<Tareas> options, Activity activity) {
        super(options);
        this.activity=activity;
        miFirebase=FirebaseFirestore.getInstance();
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int i, @NonNull @NotNull Tareas tareas) {

        DocumentSnapshot tareasDocument= getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String id= tareasDocument.getId();
        holder.textViewEnunciado.setText("La tarea consiste en: "+tareas.getEnunciado());
        holder.textViewDocente.setText("Docente: "+tareas.getDocente());
        holder.textViewMateria.setText("Materia: "+tareas.getMateria());
        holder.textViewFechaEntrega.setText("Limite hasta el dia: "+tareas.getFecha_Entrega());

    holder.buttonEditar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(activity,EditarActivity.class);
            intent.putExtra("tareaId",id);
            activity.startActivity(intent);
        }});

    holder.buttonEliminar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        miFirebase.collection("Tareas-Pendientes").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast toast1 =
                        Toast.makeText(activity.getApplicationContext(),
                                "Los datos se han borrado correctamente", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });
        }
    });


    }

    @NonNull
    @NotNull
    @Override


    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_tareas,viewGroup,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewDocente;
        TextView textViewMateria;
        TextView textViewEnunciado;
        TextView textViewFechaEntrega;
        Button buttonEditar;
        Button buttonEliminar;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewDocente=itemView.findViewById(R.id.textViewDocente);
            textViewMateria=itemView.findViewById(R.id.textViewMateria);
            textViewEnunciado=itemView.findViewById(R.id.textViewEnunciado);
            textViewFechaEntrega=itemView.findViewById(R.id.textViewFechaEntrega);
            buttonEditar=itemView.findViewById(R.id.btnEditar);
            buttonEliminar=itemView.findViewById(R.id.btnEliminar);
        }
    }

}
