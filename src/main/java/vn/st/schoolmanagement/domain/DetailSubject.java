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

    @Column(name = "fifteen_minutes")
    private String fifteenMinutes;

    @Column(name = "one_lesson")
    private String oneLesson;

    @Column(name = "finish_the_subject")
    private String finishTheSubject;

    @ManyToOne
    @JoinColumn(name = "id_subject")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "id_student")
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
