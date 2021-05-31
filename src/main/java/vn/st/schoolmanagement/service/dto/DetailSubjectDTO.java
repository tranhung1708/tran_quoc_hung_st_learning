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


    public double checkLearning() {
        if (avgSubject() >= 5.5 && avgSubject() <= 7.0) {
            return 2;
        } else if (avgSubject() < 5.5) {
            return 3;
        }
        return 1;
    }

    public String formatFileText() {
        String.format("%7s | %7s | %7s | %7s | %7s | %7s" , "{Môn Học}" , "{Miệng}","{15 Phút}", "{1 Tiết}" , "{Kết thúc môn}", "{Trung Bình Môn}");
        return String.format("%7s | %5s | %7s | %6s | %10s |%10s",
            subject.getName(),
            this.getMouth(),
            this.getFifteenMinutes(),
            this.getOneLesson(),
            this.getFinishTheSubject(),
            this.avgSubject());
    }
}
