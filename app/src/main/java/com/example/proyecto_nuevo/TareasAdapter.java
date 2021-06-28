package com.example.proyecto_nuevo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class TareasAdapter extends FirestoreRecyclerAdapter<Tareas, TareasAdapter.ViewHolder> {


    public TareasAdapter(@NonNull @NotNull FirestoreRecyclerOptions<Tareas> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int i, @NonNull @NotNull Tareas tareas) {
    holder.textViewDocente.setText(tareas.getDocente());
    holder.textViewMateria.setText(tareas.getMateria());


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


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewDocente=itemView.findViewById(R.id.textViewDocente);
            textViewMateria=itemView.findViewById(R.id.textViewMateria);
        }
    }

}
