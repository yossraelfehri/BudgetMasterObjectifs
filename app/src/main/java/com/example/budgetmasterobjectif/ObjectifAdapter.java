package com.example.budgetmasterobjectif;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ObjectifAdapter extends RecyclerView.Adapter<ObjectifAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Objectif> list;
    private DBHelper db;

    public ObjectifAdapter(Context context, ArrayList<Objectif> list) {
        this.context = context;
        this.list = list;
        db = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_objectif, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Objectif o = list.get(position);

        holder.tvNom.setText(o.getNom());
        holder.tvCible.setText("Cible : " + o.getMontantCible());
        holder.tvAtteint.setText("Atteint : " + o.getMontantAtteint());
        holder.tvPourcentage.setText("Progression : " + o.getProgression() + "%");

        holder.btnDelete.setOnClickListener(v -> {
            db.deleteObjectif(o.getId());
            list.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size());
            Toast.makeText(context, "Objectif supprimé", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNom, tvCible, tvAtteint, tvPourcentage;
        Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNom = itemView.findViewById(R.id.tvNom);
            tvCible = itemView.findViewById(R.id.tvCible);
            tvAtteint = itemView.findViewById(R.id.tvAtteint);
            tvPourcentage = itemView.findViewById(R.id.tvPourcentage);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
