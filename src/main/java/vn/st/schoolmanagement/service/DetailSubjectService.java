package vn.st.schoolmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.st.schoolmanagement.domain.DetailSubject;
import vn.st.schoolmanagement.domain.Student;
import vn.st.schoolmanagement.domain.Subject;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;

import java.util.List;
import java.util.Optional;

public interface DetailSubjectService {
    DetailSubjectDTO save(DetailSubjectDTO studentDTO);

    Page<DetailSubjectDTO> findAll(Pageable pageable);

    Optional<DetailSubjectDTO> findOne(Long id);

    void delete(Long id);



}
