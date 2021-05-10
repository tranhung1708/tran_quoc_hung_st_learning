package vn.st.schoolmanagement.domain;

import javax.persistence.*;

@Entity
@Table(name = "detail_subject")
public class DetailSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "mouth")
    private double mouth;

    @Column(name = "fifteen_minutes")
    private double fifteenMinutes;

    @Column(name = "one_lesson")
    private double oneLesson;

    @Column(name = "finish_the_subject")
    private double finishTheSubject;

    @ManyToOne
    @JoinColumn(name = "id_subject")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMouth() {
        return mouth;
    }

    public void setMouth(double mouth) {
        this.mouth = mouth;
    }

    public double getFifteenMinutes() {
        return fifteenMinutes;
    }

    public void setFifteenMinutes(double fifteenMinutes) {
        this.fifteenMinutes = fifteenMinutes;
    }

    public double getOneLesson() {
        return oneLesson;
    }

    public void setOneLesson(double oneLesson) {
        this.oneLesson = oneLesson;
    }

    public double getFinishTheSubject() {
        return finishTheSubject;
    }

    public void setFinishTheSubject(double finishTheSubject) {
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
}
