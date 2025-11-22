package com.example.budgetmasterobjectif;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddObjectifActivity extends AppCompatActivity {

    EditText etNom, etCible;
    Button btnValider, btnAnnuler;
    DBHelper db;

    // bottom nav buttons
    Button btnInvest, btnObjectif, btnStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_objectif);

        db = new DBHelper(this);

        etNom = findViewById(R.id.etNom);
        etCible = findViewById(R.id.etCible);
        btnValider = findViewById(R.id.btnValider);
        btnAnnuler = findViewById(R.id.btnAnnuler);

        // bottom nav from include
        btnInvest = findViewById(R.id.btnInvest);
        btnObjectif = findViewById(R.id.btnObjectif);
        btnStats = findViewById(R.id.btnStats);

        btnValider.setOnClickListener(v -> {
            String nom = etNom.getText().toString().trim();
            String cibleStr = etCible.getText().toString().trim();

            if (nom.isEmpty() || cibleStr.isEmpty()) {
                Toast.makeText(this, "Champs incomplets", Toast.LENGTH_SHORT).show();
                return;
            }

            double cible;
            try {
                cible = Double.parseDouble(cibleStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Montant cible invalide", Toast.LENGTH_SHORT).show();
                return;
            }

            db.addObjectif(nom, cible);

            // vider les champs (comportement demandé)
            etNom.setText("");
            etCible.setText("");

            Toast.makeText(this, "Objectif enregistré", Toast.LENGTH_SHORT).show();
            // on reste sur la page pour permettre d'ajouter d'autres objectifs
        });

        btnAnnuler.setOnClickListener(v -> {
            etNom.setText("");
            etCible.setText("");
        });

        // bottom nav actions
        btnObjectif.setOnClickListener(v -> {
            // retourner à la liste d'objectifs
            Intent i = new Intent(this, ObjectifsActivity.class);
            // on veut éviter d'empiler trop d'activités
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(i);
            // pas finish ici ; ObjectifsActivity.onResume rechargera la liste
        });

        btnInvest.setOnClickListener(v -> Toast.makeText(this, "Investissements (non implémenté)", Toast.LENGTH_SHORT).show());
        btnStats.setOnClickListener(v -> Toast.makeText(this, "Statistiques (non implémenté)", Toast.LENGTH_SHORT).show());
    }
}
