package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
        import java.util.Date;
import java.util.List;

public class FemmeService implements IDao<Femme> {

  @Override
  public boolean create(Femme f) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(f);
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
  public boolean update(Femme f) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(f);
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
  public boolean delete(Femme f) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      Femme managed = em.find(Femme.class, f.getId());
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
  public Femme findById(int id) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.find(Femme.class, id);
    } finally {
      em.close();
    }
  }

  @Override
  public List<Femme> findAll() {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery("SELECT f FROM Femme f", Femme.class).getResultList();
    } finally {
      em.close();
    }
  }

  // Femme la plus âgée
  public Femme findFemmePlusAgee() {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery(
                      "SELECT f FROM Femme f ORDER BY f.dateNaissance ASC", Femme.class)
              .setMaxResults(1)
              .getSingleResult();
    } finally {
      em.close();
    }
  }

  // Nombre d'enfants d'une femme entre deux dates (requête native nommée)
  public int getNbrEnfantsEntreDates(Femme femme, Date dateDebut, Date dateFin) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      Object result = em.createNamedQuery("Femme.nbrEnfantsEntreDates")
              .setParameter("femmeId", femme.getId())
              .setParameter("dateDebut", dateDebut)
              .setParameter("dateFin", dateFin)
              .getSingleResult();
      return result == null ? 0 : ((Number) result).intValue();
    } finally {
      em.close();
    }
  }

  // Femmes mariées au moins deux fois (requête nommée)
  public List<Femme> findFemmesMarieesDeuxFois() {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createNamedQuery("Femme.marieeDeuxFois", Femme.class)
              .getResultList();
    } finally {
      em.close();
    }
  }
}