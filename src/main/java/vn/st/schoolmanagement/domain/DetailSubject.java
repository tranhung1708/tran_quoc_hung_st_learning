package vn.st.schoolmanagement.domain;

import javax.persistence.*;

@Entity
@Table(name = "detail_subject")
public class DetailSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "mouth")
    private String mouth;

    @Column(name = "fifteenMinutes")
    private String fifteenMinutes;

    @Column(name = "oneLesson")
    private String oneLesson;

    @Column(name = "finishTheSubject")
    private String finishTheSubject;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public String getMouth() {
        return mouth;
    }

    public void setMouth(String mouth) {
        this.mouth = mouth;
    }

    public String getFifteenMinutes() {
        return fifteenMinutes;
    }

    public void setFifteenMinutes(String fifteenMinutes) {
        this.fifteenMinutes = fifteenMinutes;
    }

    public String getOneLesson() {
        return oneLesson;
    }

    public void setOneLesson(String oneLesson) {
        this.oneLesson = oneLesson;
    }

    public String getFinishTheSubject() {
        return finishTheSubject;
    }

    public void setFinishTheSubject(String finishTheSubject) {
        this.finishTheSubject = finishTheSubject;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
