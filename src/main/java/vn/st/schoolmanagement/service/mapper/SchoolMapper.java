package vn.st.schoolmanagement.service.mapper;

import org.mapstruct.Mapper;
import vn.st.schoolmanagement.domain.Schools;
import vn.st.schoolmanagement.service.dto.SchoolDTO;


/**
 * Mapper for the entity {@link Schools} and its DTO {@link SchoolDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SchoolMapper extends EntityMapper<SchoolDTO, Schools>{

    default Schools fromId(Long id) {
        if (id == null) {
            return null;
        }
        Schools schools = new Schools();
        schools.setIdSchool(id);
        return schools;
    }
}
