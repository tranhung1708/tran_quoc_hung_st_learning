package vn.st.schoolmanagement.service.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class SchoolDTO implements Serializable {

    private Long idSchool;

    private String nameSchools;

}
