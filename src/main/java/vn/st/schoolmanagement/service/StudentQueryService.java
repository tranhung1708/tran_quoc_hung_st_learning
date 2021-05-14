package vn.st.schoolmanagement.service;

import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.st.schoolmanagement.domain.Clazz;
import vn.st.schoolmanagement.domain.Clazz_;
import vn.st.schoolmanagement.domain.Student;
import vn.st.schoolmanagement.domain.Student_;
import vn.st.schoolmanagement.repository.StudentRepository;

import vn.st.schoolmanagement.service.dto.StudentCriteria;
import vn.st.schoolmanagement.service.dto.StudentDTO;
import vn.st.schoolmanagement.service.mapper.StudentMapper;

@Service
@Transactional(readOnly = true)
public class StudentQueryService extends QueryService<Student> {
    private final Logger log = LoggerFactory.getLogger(StudentQueryService.class);

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentQueryService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


    @Transactional(readOnly = true)
    public Page<StudentDTO> findByCriteria(StudentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Student> specification = createSpecification(criteria);
        return studentRepository.findAll(specification, page)
            .map(studentMapper::toDto);
    }


    protected Specification<Student> createSpecification(StudentCriteria criteria) {
        Specification<Student> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Student_.id));
            }
            if (criteria.getNameStudent() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameStudent(), Student_.nameStudent));
            }

            if (criteria.getBirthday() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBirthday(), Student_.birthday));
            }

            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), Student_.address));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Student_.gmail));
            }

            if (criteria.getGender() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGender(), Student_.gender));
            }
        }
        return specification;
    }

}
