package ma.projet.service;

import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class MariageService implements IDao<Mariage> {

  @Override
  public boolean create(Mariage m) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(m);
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
  public boolean update(Mariage m) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(m);
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
  public boolean delete(Mariage m) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      Mariage managed = em.find(Mariage.class, m.getId());
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
  public Mariage findById(int id) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.find(Mariage.class, id);
    } finally {
      em.close();
    }
  }

  @Override
  public List<Mariage> findAll() {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery("SELECT m FROM Mariage m", Mariage.class).getResultList();
    } finally {
      em.close();
    }
  }
}