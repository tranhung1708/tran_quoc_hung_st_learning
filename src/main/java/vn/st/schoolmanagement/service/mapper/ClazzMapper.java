package vn.st.schoolmanagement.service.mapper;

import org.mapstruct.*;
import vn.st.schoolmanagement.domain.Clazz;
import vn.st.schoolmanagement.service.dto.ClazzDTO;


/**
 * Mapper for the entity {@link Clazz} and its DTO {@link ClazzDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClazzMapper extends EntityMapper<ClazzDTO, Clazz>{

    @Mapping(target = "schools.idSchool", source = "idSchool")
    Clazz toEntity(ClazzDTO dto);

    @Mapping(target = "idSchool", source = "schools.idSchool")
    @Mapping(target = "studentDTOS", source = "studentSet")
    ClazzDTO toDto(Clazz entity);

    default Clazz fromId(Long id) {
        if (id == null) {
            return null;
        }
        Clazz clazz = new Clazz();
        clazz.setIdClazz(id);
        return clazz;
    }
}
