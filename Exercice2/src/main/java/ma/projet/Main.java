package ma.projet;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

  public static void main(String[] args) throws Exception {

    EmployeService employeService         = new EmployeService();
    ProjetService projetService           = new ProjetService();
    TacheService tacheService             = new TacheService();
    EmployeTacheService employeTacheService = new EmployeTacheService();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // 1. Créer des employés
    System.out.println("===== Création des employés =====");
    Employe e1 = new Employe("Alami", "Youssef", "0612345678");
    Employe e2 = new Employe("Bennani", "Sara", "0698765432");
    employeService.create(e1);
    employeService.create(e2);

    // 2. Créer des projets
    System.out.println("===== Création des projets =====");
    Projet p1 = new Projet("Gestion de stock",
            sdf.parse("14/01/2013"), sdf.parse("30/06/2013"), e1);
    Projet p2 = new Projet("Site E-commerce",
            sdf.parse("01/03/2023"), sdf.parse("01/09/2023"), e2);
    projetService.create(p1);
    projetService.create(p2);

    // 3. Créer des tâches
    System.out.println("===== Création des tâches =====");
    Tache t1 = new Tache("Analyse",       sdf.parse("10/02/2013"), sdf.parse("20/02/2013"), 800.0,  p1);
    Tache t2 = new Tache("Conception",    sdf.parse("10/03/2013"), sdf.parse("15/03/2013"), 1200.0, p1);
    Tache t3 = new Tache("Développement", sdf.parse("10/04/2013"), sdf.parse("25/04/2013"), 1500.0, p1);
    Tache t4 = new Tache("Design UI",     sdf.parse("01/04/2023"), sdf.parse("15/04/2023"), 900.0,  p2);
    tacheService.create(t1);
    tacheService.create(t2);
    tacheService.create(t3);
    tacheService.create(t4);

    // 4. Créer des EmployeTache
    System.out.println("===== Création des EmployeTache =====");
    employeTacheService.create(new EmployeTache(
            sdf.parse("10/02/2013"), sdf.parse("20/02/2013"), e1, t1));
    employeTacheService.create(new EmployeTache(
            sdf.parse("10/03/2013"), sdf.parse("15/03/2013"), e1, t2));
    employeTacheService.create(new EmployeTache(
            sdf.parse("10/04/2013"), sdf.parse("25/04/2013"), e2, t3));
    employeTacheService.create(new EmployeTache(
            sdf.parse("01/04/2023"), sdf.parse("15/04/2023"), e2, t4));

    // 5. Tâches réalisées par un employé
    System.out.println("===== Tâches de l'employé : " + e1.getNom() + " =====");
    List<Tache> tachesEmploye = employeService.findTachesByEmploye(e1);
    for (Tache t : tachesEmploye) {
      System.out.println(t.getNom() + " - " + t.getPrix() + " DH");
    }

    // 6. Projets gérés par un employé
    System.out.println("===== Projets gérés par : " + e1.getNom() + " =====");
    List<Projet> projetsEmploye = employeService.findProjetsByEmploye(e1);
    for (Projet p : projetsEmploye) {
      System.out.println(p.getNom() + " - " + sdf.format(p.getDateDebut()));
    }

    // 7. Tâches planifiées pour un projet
    System.out.println("===== Tâches planifiées du projet : " + p1.getNom() + " =====");
    List<Tache> tachesProjet = projetService.findTachesByProjet(p1);
    for (Tache t : tachesProjet) {
      System.out.println(t.getNom() + " - " + t.getPrix() + " DH");
    }

    // 8. Tâches réalisées avec dates réelles
    System.out.println("===== Tâches réalisées du projet =====");
    projetService.afficherTachesRealisees(p1);

    // 9. Tâches dont prix > 1000 DH (NamedQuery)
    System.out.println("===== Tâches dont prix > 1000 DH =====");
    List<Tache> tachesCheres = tacheService.findTachesCheres();
    for (Tache t : tachesCheres) {
      System.out.println(t.getNom() + " - " + t.getPrix() + " DH");
    }

    // 10. Tâches réalisées entre deux dates
    System.out.println("===== Tâches entre 01/01/2013 et 31/12/2013 =====");
    Date debut = sdf.parse("01/01/2013");
    Date fin   = sdf.parse("31/12/2013");
    List<Tache> tachesEntre = tacheService.findTachesEntreDates(debut, fin);
    for (Tache t : tachesEntre) {
      System.out.println(t.getNom() + " - " + t.getPrix() + " DH");
    }

    HibernateUtil.close();

  }
}