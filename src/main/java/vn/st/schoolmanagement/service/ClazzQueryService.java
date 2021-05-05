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
import vn.st.schoolmanagement.domain.Subject;
import vn.st.schoolmanagement.repository.ClazzRepository;
import vn.st.schoolmanagement.service.dto.ClazzCriteria;
import vn.st.schoolmanagement.service.dto.ClazzDTO;
import vn.st.schoolmanagement.service.dto.SubjectCriteria;
import vn.st.schoolmanagement.service.dto.SubjectDTO;
import vn.st.schoolmanagement.service.mapper.ClazzMapper;

@Service
@Transactional(readOnly = true)
public class ClazzQueryService extends QueryService<Clazz> {
    private final Logger log = LoggerFactory.getLogger(ClazzQueryService.class);

    private final ClazzRepository clazzRepository;

    private final ClazzMapper clazzMapper;

    public ClazzQueryService(ClazzRepository clazzRepository, ClazzMapper clazzMapper) {
        this.clazzRepository = clazzRepository;
        this.clazzMapper = clazzMapper;
    }

    @Transactional(readOnly = true)
    public Page<ClazzDTO> findByCriteria(ClazzCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Clazz> specification = createSpecification(criteria);
        return clazzRepository.findAll(specification, page)
            .map(clazzMapper::toDto);
    }


    protected Specification<Clazz> createSpecification(ClazzCriteria criteria) {
        Specification<Clazz> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getIdClazz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdClazz(), Clazz_.idClazz));
            }
            if (criteria.getNameClass() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameClass(), Clazz_.nameClass));
            }
        }
        return specification;
    }
}
