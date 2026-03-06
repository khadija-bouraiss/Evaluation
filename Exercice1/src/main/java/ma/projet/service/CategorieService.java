package ma.projet.service;

import ma.projet.classes.Categorie;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CategorieService implements IDao<Categorie> {

  @Override
  public boolean create(Categorie c) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(c);
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
  public boolean update(Categorie c) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(c);
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
  public boolean delete(Categorie c) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      Categorie managed = em.find(Categorie.class, c.getId());
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
  public Categorie findById(int id) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.find(Categorie.class, id);
    } finally {
      em.close();
    }
  }

  @Override
  public List<Categorie> findAll() {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery("SELECT c FROM Categorie c", Categorie.class).getResultList();
    } finally {
      em.close();
    }
  }
}