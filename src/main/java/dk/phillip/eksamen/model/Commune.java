package dk.phillip.eksamen.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "commune")
public class Commune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String communeCode;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commune")
    private Set<Parish> parishSet = new HashSet<>();

    public Commune() {
    }

    public Commune(long id) {
        this.id = id;
    }

    //    ----------------Getters & Setters ----------------------------


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommuneCode() {
        return communeCode;
    }

    public void setCommuneCode(String communeCode) {
        this.communeCode = communeCode;
    }

    public Set<Parish> getParishSet() {
        return parishSet;
    }

    public void setParishSet(Set<Parish> parishSet) {
        this.parishSet = parishSet;
    }
}
