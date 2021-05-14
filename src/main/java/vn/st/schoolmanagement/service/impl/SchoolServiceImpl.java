package vn.st.schoolmanagement.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.st.schoolmanagement.repository.SchoolRepository;
import vn.st.schoolmanagement.service.SchoolService;
import vn.st.schoolmanagement.service.dto.SchoolDTO;
import vn.st.schoolmanagement.service.mapper.SchoolMapper;

@Service
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolServiceImpl(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    @Override
    public Page<SchoolDTO> findAll(Pageable pageable) {
        return schoolRepository.findAll(pageable)
            .map(schoolMapper::toDto);
    }
}
