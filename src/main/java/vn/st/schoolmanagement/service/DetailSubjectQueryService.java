package vn.st.schoolmanagement.service;

import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.st.schoolmanagement.domain.*;
import vn.st.schoolmanagement.repository.DetailSubjectRepository;
import vn.st.schoolmanagement.service.dto.DetailSubjectCriteria;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;
import vn.st.schoolmanagement.service.mapper.DetailSubjectMapper;
import javax.persistence.criteria.*;


@Service
@Transactional(readOnly = true)
public class DetailSubjectQueryService extends QueryService<DetailSubject> {
    private final Logger log = LoggerFactory.getLogger(DetailSubjectQueryService.class);

    private final DetailSubjectRepository detailSubjectRepository;

    private final DetailSubjectMapper detailSubjectMapper;

    public DetailSubjectQueryService(DetailSubjectRepository detailSubjectRepository, DetailSubjectMapper detailSubjectMapper) {
        this.detailSubjectRepository = detailSubjectRepository;
        this.detailSubjectMapper = detailSubjectMapper;
    }


    @Transactional(readOnly = true)
    public Page<DetailSubjectDTO> findByCriteria(DetailSubjectCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DetailSubject> specification = createSpecification(criteria);
        return detailSubjectRepository.findAll(specification, page)
            .map(detailSubjectMapper::toDto);
    }


    protected Specification<DetailSubject> createSpecification(DetailSubjectCriteria criteria) {
        Specification<DetailSubject> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DetailSubject_.id));
            }
            if (criteria.getMouth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMouth(), DetailSubject_.mouth));
            }
            if (criteria.getFifteenMinutes() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFifteenMinutes(), DetailSubject_.fifteenMinutes));
            }
            if (criteria.getOneLesson() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOneLesson(), DetailSubject_.oneLesson));
            }
            if (criteria.getFinishTheSubject() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFinishTheSubject(), DetailSubject_.finishTheSubject));
            }
            if (criteria.getSubjectName() != null) {
                specification = specification.and(joinTest(criteria.getSubjectName().getEquals()));
            }
            if (criteria.getStudentName() != null) {
                specification = specification.and(joinStudent(criteria.getStudentName().getEquals()));
            }
            if (criteria.getStudentBirthday() != null) {
                specification = specification.and(joinStudentBirthday(criteria.getStudentBirthday().getEquals()));
            }
            if (criteria.getStudentGender() != null) {
                specification = specification.and(joinStudentGender(criteria.getStudentGender().getEquals()));
            }
            if (criteria.getClazzName() != null) {
                specification = specification.and(joinClazz(criteria.getClazzName().getEquals()));
            }
            if (criteria.getSubjectName() != null){
                specification = specification.and(joinStudentSubject(criteria.getSubjectName().getEquals()));
            }
        }
        return specification;
    }


    public static Specification<DetailSubject> joinTest(String name) {
        return (Specification<DetailSubject>) (root, query, cb) -> {
            Join<DetailSubject, Subject> detailSubject = root.join("subject");
            return cb.equal(detailSubject.get(Subject_.name), name);
        };
    }

    public static Specification<DetailSubject> joinStudentBirthday(String name) {
        return (Specification<DetailSubject>) (root, query, cb) -> {
            Join<DetailSubject, Student> detailSubject = root.join("student");
            return cb.equal(detailSubject.get(Student_.birthday), name);
        };
    }

    public static Specification<DetailSubject> joinStudentGender(String name) {
        return (Specification<DetailSubject>) (root, query, cb) -> {
            Join<DetailSubject, Student> detailSubject = root.join("student");
            return cb.equal(detailSubject.get(Student_.gender), name);
        };
    }

    public static Specification<DetailSubject> joinStudent(String name) {
        return (Specification<DetailSubject>) (root, query, cb) -> {
            Join<DetailSubject, Student> joinStudent = root.join("student");
            return cb.equal(joinStudent.get(Student_.nameStudent), name);
        };
    }

    public static Specification<DetailSubject> joinClazz(String name) {
        return (root, query, cb) -> {
            Join<DetailSubject, Student> joinStudent = root.join("student");
            Join<Student, Clazz> joinClazz = joinStudent.join("clazz");
            return cb.equal(joinClazz.get(Clazz_.nameClass), name);
        };
    }

    public static Specification<DetailSubject> joinStudentSubject(String name) {
        return (root, query, cb) -> {
            Join<DetailSubject, Subject> joinSubject = root.join("subject");
            return cb.equal(joinSubject.get(Subject_.name), name);
        };
    }
}
