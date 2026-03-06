package ma.projet.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

  private static EntityManagerFactory emf;

  static {
    try {
      emf = Persistence.createEntityManagerFactory("Exercice3");
    } catch (Exception e) {
      e.printStackTrace();
      throw new ExceptionInInitializerError(e);
    }
  }

  public static EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public static void close() {
    if (emf != null && emf.isOpen()) {
      emf.close();
    }
  }
}