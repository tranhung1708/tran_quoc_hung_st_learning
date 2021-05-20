package vn.st.schoolmanagement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.st.schoolmanagement.domain.DetailSubject;
import vn.st.schoolmanagement.repository.DetailSubjectRepository;
import vn.st.schoolmanagement.service.DetailSubjectService;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;
import vn.st.schoolmanagement.service.mapper.DetailSubjectMapper;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetailSubjectServiceImpl implements DetailSubjectService {

    private final Logger log = LoggerFactory.getLogger(DetailSubjectServiceImpl.class);

    private final DetailSubjectRepository detailSubjectRepository;

    private final DetailSubjectMapper detailSubjectMapper;

    public DetailSubjectServiceImpl(DetailSubjectRepository detailSubjectRepository, DetailSubjectMapper detailSubjectMapper) {
        this.detailSubjectRepository = detailSubjectRepository;
        this.detailSubjectMapper = detailSubjectMapper;
    }

    @Override
    public DetailSubjectDTO save(DetailSubjectDTO subjectDTO) {
        log.debug("Request to save detailSubject : {}", subjectDTO);
        DetailSubject detailSubject = detailSubjectMapper.toEntity(subjectDTO);
        detailSubject = detailSubjectRepository.save(detailSubject);
        return detailSubjectMapper.toDto(detailSubject);
    }

    @Override
    public Page<DetailSubjectDTO> findAll(Pageable pageable) {
        log.debug("Request to get all detailSubject");
        return detailSubjectRepository.findAll(pageable)
            .map(detailSubjectMapper::toDto);
    }


    @Override
    public Optional<DetailSubjectDTO> findOne(Long id) {
        log.debug("Request to get detailSubject : {}", id);
        return detailSubjectRepository.findById(id)
            .map(detailSubjectMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        detailSubjectRepository.deleteById(id);
    }

    @Override
    public List<DetailSubjectDTO> saveAll(List<DetailSubjectDTO> subjectDTOS) {
        log.debug("Request to save detailSubject : {}", subjectDTOS);
        List<DetailSubject> detailSubject = detailSubjectMapper.toEntity(subjectDTOS);
        detailSubject = detailSubjectRepository.saveAll(detailSubject);
        return detailSubjectMapper.toDto(detailSubject);
    }

    @Override
    public List<DetailSubjectDTO> findAllByStudentId(Long studentId) {
        return detailSubjectMapper.toDto(detailSubjectRepository.findAllByStudentId(studentId));
    }


}
