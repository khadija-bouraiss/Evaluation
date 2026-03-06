package ma.projet.service;

import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class TacheService implements IDao<Tache> {

  @Override
  public boolean create(Tache t) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(t);
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
  public boolean update(Tache t) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(t);
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
  public boolean delete(Tache t) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      Tache managed = em.find(Tache.class, t.getId());
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
  public Tache findById(int id) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.find(Tache.class, id);
    } finally {
      em.close();
    }
  }

  @Override
  public List<Tache> findAll() {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery("SELECT t FROM Tache t", Tache.class).getResultList();
    } finally {
      em.close();
    }
  }

  // Tâches dont prix > 1000 DH via NamedQuery
  public List<Tache> findTachesCheres() {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createNamedQuery("Tache.findByPrixSuperieur1000", Tache.class)
              .getResultList();
    } finally {
      em.close();
    }
  }

  // Tâches réalisées entre deux dates
  public List<Tache> findTachesEntreDates(Date dateDebut, Date dateFin) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery(
                      "SELECT DISTINCT et.tache FROM EmployeTache et " +
                              "WHERE et.dateDebutReelle BETWEEN :dateDebut AND :dateFin", Tache.class)
              .setParameter("dateDebut", dateDebut)
              .setParameter("dateFin", dateFin)
              .getResultList();
    } finally {
      em.close();
    }
  }
}