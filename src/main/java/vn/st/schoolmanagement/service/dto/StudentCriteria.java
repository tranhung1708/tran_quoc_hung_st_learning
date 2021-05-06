package vn.st.schoolmanagement.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;

public class StudentCriteria implements Serializable, Criteria {

    private LongFilter id;
    private StringFilter nameStudent;
    private StringFilter birthday;
    private StringFilter address;
    private StringFilter gender;


    public StudentCriteria() {}

    public StudentCriteria(StudentCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nameStudent = other.nameStudent == null ? null : other.nameStudent.copy();
        this.birthday = other.birthday == null ? null : other.birthday.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.gender = other.gender == null ? null : other.gender.copy();
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(StringFilter nameStudent) {
        this.nameStudent = nameStudent;
    }

    public StringFilter getBirthday() {
        return birthday;
    }

    public void setBirthday(StringFilter birthday) {
        this.birthday = birthday;
    }

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getGender() {
        return gender;
    }

    public void setGender(StringFilter gender) {
        this.gender = gender;
    }

    @Override
    public Criteria copy() {
        return new StudentCriteria(this);
    }

}
