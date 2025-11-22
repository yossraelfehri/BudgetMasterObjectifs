package com.example.budgetmasterobjectif;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ObjectifAdapter extends RecyclerView.Adapter<ObjectifAdapter.ViewHolder> {

    ArrayList<Objectif> objectifs;

    public ObjectifAdapter(ArrayList<Objectif> objectifs) {
        this.objectifs = objectifs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_objectif, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int pos) {
        Objectif o = objectifs.get(pos);
        h.nom.setText(o.getNom());
        h.cible.setText("Cible : " + o.getMontantCible());
        h.atteint.setText("Atteint : " + o.getMontantAtteint());
        h.progress.setText("Progression : " + String.format("%.2f", o.getProgression()) + "%");
    }

    @Override
    public int getItemCount() {
        return objectifs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nom, cible, atteint, progress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.tvNom);
            cible = itemView.findViewById(R.id.tvCible);
            atteint = itemView.findViewById(R.id.tvAtteint);
            progress = itemView.findViewById(R.id.tvProgress);
        }
    }
}
