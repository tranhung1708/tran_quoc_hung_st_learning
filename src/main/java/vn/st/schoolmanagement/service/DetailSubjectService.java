package vn.st.schoolmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;

import javax.persistence.criteria.Expression;
import java.util.List;
import java.util.Optional;

public interface DetailSubjectService {

    DetailSubjectDTO save(DetailSubjectDTO subjectDTO);

    Page<DetailSubjectDTO> findAll(Pageable pageable);



    Optional<DetailSubjectDTO> findOne(Long id);

    void delete(Long id);

    List<DetailSubjectDTO> saveAll(List<DetailSubjectDTO> subjectDTOS);

}
