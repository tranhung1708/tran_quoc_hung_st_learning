package vn.st.schoolmanagement.service.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class StudentDTO implements Serializable {

    private Long id;

    private String nameStudent;

    private String birthday;

    private String address;

    private String gender;

    private Long idClazz;

}
