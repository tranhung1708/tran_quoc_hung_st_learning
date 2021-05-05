package vn.st.schoolmanagement.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "student")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameStudent;
    private String birthday;
    private String address;
    private String gender;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "idClazz")
    private Clazz clazz;



    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<DetailSubject> detailSubjects;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Set<DetailSubject> getDetailSubjects() {
        return detailSubjects;
    }

    public void setDetailSubjects(Set<DetailSubject> detailSubjects) {
        this.detailSubjects = detailSubjects;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}
