package vn.st.schoolmanagement.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class StudentCriteria implements Serializable, Criteria {

    private LongFilter id;
    private StringFilter nameStudent;
    private StringFilter birthday;
    private StringFilter address;
    private StringFilter email;
    private StringFilter gender;
    private DoubleFilter detailSubjectFinishTheSubject;
    private StringFilter subjectName;


    public StudentCriteria() {
    }

    public StudentCriteria(StudentCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nameStudent = other.nameStudent == null ? null : other.nameStudent.copy();
        this.birthday = other.birthday == null ? null : other.birthday.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.gender = other.gender == null ? null : other.gender.copy();
        this.detailSubjectFinishTheSubject = other.detailSubjectFinishTheSubject == null ? null : other.detailSubjectFinishTheSubject.copy();
        this.subjectName = other.subjectName == null ? null : other.subjectName.copy();
    }

    @Override
    public Criteria copy() {
        return new StudentCriteria(this);
    }

}
