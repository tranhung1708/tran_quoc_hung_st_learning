package vn.st.schoolmanagement.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.st.schoolmanagement.domain.Clazz;
import vn.st.schoolmanagement.domain.DetailSubject;
import vn.st.schoolmanagement.service.dto.ClazzDTO;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;


/**
 * Mapper for the entity {@link Clazz} and its DTO {@link ClazzDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DetailSubjectMapper extends EntityMapper<DetailSubjectDTO, DetailSubject>{

//    @Mapping(target = "schools.idSchool", source = "idSchool")
//    DetailSubject toEntity(DetailSubjectDTO dto);
//
//    @Mapping(target = "idSchool", source = "schools.idSchool")
//    DetailSubjectDTO toDto(DetailSubject entity);

    default DetailSubject fromId(Long id) {
        if (id == null) {
            return null;
        }
        DetailSubject detailSubject = new DetailSubject();
        detailSubject.setId(id);
        return detailSubject;
    }
}
