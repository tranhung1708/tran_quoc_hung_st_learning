package vn.st.schoolmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.st.schoolmanagement.service.dto.SchoolDTO;

public interface SchoolService {
    Page<SchoolDTO> findAll(Pageable pageable);
}
