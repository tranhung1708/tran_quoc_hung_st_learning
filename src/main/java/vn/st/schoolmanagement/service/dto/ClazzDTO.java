package vn.st.schoolmanagement.service.dto;

import java.io.Serializable;

public class ClazzDTO implements Serializable {
    private Long idClazz;

    private String nameClass;

    private Long idSchool;

    public Long getIdSchool() {
        return idSchool;
    }

    public void setIdSchool(Long idSchool) {
        this.idSchool = idSchool;
    }

    public Long getIdClazz() {
        return idClazz;
    }

    public void setIdClazz(Long idClazz) {
        this.idClazz = idClazz;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }
}
