package vn.st.schoolmanagement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.st.schoolmanagement.domain.Student;
import vn.st.schoolmanagement.repository.StudentRepository;
import vn.st.schoolmanagement.service.StudentService;
import vn.st.schoolmanagement.service.dto.StudentDTO;
import vn.st.schoolmanagement.service.mapper.StudentMapper;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        log.debug("Request to save Student : {}", studentDTO);
        Student student = studentMapper.toEntity(studentDTO);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    @Override
    public Page<StudentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Student");
        return studentRepository.findAll(pageable)
            .map(studentMapper::toDto);
    }

    @Override
    public Optional<StudentDTO> findOneById(Long id) {
        log.debug("Request to get Student : {}", id);
        return studentRepository.findById(id)
            .map(studentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Student : {}", id);
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDTO> findAllByClazzId(Long classId) {
        return studentMapper.toDto(studentRepository.findAllByClazz_IdClazz(classId));
    }
}
