package ma.projet;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

  public static void main(String[] args) throws Exception {

    CategorieService categorieService = new CategorieService();
    ProduitService produitService = new ProduitService();
    CommandeService commandeService = new CommandeService();
    LigneCommandeService ligneService = new LigneCommandeService();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // 1. Créer des catégories
    System.out.println(" Création des catégories ");
    Categorie cat1 = new Categorie("PC", "Ordinateurs");
    Categorie cat2 = new Categorie("PERIPH", "Périphériques");
    categorieService.create(cat1);
    categorieService.create(cat2);

    // 2. Créer des produits
    System.out.println("===== Création des produits =====");
    Produit p1 = new Produit("ES12", 120f, cat1);
    Produit p2 = new Produit("ZR85", 100f, cat1);
    Produit p3 = new Produit("EE85", 200f, cat2);
    Produit p4 = new Produit("AA10", 80f, cat2);
    produitService.create(p1);
    produitService.create(p2);
    produitService.create(p3);
    produitService.create(p4);

    // 3. Produits par catégorie
    System.out.println("===== Produits par catégorie =====");
    List<Produit> produitsPC = produitService.findByCategorie(cat1);
    for (Produit p : produitsPC) {
      System.out.println(p.getReference() + " - " + p.getPrix() + " DH");
    }

    // 4. Créer des commandes
    System.out.println("===== Création des commandes =====");
    Commande cmd1 = new Commande(sdf.parse("14/03/2013"));
    Commande cmd2 = new Commande(sdf.parse("20/06/2023"));
    commandeService.create(cmd1);
    commandeService.create(cmd2);

    // 5. Créer des lignes de commande
    System.out.println("===== Création des lignes =====");
    ligneService.create(new LigneCommandeProduit(7, cmd1, p1));
    ligneService.create(new LigneCommandeProduit(14, cmd1, p2));
    ligneService.create(new LigneCommandeProduit(5, cmd1, p3));
    ligneService.create(new LigneCommandeProduit(3, cmd2, p4));

    // 6. Afficher produits d'une commande
    System.out.println("===== Produits de la commande 1 =====");
    produitService.afficherProduitsParCommande(cmd1);

    // 7. Produits commandés entre deux dates
    System.out.println("===== Produits entre 01/01/2013 et 31/12/2013 =====");
    Date debut = sdf.parse("01/01/2013");
    Date fin = sdf.parse("31/12/2013");
    List<Produit> produitsEntre = produitService.findProduitsCommandesEntreDates(debut, fin);
    for (Produit p : produitsEntre) {
      System.out.println(p.getReference() + " - " + p.getPrix() + " DH");
    }

    // 8. Produits dont prix > 100 DH (NamedQuery)
    System.out.println("===== Produits dont prix > 100 DH =====");
    List<Produit> produitsChers = produitService.findProduitsChers();
    for (Produit p : produitsChers) {
      System.out.println(p.getReference() + " - " + p.getPrix() + " DH");
    }

    HibernateUtil.close();

  }
}