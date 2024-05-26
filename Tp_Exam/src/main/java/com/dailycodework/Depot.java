package com.dailycodework;

public interface Depot {
        int chercheProduit(String p); //Retourne la quantité du produit p dans le dépôt
        void destockerProduit(String p,int qte) ; // Detocker la quantité qte du produit p
        void ajouterQuantite(String p,int qte) ; // Ajouter dans le dépôt la quantité qte du produit p
        int getPrix(String p); // Renvoyer le prix unitaire du produit p
        // void setPrix(String p,int prix);
    }

