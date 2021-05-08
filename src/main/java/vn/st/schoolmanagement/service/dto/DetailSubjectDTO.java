package vn.st.schoolmanagement.service.dto;


import java.io.Serializable;

public class DetailSubjectDTO implements Serializable {

    private Long id;

    private String mouth;

    private String fifteenMinutes;

    private String oneLesson;

    private String finishTheSubject;

    private Long idSubject;

    private Long idStudent;

    public DetailSubjectDTO(Long id, String mouth, String fifteenMinutes, String oneLesson, String finishTheSubject, Long idSubject, Long idStudent) {
        this.id = id;
        this.mouth = mouth;
        this.fifteenMinutes = fifteenMinutes;
        this.oneLesson = oneLesson;
        this.finishTheSubject = finishTheSubject;
        this.idSubject = idSubject;
        this.idStudent = idStudent;
    }

    public DetailSubjectDTO( String mouth, String fifteenMinutes, String oneLesson, String finishTheSubject, Long idSubject, Long idStudent) {
        this.mouth = mouth;
        this.fifteenMinutes = fifteenMinutes;
        this.oneLesson = oneLesson;
        this.finishTheSubject = finishTheSubject;
        this.idSubject = idSubject;
        this.idStudent = idStudent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Long idSubject) {
        this.idSubject = idSubject;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }
}
