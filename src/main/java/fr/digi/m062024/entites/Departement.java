package fr.digi.m062024.entites;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Departement")
public class Departement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "code")
    private String code;
    @Column(name = "nom")
    private String nom;
    @Column(name = "code_region")
    private Integer codeReg;

    public Departement() {
    }

    public Departement(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getCodeReg() {
        return this.codeReg;
    }

    public void setCodeReg(Integer codeReg) {
        this.codeReg = codeReg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Departement{");
        sb.append("id=").append(this.id);
        sb.append(", code='").append(this.code).append('\'');
        sb.append(", nom='").append(this.nom).append('\'');
        sb.append(", codeReg=").append(this.codeReg);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Departement that = (Departement) o;
            return Objects.equals(this.id, that.id) && Objects.equals(this.code, that.code) && Objects.equals(this.nom, that.nom) && Objects.equals(this.codeReg, that.codeReg);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.code, this.nom, this.codeReg});
    }
}
