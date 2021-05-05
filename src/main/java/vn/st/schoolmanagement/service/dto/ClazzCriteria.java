package vn.st.schoolmanagement.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;

public class ClazzCriteria implements Serializable, Criteria {

    private LongFilter idClazz;
    private StringFilter nameClass;

    public ClazzCriteria() {

    }

    public ClazzCriteria(ClazzCriteria other) {
        this.idClazz = other.idClazz == null ? null : other.idClazz.copy();
        this.nameClass = other.nameClass == null ? null : other.nameClass.copy();
    }

    @Override
    public Criteria copy() {
        return new ClazzCriteria(this);
    }

    public LongFilter getIdClazz() {
        return idClazz;
    }

    public void setIdClazz(LongFilter idClazz) {
        this.idClazz = idClazz;
    }

    public StringFilter getNameClass() {
        return nameClass;
    }

    public void setNameClass(StringFilter nameClass) {
        this.nameClass = nameClass;
    }
}
