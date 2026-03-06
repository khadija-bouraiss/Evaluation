package ma.projet.service;

import ma.projet.classes.EmployeTache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeTacheService implements IDao<EmployeTache> {

  @Override
  public boolean create(EmployeTache et) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(et);
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
  public boolean update(EmployeTache et) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(et);
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
  public boolean delete(EmployeTache et) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      EmployeTache managed = em.find(EmployeTache.class, et.getId());
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
  public EmployeTache findById(int id) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.find(EmployeTache.class, id);
    } finally {
      em.close();
    }
  }

  @Override
  public List<EmployeTache> findAll() {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery("SELECT et FROM EmployeTache et",
              EmployeTache.class).getResultList();
    } finally {
      em.close();
    }
  }
}