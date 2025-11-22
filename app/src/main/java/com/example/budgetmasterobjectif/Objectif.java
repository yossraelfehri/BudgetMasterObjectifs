package com.example.budgetmasterobjectif;

public class Objectif {
    private int id;
    private String nom;
    private double montantCible;
    private double montantAtteint;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getMontantCible() { return montantCible; }
    public void setMontantCible(double montantCible) { this.montantCible = montantCible; }

    public double getMontantAtteint() { return montantAtteint; }
    public void setMontantAtteint(double montantAtteint) { this.montantAtteint = montantAtteint; }

    public double getProgression() {
        if (montantCible == 0) return 0.0;
        return (montantAtteint / montantCible) * 100.0;
    }
}
