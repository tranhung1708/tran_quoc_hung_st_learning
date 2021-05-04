package vn.st.schoolmanagement.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "schools")
public class Schools implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nameSchools;

    @ManyToOne
    @JoinColumn(name = "clazz_id", referencedColumnName = "id")
    private Clazz clazz;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameSchools() {
        return nameSchools;
    }

    public void setNameSchools(String nameSchools) {
        this.nameSchools = nameSchools;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}
