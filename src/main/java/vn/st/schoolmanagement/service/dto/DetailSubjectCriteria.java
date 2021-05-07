package vn.st.schoolmanagement.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class DetailSubjectCriteria implements Serializable, Criteria {

    private LongFilter id;
    private StringFilter mouth;
    private StringFilter fifteenMinutes;
    private StringFilter oneLesson;
    private StringFilter finishTheSubject;
    private LongFilter idSubject;
    private LongFilter idStudent;
    private StringFilter subjectName;
    private StringFilter studentName;
    private StringFilter studentBirthday;
    private StringFilter studentGender;
    private StringFilter clazzName;

    public DetailSubjectCriteria() {}

    public DetailSubjectCriteria(DetailSubjectCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.mouth = other.mouth == null ? null : other.mouth.copy();
        this.fifteenMinutes = other.fifteenMinutes == null ? null : other.fifteenMinutes.copy();
        this.oneLesson = other.oneLesson == null ? null : other.oneLesson.copy();
        this.finishTheSubject = other.finishTheSubject == null ? null : other.finishTheSubject.copy();
        this.idSubject = other.idSubject == null ? null : other.idSubject.copy();
        this.idStudent = other.idStudent == null ? null : other.idStudent.copy();
        this.subjectName = other.subjectName == null ? null : other.subjectName.copy();
        this.studentName = other.studentName == null ? null : other.studentName.copy();
        this.studentBirthday = other.studentBirthday == null ? null : other.studentBirthday.copy();
        this.studentGender = other.studentGender == null ? null : other.studentGender.copy();
        this.clazzName = other.clazzName == null ? null : other.clazzName.copy();
    }

    @Override
    public Criteria copy() {
        return new DetailSubjectCriteria(this);
    }

}
