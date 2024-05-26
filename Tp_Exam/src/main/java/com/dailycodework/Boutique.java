package com.dailycodework;


public class Boutique {
    private Depot depot;
    private WheelOfChance wheel;
    private int prixTotal;
    private int quantiteReservee;
    private String produitReserve;

    public void setDepot(Depot depot){
        this.depot=depot;
    }

    public boolean demande(String produit, int qte){
        return depot.chercheProduit(produit)>=qte;
    }

    public int getPrice(String produit){
        return depot.getPrix(produit);
    }

    public int roll(){
        return wheel.roll();
    }

    public int reserver(String produit, int quantite) {
        if (!demande(produit, quantite)) {
            return -1;  // Indique que le produit n'est pas disponible
        }

        int prixUnitaire = depot.getPrix(produit);
        int prixTotal = prixUnitaire * quantite;

        int tour1 = wheel.roll();
        int tour2 = wheel.roll();

        if (tour1 + tour2 > 15) {
            prixTotal *= 0.9;
        }

        depot.destockerProduit(produit, quantite);
        this.prixTotal=prixTotal;
        this.quantiteReservee=quantite;
        this.produitReserve=produit;
        return prixTotal;
    }

    public void annuler(String string, int i) {
        depot.ajouterQuantite(string,i);
    }

    public int valider(int somme) {
        if (somme < prixTotal) {
            annuler(produitReserve, quantiteReservee);
            return -1;  // Achat annulé
        } else {
            return somme - prixTotal;  // Rendre la monnaie
        }
    }


}
