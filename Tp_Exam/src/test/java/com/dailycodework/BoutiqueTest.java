package com.dailycodework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BoutiqueTest {
    @InjectMocks
    private Boutique boutique=new Boutique();

    @Mock
    private Depot depot;

    @Mock
    private WheelOfChance wheel;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenDemandeProduitReturnTrue(){
        when(depot.chercheProduit("test")).thenReturn(9);

        assert boutique.demande("test",8)==true;
    }

    @Test
    public void whenRollThenPriceCut(){
        when(wheel.roll()).thenReturn(9).thenReturn(10);
        assert boutique.roll()+boutique.roll()>15;

        int price=50;
        when(depot.getPrix("test")).thenReturn(price).thenReturn(price-price/10);
        int oldPrice=depot.getPrix("test");
        int newPrice=depot.getPrix("test");
        int expectedNewPrice=oldPrice-oldPrice/10;
        assert newPrice==expectedNewPrice;
    }

    @Test
    void testReserverSansRemise() {
        String produit = "ProduitA";
        int quantite = 5;
        int prixUnitaire = 100;

        when(depot.chercheProduit(produit)).thenReturn(10);
        when(depot.getPrix(produit)).thenReturn(prixUnitaire);
        when(wheel.roll()).thenReturn(5, 5);

        assertEquals(500, boutique.reserver(produit, quantite));
        verify(depot).destockerProduit(produit, quantite);
    }

    @Test
    void testReserverAvecRemise() {
        String produit = "ProduitA";
        int quantite = 5;
        int prixUnitaire = 100;

        when(depot.chercheProduit(produit)).thenReturn(10);
        when(depot.getPrix(produit)).thenReturn(prixUnitaire);
        when(wheel.roll()).thenReturn(8, 8);

        assertEquals(450, boutique.reserver(produit, quantite));
        verify(depot).destockerProduit(produit, quantite);//spy
    }

    @Test
    public void whenAnnulerThenStock(){
        when(depot.chercheProduit("test")).thenReturn(5);
        int oldQte=depot.chercheProduit("test");
        int returnedQte=5;
        boutique.annuler("test",returnedQte);
        when(depot.chercheProduit("test")).thenReturn(10);
        assert depot.chercheProduit("test")==oldQte+returnedQte;
    }

    @Test
    void testAnnulerCommande() {
        String produit = "produitA";
        int quantite = 5;

        boutique.demande(produit, quantite);
        boutique.annuler(produit,quantite);

        verify(depot).ajouterQuantite(produit, quantite);
    }

    @Test
    void testValiderAchatInsuffisant() {
        String produit = "ProduitA";
        int quantite = 5;
        int prixUnitaire = 100;

        when(depot.chercheProduit(produit)).thenReturn(10);
        when(depot.getPrix(produit)).thenReturn(prixUnitaire);
        when(wheel.roll()).thenReturn(5, 5);

        boutique.reserver(produit, quantite);

        assertEquals(-1, boutique.valider(400));  // Achat annulé
    }

    @Test
    void testValiderAchatSuffisant() {
        String produit = "ProduitA";
        int quantite = 5;
        int prixUnitaire = 100;

        when(depot.chercheProduit(produit)).thenReturn(10);
        when(depot.getPrix(produit)).thenReturn(prixUnitaire);
        when(wheel.roll()).thenReturn(5, 5);

        boutique.reserver(produit, quantite);

        assertEquals(100, boutique.valider(600));  // 500 payé, 100 rendu
    }



}