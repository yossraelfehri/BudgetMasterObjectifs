package com.example.budgetmasterobjectif;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ObjectifsActivity extends AppCompatActivity {

    DBHelper db;
    RecyclerView rv;
    Button btnAdd;
    ArrayList<Objectif> liste;
    ObjectifAdapter adapter;

    // bottom nav buttons
    Button btnInvest, btnObjectif, btnStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectifs);

        db = new DBHelper(this);

        rv = findViewById(R.id.rvObjectifs);
        btnAdd = findViewById(R.id.btnAdd);

        // bottom nav
        btnInvest = findViewById(R.id.btnInvest);
        btnObjectif = findViewById(R.id.btnObjectif);
        btnStats = findViewById(R.id.btnStats);

        rv.setLayoutManager(new LinearLayoutManager(this));
        loadObjectifs();

        btnAdd.setOnClickListener(v -> startActivity(new Intent(this, AddObjectifActivity.class)));

        // bottom nav actions
        btnObjectif.setOnClickListener(v -> {
            // already here, just reload
            loadObjectifs();
            Toast.makeText(this, "Objectifs", Toast.LENGTH_SHORT).show();
        });

        btnInvest.setOnClickListener(v -> Toast.makeText(this, "Investissements (non implémenté)", Toast.LENGTH_SHORT).show());
        btnStats.setOnClickListener(v -> Toast.makeText(this, "Statistiques (non implémenté)", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadObjectifs();
    }

    private void loadObjectifs() {
        liste = db.getAllObjectifs();
        adapter = new ObjectifAdapter(liste);
        rv.setAdapter(adapter);
    }
}
