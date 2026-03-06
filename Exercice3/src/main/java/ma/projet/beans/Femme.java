package ma.projet.beans;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "femme")
@NamedQuery(
        name = "Femme.marieeDeuxFois",
        query = "SELECT f FROM Femme f WHERE " +
                "(SELECT COUNT(m) FROM Mariage m WHERE m.femme = f) >= 2"
)
@NamedNativeQuery(
        name = "Femme.nbrEnfantsEntreDates",
        query = "SELECT SUM(m.nbrEnfant) FROM mariage m " +
                "WHERE m.femme_id = :femmeId " +
                "AND m.dateDebut BETWEEN :dateDebut AND :dateFin"
)
public class Femme extends Personne {

  @OneToMany(mappedBy = "femme", cascade = CascadeType.ALL)
  private List<Mariage> mariages;

  public Femme() {}
  public Femme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
    super(nom, prenom, telephone, adresse, dateNaissance);
  }

  public List<Mariage> getMariages() { return mariages; }
  public void setMariages(List<Mariage> mariages) { this.mariages = mariages; }
}