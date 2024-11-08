package fr.digi.m062024.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "communes")
public class Commune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10, nullable = false)
    private Integer id;
    @Column(name = "nom", length = 50, nullable = false)
    private String nom;
    @Column(name = "code_commune", length = 10, nullable = false)
    private Integer codeCommune;
    @Column(name = "code_departement", length = 10, nullable = false)
    private String codeDepartement;
    @Column(name = "population", nullable = false)
    private Integer population;

    public Commune(String nom, Integer codeCommune, String codeDepartement, Integer population) {
        this.nom = nom;
        this.codeCommune = codeCommune;
        this.codeDepartement = codeDepartement;
        this.population = population;
    }

    public Commune() {

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Commune{");
        sb.append("id=").append(id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", codeCommune=").append(codeCommune);
        sb.append(", codeDepartement='").append(codeDepartement).append('\'');
        sb.append(", population='").append(population).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * Getter for id.
     *
     * @return the value of id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for id.
     *
     * @param value the new value for id.
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Getter for nom.
     *
     * @return the value of nom.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter for nom.
     *
     * @param value the new value for nom.
     */
    public void setNom(String value) {
        this.nom = value;
    }

    /**
     * Getter for codeCommune.
     *
     * @return the value of codeCommune.
     */
    public Integer getCodeCommune() {
        return codeCommune;
    }

    /**
     * Setter for codeCommune.
     *
     * @param value the new value for codeCommune.
     */
    public void setCodeCommune(Integer value) {
        this.codeCommune = value;
    }

    /**
     * Getter for codeDepartement.
     *
     * @return the value of codeDepartement.
     */
    public String getCodeDepartement() {
        return codeDepartement;
    }

    /**
     * Setter for codeDepartement.
     *
     * @param value the new value for codeDepartement.
     */
    public void setCodeDepartement(String value) {
        this.codeDepartement = value;
    }

    /**
     * Getter for population.
     *
     * @return the value of population.
     */
    public Integer getPopulation() {
        return population;
    }

    /**
     * Setter for population.
     *
     * @param value the new value for population.
     */
    public void setPopulation(Integer value) {
        this.population = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commune commune = (Commune) o;
        return Objects.equals(id, commune.id) && Objects.equals(nom, commune.nom) && Objects.equals(codeCommune, commune.codeCommune) && Objects.equals(codeDepartement, commune.codeDepartement) && Objects.equals(population, commune.population);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, codeCommune, codeDepartement, population);
    }
}