package vn.st.schoolmanagement.service.dto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StudentDTO implements Serializable {

    private Long id;

    private String nameStudent;

    private String birthday;

    private String address;

    private String gender;

    private Long idClazz;

    private List<DetailSubjectDTO> detailSubjects;

}
