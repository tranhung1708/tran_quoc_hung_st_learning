package vn.st.schoolmanagement.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clazz")
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idClazz;

    @Column(name = "name")
    private String nameClass;

    @OneToMany(mappedBy = "clazz")
    private Set<Student> studentSet;


    @ManyToOne
    @JoinColumn(name = "id_school")
    private Schools schools;


    public long getIdClazz() {
        return idClazz;
    }

    public void setIdClazz(long idClazz) {
        this.idClazz = idClazz;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public Schools getSchools() {
        return schools;
    }

    public void setSchools(Schools schools) {
        this.schools = schools;
    }
}
