package vn.st.schoolmanagement.web.rest;

import io.github.jhipster.web.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.st.schoolmanagement.service.DetailSubjectQueryService;
import vn.st.schoolmanagement.service.DetailSubjectService;
import vn.st.schoolmanagement.service.dto.DetailSubjectCriteria;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;
import vn.st.schoolmanagement.service.dto.StudentCriteria;
import vn.st.schoolmanagement.service.dto.StudentDTO;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DetailSubjectController {
    private final Logger log = LoggerFactory.getLogger(DetailSubjectController.class);
    private static final String ENTITY_NAME = "DetailSubject";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetailSubjectService detailSubjectService;
    private final DetailSubjectQueryService detailSubjectQueryService;

    public DetailSubjectController(DetailSubjectService detailSubjectService, DetailSubjectQueryService detailSubjectQueryService) {
        this.detailSubjectService = detailSubjectService;
        this.detailSubjectQueryService = detailSubjectQueryService;
    }

    @GetMapping("/detail-subjects")
    public ResponseEntity<List<DetailSubjectDTO>> getAllDetailSubject(DetailSubjectCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Student by criteria: {}", criteria);
        Page<DetailSubjectDTO> page = detailSubjectQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
