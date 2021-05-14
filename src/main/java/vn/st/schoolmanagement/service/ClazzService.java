package vn.st.schoolmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.st.schoolmanagement.service.dto.ClazzDTO;

import java.util.List;
import java.util.Optional;

public interface ClazzService {
    ClazzDTO save(ClazzDTO clazzDTO);

    Page<ClazzDTO> findAll(Pageable pageable);

    Optional<ClazzDTO> findOne(Long id);

    void delete(Long id);

    List<ClazzDTO> findAllByShoolId(Long schoolId);
}
