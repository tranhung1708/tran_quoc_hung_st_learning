package vn.st.schoolmanagement.service.dto;

import java.io.Serializable;

public class SchoolDTO implements Serializable {

    private Long idSchool;

    private String nameSchool;

    public Long getIdSchool() {
        return idSchool;
    }

    public void setIdSchool(Long idSchool) {
        this.idSchool = idSchool;
    }

    public String getNameSchool() {
        return nameSchool;
    }

    public void setNameSchool(String nameSchool) {
        this.nameSchool = nameSchool;
    }
}
