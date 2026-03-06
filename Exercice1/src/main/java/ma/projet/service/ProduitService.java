package ma.projet.service;

import ma.projet.classes.Categorie;
import ma.projet.classes.Commande;
import ma.projet.classes.Produit;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class ProduitService implements IDao<Produit> {

  @Override
  public boolean create(Produit p) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(p);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      em.getTransaction().rollback();
      e.printStackTrace();
      return false;
    } finally {
      em.close();
    }
  }

  @Override
  public boolean update(Produit p) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(p);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      em.getTransaction().rollback();
      e.printStackTrace();
      return false;
    } finally {
      em.close();
    }
  }

  @Override
  public boolean delete(Produit p) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      Produit managed = em.find(Produit.class, p.getId());
      if (managed != null) em.remove(managed);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      em.getTransaction().rollback();
      e.printStackTrace();
      return false;
    } finally {
      em.close();
    }
  }

  @Override
  public Produit findById(int id) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.find(Produit.class, id);
    } finally {
      em.close();
    }
  }

  @Override
  public List<Produit> findAll() {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
    } finally {
      em.close();
    }
  }

  // Produits par catégorie
  public List<Produit> findByCategorie(Categorie categorie) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery(
                      "SELECT p FROM Produit p WHERE p.categorie = :cat", Produit.class)
              .setParameter("cat", categorie)
              .getResultList();
    } finally {
      em.close();
    }
  }

  // Produits commandés entre deux dates
  public List<Produit> findProduitsCommandesEntreDates(Date dateDebut, Date dateFin) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery(
                      "SELECT DISTINCT lcp.produit FROM LigneCommandeProduit lcp " +
                              "WHERE lcp.commande.date BETWEEN :dateDebut AND :dateFin", Produit.class)
              .setParameter("dateDebut", dateDebut)
              .setParameter("dateFin", dateFin)
              .getResultList();
    } finally {
      em.close();
    }
  }

  // Produits d'une commande donnée
  public void afficherProduitsParCommande(Commande commande) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      List<Object[]> results = em.createQuery(
                      "SELECT lcp.produit.reference, lcp.produit.prix, lcp.quantite " +
                              "FROM LigneCommandeProduit lcp WHERE lcp.commande = :cmd", Object[].class)
              .setParameter("cmd", commande)
              .getResultList();

      System.out.println("Commande : " + commande.getId() +
              "     Date : " + commande.getDate());
      System.out.println("Liste des produits :");
      System.out.printf("%-12s %-10s %s%n", "Référence", "Prix", "Quantité");
      for (Object[] row : results) {
        String ref = (String) row[0];
        float prix = (Float) row[1];
        int qte = (Integer) row[2];
        System.out.printf("%-12s %-10s %d%n", ref, (int) prix + " DH", qte);
      }
    } finally {
      em.close();
    }
  }

  // Produits dont prix > 100 DH via NamedQuery
  public List<Produit> findProduitsChers() {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createNamedQuery("Produit.findByPrixSuperieur100", Produit.class)
              .getResultList();
    } finally {
      em.close();
    }
  }
}