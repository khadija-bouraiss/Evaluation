package ma.projet;

import ma.projet.beans.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    try {
      FemmeService femmeService      = new FemmeService();
      HommeService hommeService      = new HommeService();
      MariageService mariageService  = new MariageService();

      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

      // 1. Créer 10 femmes
      System.out.println("===== Création des femmes =====");
      Femme f1  = new Femme("RAMI",   "SALIMA",  "0611111111", "Rabat",   sdf.parse("03/05/1970"));
      Femme f2  = new Femme("ALI",    "AMAL",    "0622222222", "Fes",     sdf.parse("10/08/1975"));
      Femme f3  = new Femme("ALAOUI", "WAFA",    "0633333333", "Meknes",  sdf.parse("04/11/1978"));
      Femme f4  = new Femme("ALAMI",  "KARIMA",  "0644444444", "Sale",    sdf.parse("03/09/1968"));
      Femme f5  = new Femme("IDRISSI","NADIA",   "0655555555", "Tanger",  sdf.parse("15/03/1980"));
      Femme f6  = new Femme("BENCHR", "HOUDA",   "0666666666", "Agadir",  sdf.parse("20/07/1982"));
      Femme f7  = new Femme("ZAHRA",  "FATIMA",  "0677777777", "Oujda",   sdf.parse("11/02/1972"));
      Femme f8  = new Femme("MOUSSA", "SANAE",   "0688888888", "Beni",    sdf.parse("25/09/1985"));
      Femme f9  = new Femme("HASSAN", "LOUBNA",  "0699999999", "Kenitra", sdf.parse("08/04/1977"));
      Femme f10 = new Femme("BENJEL", "SAMIRA",  "0610101010", "Marrak",  sdf.parse("30/12/1965"));

      femmeService.create(f1);
      femmeService.create(f2);
      femmeService.create(f3);
      femmeService.create(f4);
      femmeService.create(f5);
      femmeService.create(f6);
      femmeService.create(f7);
      femmeService.create(f8);
      femmeService.create(f9);
      femmeService.create(f10);

      // 2. Créer 5 hommes
      System.out.println("===== Création des hommes =====");
      Homme h1 = new Homme("SAFI",    "SAID",    "0611000001", "Rabat",  sdf.parse("01/01/1965"));
      Homme h2 = new Homme("NACIRI",  "OMAR",    "0611000002", "Fes",    sdf.parse("15/06/1970"));
      Homme h3 = new Homme("TAZI",    "YOUSSEF", "0611000003", "Meknes", sdf.parse("20/03/1968"));
      Homme h4 = new Homme("BENHIDA", "KHALID",  "0611000004", "Sale",   sdf.parse("10/11/1972"));
      Homme h5 = new Homme("AMRANI",  "HASSAN",  "0611000005", "Agadir", sdf.parse("05/09/1975"));

      hommeService.create(h1);
      hommeService.create(h2);
      hommeService.create(h3);
      hommeService.create(h4);
      hommeService.create(h5);

      // 3. Créer des mariages
      System.out.println("===== Création des mariages =====");
      mariageService.create(new Mariage(sdf.parse("03/09/1989"), sdf.parse("03/09/1990"), 0,  h1, f4));
      mariageService.create(new Mariage(sdf.parse("03/09/1990"), null,                    4,  h1, f1));
      mariageService.create(new Mariage(sdf.parse("03/09/1995"), null,                    2,  h1, f2));
      mariageService.create(new Mariage(sdf.parse("04/11/2000"), null,                    3,  h1, f3));
      mariageService.create(new Mariage(sdf.parse("01/01/1995"), null,                    2,  h2, f5));
      mariageService.create(new Mariage(sdf.parse("01/01/2000"), null,                    1,  h2, f6));
      mariageService.create(new Mariage(sdf.parse("01/03/1992"), null,                    3,  h3, f7));
      mariageService.create(new Mariage(sdf.parse("01/03/1994"), null,                    2,  h3, f8));
      mariageService.create(new Mariage(sdf.parse("01/03/1996"), null,                    1,  h3, f9));
      mariageService.create(new Mariage(sdf.parse("01/03/1998"), null,                    4,  h3, f10));
      mariageService.create(new Mariage(sdf.parse("05/05/2005"), null,                    2,  h4, f5));

      System.out.println("===== Mariages créés, début des affichages =====");

      // 4. Afficher la liste des femmes
      System.out.println("\n===== Liste des femmes =====");
      List<Femme> femmes = femmeService.findAll();
      for (Femme f : femmes) {
        System.out.println(f.getNom() + " " + f.getPrenom());
      }

      // 5. Femme la plus âgée
      System.out.println("\n===== Femme la plus âgée =====");
      Femme plusAgee = femmeService.findFemmePlusAgee();
      System.out.println(plusAgee.getNom() + " " + plusAgee.getPrenom() +
              " - " + sdf.format(plusAgee.getDateNaissance()));

      // 6. Épouses d'un homme entre deux dates
      System.out.println("\n===== Épouses de SAFI entre 1989 et 2001 =====");
      List<Femme> epouses = hommeService.findEpousesEntreDates(h1,
              sdf.parse("01/01/1989"), sdf.parse("31/12/2001"));
      for (Femme f : epouses) {
        System.out.println(f.getNom() + " " + f.getPrenom());
      }

      // 7. Nombre d'enfants d'une femme entre deux dates
      System.out.println("\n===== Nbr enfants de RAMI SALIMA entre 1990 et 2000 =====");
      int nbr = femmeService.getNbrEnfantsEntreDates(f1,
              sdf.parse("01/01/1990"), sdf.parse("31/12/2000"));
      System.out.println("Nombre d'enfants : " + nbr);

      // 8. Femmes mariées deux fois ou plus
      System.out.println("\n===== Femmes mariées 2 fois ou plus =====");
      List<Femme> deuxFois = femmeService.findFemmesMarieesDeuxFois();
      for (Femme f : deuxFois) {
        System.out.println(f.getNom() + " " + f.getPrenom());
      }

      // 9. Hommes mariés à 4 femmes entre deux dates (Criteria)
      System.out.println("\n===== Hommes mariés à 4 femmes entre 1990 et 2005 =====");
      List<Homme> hommes4 = hommeService.findHommesMaries4FemmesEntreDates(
              sdf.parse("01/01/1990"), sdf.parse("31/12/2005"));
      for (Homme h : hommes4) {
        System.out.println(h.getNom() + " " + h.getPrenom());
      }

      // 10. Mariages de SAFI SAID avec tous les détails
      System.out.println("\n===== Détails mariages de SAFI SAID =====");
      hommeService.afficherMariagesHomme(h1);

      HibernateUtil.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}