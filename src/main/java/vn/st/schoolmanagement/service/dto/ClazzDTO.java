package vn.st.schoolmanagement.service.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class ClazzDTO implements Serializable {
    private Long idClazz;

    private String nameClass;

    private Long idSchool;

    private List<StudentDTO> studentDTOS;
}
