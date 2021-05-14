package vn.st.schoolmanagement.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.st.schoolmanagement.domain.Clazz;
import vn.st.schoolmanagement.domain.Student;
import vn.st.schoolmanagement.service.dto.ClazzDTO;
import vn.st.schoolmanagement.service.dto.StudentDTO;


/**
 * Mapper for the entity {@link Student} and its DTO {@link StudentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StudentMapper extends EntityMapper<StudentDTO, Student>{

    @Mapping(target = "clazz.idClazz", source = "idClazz")
    Student toEntity(StudentDTO dto);

    @Mapping(target = "idClazz", source = "clazz.idClazz")
    StudentDTO toDto(Student entity);

    default Student fromId(Long id) {
        if (id == null) {
            return null;
        }
        Student student = new Student();
        student.setId(id);
        return student;
    }
}
