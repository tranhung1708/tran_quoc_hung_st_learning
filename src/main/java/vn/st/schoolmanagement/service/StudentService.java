package vn.st.schoolmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.st.schoolmanagement.service.dto.StudentDTO;

import java.util.Optional;

public interface StudentService {

    StudentDTO save(StudentDTO studentDTO);

    Page<StudentDTO> findAll(Pageable pageable);

    Optional<StudentDTO> findOne(Long id);

    void delete(Long id);
}
