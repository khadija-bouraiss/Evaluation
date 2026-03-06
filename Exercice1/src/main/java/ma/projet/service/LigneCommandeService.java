package ma.projet.service;

import ma.projet.classes.LigneCommandeProduit;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class LigneCommandeService implements IDao<LigneCommandeProduit> {

  @Override
  public boolean create(LigneCommandeProduit lcp) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(lcp);
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
  public boolean update(LigneCommandeProduit lcp) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(lcp);
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
  public boolean delete(LigneCommandeProduit lcp) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      LigneCommandeProduit managed = em.find(LigneCommandeProduit.class, lcp.getId());
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
  public LigneCommandeProduit findById(int id) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.find(LigneCommandeProduit.class, id);
    } finally {
      em.close();
    }
  }

  @Override
  public List<LigneCommandeProduit> findAll() {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery("SELECT lcp FROM LigneCommandeProduit lcp",
              LigneCommandeProduit.class).getResultList();
    } finally {
      em.close();
    }
  }
}