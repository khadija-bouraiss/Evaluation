package ma.projet.service;

import ma.projet.classes.Employe;
import ma.projet.classes.Tache;
import ma.projet.classes.Projet;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeService implements IDao<Employe> {

  @Override
  public boolean create(Employe e) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(e);
      em.getTransaction().commit();
      return true;
    } catch (Exception ex) {
      em.getTransaction().rollback();
      ex.printStackTrace();
      return false;
    } finally {
      em.close();
    }
  }

  @Override
  public boolean update(Employe e) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(e);
      em.getTransaction().commit();
      return true;
    } catch (Exception ex) {
      em.getTransaction().rollback();
      ex.printStackTrace();
      return false;
    } finally {
      em.close();
    }
  }

  @Override
  public boolean delete(Employe e) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      Employe managed = em.find(Employe.class, e.getId());
      if (managed != null) em.remove(managed);
      em.getTransaction().commit();
      return true;
    } catch (Exception ex) {
      em.getTransaction().rollback();
      ex.printStackTrace();
      return false;
    } finally {
      em.close();
    }
  }

  @Override
  public Employe findById(int id) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.find(Employe.class, id);
    } finally {
      em.close();
    }
  }

  @Override
  public List<Employe> findAll() {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery("SELECT e FROM Employe e", Employe.class).getResultList();
    } finally {
      em.close();
    }
  }

  // Tâches réalisées par un employé
  public List<Tache> findTachesByEmploye(Employe employe) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery(
                      "SELECT et.tache FROM EmployeTache et WHERE et.employe = :emp", Tache.class)
              .setParameter("emp", employe)
              .getResultList();
    } finally {
      em.close();
    }
  }

  // Projets gérés par un employé (chef de projet)
  public List<Projet> findProjetsByEmploye(Employe employe) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery(
                      "SELECT p FROM Projet p WHERE p.chefProjet = :emp", Projet.class)
              .setParameter("emp", employe)
              .getResultList();
    } finally {
      em.close();
    }
  }
}