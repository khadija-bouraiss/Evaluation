package ma.projet.service;

import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.List;

public class ProjetService implements IDao<Projet> {

  @Override
  public boolean create(Projet p) {
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
  public boolean update(Projet p) {
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
  public boolean delete(Projet p) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      Projet managed = em.find(Projet.class, p.getId());
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
  public Projet findById(int id) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.find(Projet.class, id);
    } finally {
      em.close();
    }
  }

  @Override
  public List<Projet> findAll() {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery("SELECT p FROM Projet p", Projet.class).getResultList();
    } finally {
      em.close();
    }
  }

  // Tâches planifiées pour un projet
  public List<Tache> findTachesByProjet(Projet projet) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery(
                      "SELECT t FROM Tache t WHERE t.projet = :proj", Tache.class)
              .setParameter("proj", projet)
              .getResultList();
    } finally {
      em.close();
    }
  }

  // Tâches réalisées avec dates réelles
  public void afficherTachesRealisees(Projet projet) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      List<Object[]> results = em.createQuery(
                      "SELECT t.id, t.nom, et.dateDebutReelle, et.dateFinReelle " +
                              "FROM EmployeTache et JOIN et.tache t " +
                              "WHERE t.projet = :proj", Object[].class)
              .setParameter("proj", projet)
              .getResultList();

      System.out.println("Projet : " + projet.getId() +
              "      Nom : " + projet.getNom() +
              "     Date début : " + sdf.format(projet.getDateDebut()));
      System.out.println("Liste des tâches:");
      System.out.printf("%-4s %-15s %-20s %-20s%n",
              "Num", "Nom", "Date Début Réelle", "Date Fin Réelle");

      for (Object[] row : results) {
        int id = (Integer) row[0];
        String nom = (String) row[1];
        String debut = sdf.format(row[2]);
        String fin = sdf.format(row[3]);
        System.out.printf("%-4d %-15s %-20s %-20s%n", id, nom, debut, fin);
      }
    } finally {
      em.close();
    }
  }
}