package vn.st.schoolmanagement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.st.schoolmanagement.domain.Clazz;
import vn.st.schoolmanagement.repository.ClazzRepository;
import vn.st.schoolmanagement.service.ClazzService;
import vn.st.schoolmanagement.service.dto.ClazzDTO;
import vn.st.schoolmanagement.service.mapper.ClazzMapper;

import java.util.Optional;

@Service
@Transactional
public class ClazzServiceImpl implements ClazzService {

    private final Logger log = LoggerFactory.getLogger(ClazzServiceImpl.class);

    private final ClazzRepository clazzRepository;

    private final ClazzMapper clazzMapper;


    public ClazzServiceImpl(ClazzRepository clazzRepository, ClazzMapper clazzMapper) {
        this.clazzRepository = clazzRepository;
        this.clazzMapper = clazzMapper;
    }

    @Override
    public ClazzDTO save(ClazzDTO clazzDTO) {
        log.debug("Request to save Clazz : {}", clazzDTO);
        Clazz clazz = clazzMapper.toEntity(clazzDTO);
        clazz = clazzRepository.save(clazz);
        return clazzMapper.toDto(clazz);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClazzDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Clazz");
        return clazzRepository.findAll(pageable)
            .map(clazzMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClazzDTO> findOne(Long id) {
        log.debug("Request to get Clazz : {}", id);
        return clazzRepository.findById(id)
            .map(clazzMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Clazz : {}", id);
        clazzRepository.deleteById(id);
    }
}
