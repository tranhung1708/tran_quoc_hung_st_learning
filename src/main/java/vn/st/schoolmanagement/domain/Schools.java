package vn.st.schoolmanagement.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "schools")
public class Schools {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSchool;

    private String nameSchools;


    @OneToMany(mappedBy = "schools")
    private Set<Clazz> clazzes;

    public long getIdSchool() {
        return idSchool;
    }

    public void setIdSchool(long idSchool) {
        this.idSchool = idSchool;
    }

    public String getNameSchools() {
        return nameSchools;
    }

    public void setNameSchools(String nameSchools) {
        this.nameSchools = nameSchools;
    }

    public Set<Clazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(Set<Clazz> clazzes) {
        this.clazzes = clazzes;
    }
}
