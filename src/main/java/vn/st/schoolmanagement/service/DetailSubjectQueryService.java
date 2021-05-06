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
                specification = specification.and(buildStringSpecification(criteria.getMouth(), DetailSubject_.mouth));
            }
            if (criteria.getFifteenMinutes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFifteenMinutes(), DetailSubject_.fifteenMinutes));
            }
            if (criteria.getOneLesson() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOneLesson(), DetailSubject_.oneLesson));
            }
            if (criteria.getFinishTheSubject() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFinishTheSubject(), DetailSubject_.finishTheSubject));
            }
            if (criteria.getSubjectName() != null) {
                specification = specification.and(joinTest(criteria.getSubjectName().getEquals()));
            }

            if (criteria.getStudentName() != null) {
                specification = specification.and(joinStudent(criteria.getStudentName().getEquals()));
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

    public static Specification<DetailSubject> joinStudent(String name) {
        return (Specification<DetailSubject>) (root, query, cb) -> {
            Join<DetailSubject, Student> detailSubject = root.join("student");
            return cb.equal(detailSubject.get(Student_.nameStudent), name);
        };
    }
}
