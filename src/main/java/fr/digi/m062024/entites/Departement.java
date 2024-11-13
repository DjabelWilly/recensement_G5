package fr.digi.m062024.entites;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "departements")
public class Departement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10, nullable = false)
    private Integer id;
    @Column(name = "code", length = 10, nullable = false)
    private String code;
    @Column(name = "nom", length = 50, nullable = false)
    private String nom;

    // RELATION

    // DEPARTEMENT -> REGION
    @ManyToOne
    @JoinColumn(name = "id_region")
    private Region region;

    // DEPARTEMENT -> COMMUNE
    @OneToMany(mappedBy = "departement")
    private Set<Commune> communes;


    public Departement(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public Departement() {

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Departement{");
        sb.append("code='").append(code).append('\'');
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", region=").append(region);
        sb.append(", communes=").append(communes);
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
     * Getter for code.
     *
     * @return the value of code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter for code.
     *
     * @param value the new value for code.
     */
    public void setCode(String value) {
        this.code = value;
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
     * Getter for region.
     *
     * @return the value of region.
     */
    public Region getRegion() {
        return region;
    }

    /**
     * Setter for region.
     *
     * @param value the new value for region.
     */
    public void setRegion(Region value) {
        this.region = value;
    }

    /**
     * Getter for communes.
     *
     * @return the value of communes.
     */
    public Set<Commune> getCommunes() {
        return communes;
    }

    /**
     * Setter for communes.
     *
     * @param value the new value for communes.
     */
    public void setCommunes(Set<Commune> value) {
        this.communes = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departement that = (Departement) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(nom, that.nom) && Objects.equals(region, that.region) && Objects.equals(communes, that.communes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, nom, region, communes);
    }
}
