package vn.st.schoolmanagement.service.dto;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class DetailSubjectDTO implements Serializable {

    private Long id;

    private double mouth;

    private double fifteenMinutes;

    private double oneLesson;

    private double finishTheSubject;

    private Long idSubject;

    private Long idStudent;

    private SubjectDTO subject;

    private List<SubjectDTO> subjects;

    public double avgSubject() {
        return (this.getMouth() + this.getFifteenMinutes() + this.getOneLesson() + this.getFinishTheSubject()) / 4;
    }

    public String checkAvgStudent() {
        if (this.avgSubject() >= 7.0) {
            return "Giỏi";
        } else if (this.avgSubject() >= 5.5) {
            return "Khá";
        }
        return "Trung Bình";
    }

}
