package fr.digi.m062024.entites;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "regions")
public class Region implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10, nullable = false)
    private Integer id;
    @Column(name = "nom", length = 50, nullable = false)
    private String nom;
    @Column(name = "code", length = 10, nullable = false)
    private Integer code;

    // RELATION

    // REGION -> DEPARTEMENT
    @OneToMany(mappedBy = "region")
    private Set<Departement> departements;

    public Region(String nom, Integer code) {
        this.nom = nom;
        this.code = code;
    }

    public Region() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Region{");
        sb.append("id=").append(id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", code=").append(code);
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
     * Getter for code.
     *
     * @return the value of code.
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Setter for code.
     *
     * @param value the new value for code.
     */
    public void setCode(Integer value) {
        this.code = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(nom, region.nom) && Objects.equals(code, region.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, code);
    }
}
