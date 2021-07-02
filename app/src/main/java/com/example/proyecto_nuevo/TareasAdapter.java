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
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

public class TareasAdapter extends FirestoreRecyclerAdapter<Tareas, TareasAdapter.ViewHolder> {
    Activity activity;

    public TareasAdapter(@NonNull @NotNull FirestoreRecyclerOptions<Tareas> options, Activity activity) {
        super(options);
        this.activity=activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int i, @NonNull @NotNull Tareas tareas) {

        DocumentSnapshot tareasDocument= getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String id= tareasDocument.getId();
        System.out.println(id);
    holder.textViewDocente.setText(tareas.getDocente());
    holder.textViewMateria.setText(tareas.getMateria());

    holder.buttonEditar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(activity,EditarActivity.class);
            intent.putExtra("tareaId",id);
            activity.startActivity(intent);
        }});


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
        Button buttonEditar;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewDocente=itemView.findViewById(R.id.textViewDocente);
            textViewMateria=itemView.findViewById(R.id.textViewMateria);
            buttonEditar=itemView.findViewById(R.id.btnEditar);
        }
    }

}
