package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
        import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HommeService implements IDao<Homme> {

  @Override
  public boolean create(Homme h) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(h);
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
  public boolean update(Homme h) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(h);
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
  public boolean delete(Homme h) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      Homme managed = em.find(Homme.class, h.getId());
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
  public Homme findById(int id) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.find(Homme.class, id);
    } finally {
      em.close();
    }
  }

  @Override
  public List<Homme> findAll() {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery("SELECT h FROM Homme h", Homme.class).getResultList();
    } finally {
      em.close();
    }
  }

  // Épouses d'un homme entre deux dates
  public List<Femme> findEpousesEntreDates(Homme homme, Date dateDebut, Date dateFin) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      return em.createQuery(
                      "SELECT m.femme FROM Mariage m WHERE m.homme = :h " +
                              "AND m.dateDebut BETWEEN :dateDebut AND :dateFin", Femme.class)
              .setParameter("h", homme)
              .setParameter("dateDebut", dateDebut)
              .setParameter("dateFin", dateFin)
              .getResultList();
    } finally {
      em.close();
    }
  }

  // Hommes mariés à 4 femmes entre deux dates (Criteria API)
  public List<Homme> findHommesMaries4FemmesEntreDates(Date dateDebut, Date dateFin) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Homme> cq = cb.createQuery(Homme.class);
      Root<Homme> homme = cq.from(Homme.class);
      Join<Object, Object> mariage = homme.join("mariages");

      cq.select(homme)
              .where(cb.between(mariage.get("dateDebut"), dateDebut, dateFin))
              .groupBy(homme)
              .having(cb.equal(cb.count(mariage), 4));

      return em.createQuery(cq).getResultList();
    } finally {
      em.close();
    }
  }

  // Mariages d'un homme avec tous les détails
  public void afficherMariagesHomme(Homme homme) {
    EntityManager em = HibernateUtil.getEntityManager();
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

      List<Mariage> mariages = em.createQuery(
                      "SELECT m FROM Mariage m WHERE m.homme = :h ORDER BY m.dateDebut",
                      Mariage.class)
              .setParameter("h", homme)
              .getResultList();

      System.out.println("Nom : " + homme.getNom() + " " + homme.getPrenom());

      System.out.println("\nMariages En Cours :");
      int i = 1;
      for (Mariage m : mariages) {
        if (m.getDateFin() == null) {
          System.out.println(i++ + ". Femme : " +
                  m.getFemme().getNom() + " " + m.getFemme().getPrenom() +
                  "   Date Début : " + sdf.format(m.getDateDebut()) +
                  "    Nbr Enfants : " + m.getNbrEnfant());
        }
      }

      System.out.println("\nMariages échoués :");
      i = 1;
      for (Mariage m : mariages) {
        if (m.getDateFin() != null) {
          System.out.println(i++ + ". Femme : " +
                  m.getFemme().getNom() + " " + m.getFemme().getPrenom() +
                  "   Date Début : " + sdf.format(m.getDateDebut()) +
                  "\n   Date Fin : " + sdf.format(m.getDateFin()) +
                  "    Nbr Enfants : " + m.getNbrEnfant());
        }
      }
    } finally {
      em.close();
    }
  }
}