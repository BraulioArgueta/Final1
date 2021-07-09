package com.example.proyecto_nuevo;

import android.app.Activity;
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

import org.jetbrains.annotations.NotNull;

public class LinksAdapter extends FirestoreRecyclerAdapter<Tareas, LinksAdapter.ViewHolder> {
    Activity activity;
    FirebaseFirestore miFirebase;

    public LinksAdapter(@NonNull @NotNull FirestoreRecyclerOptions<Tareas> options, Activity activity) {
        super(options);
        this.activity=activity;
        miFirebase=FirebaseFirestore.getInstance();
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int i, @NonNull @NotNull Tareas tareas) {

        DocumentSnapshot linksDocument= getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String id1= linksDocument.getId();
        holder.textViewMateria1.setText("Materia: "+tareas.getMateria1());
        holder.textViewDocente1.setText("Docente: "+tareas.getDocente1());
        holder.textViewHorario1.setText("Horario de la clase: "+tareas.getHorario1());
        holder.textViewLink1.setText("Link de la reunión: "+tareas.getLink1());

        holder.buttonEditar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity,Editar1.class);
                intent.putExtra("tareaId",id1);
                activity.startActivity(intent);
            }});

        holder.buttonEliminar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miFirebase.collection("Links-Reuniones").document(id1).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
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
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_links,viewGroup,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView textViewMateria1;
        TextView textViewDocente1;
        TextView textViewHorario1;
        TextView textViewLink1;
        Button buttonEditar1;
        Button buttonEliminar1;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewMateria1=itemView.findViewById(R.id.textViewMateria1);
            textViewDocente1=itemView.findViewById(R.id.textViewDocente1);
            textViewHorario1=itemView.findViewById(R.id.textViewHorario1);
            textViewLink1=itemView.findViewById(R.id.textViewLink1);
            buttonEditar1=itemView.findViewById(R.id.btnEditar1);
            buttonEliminar1=itemView.findViewById(R.id.btnEliminar1);
        }
    }

}