package vn.st.schoolmanagement.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "clazz")
public class Clazz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String nameClass;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @OneToMany(mappedBy = "clazz" , cascade = CascadeType.ALL)
    private Set<Schools> schoolsSet;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Set<Schools> getSchoolsSet() {
        return schoolsSet;
    }

    public void setSchoolsSet(Set<Schools> schoolsSet) {
        this.schoolsSet = schoolsSet;
    }
}
