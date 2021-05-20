package vn.st.schoolmanagement.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.st.schoolmanagement.service.ClazzQueryService;
import vn.st.schoolmanagement.service.ClazzService;
import vn.st.schoolmanagement.service.dto.ClazzCriteria;
import vn.st.schoolmanagement.service.dto.ClazzDTO;
import vn.st.schoolmanagement.web.rest.errors.BadRequestAlertException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClazzController {
    private final Logger log = LoggerFactory.getLogger(ClazzController.class);
    private static final String ENTITY_NAME = "Clazz";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClazzService clazzService;
    private final ClazzQueryService clazzQueryService;

    public ClazzController(ClazzService clazzService, ClazzQueryService clazzQueryService) {
        this.clazzService = clazzService;
        this.clazzQueryService = clazzQueryService;
    }

    //Lấy dữ liệu
    @GetMapping("/get-all-clazz")
    public ResponseEntity<List<ClazzDTO>> getAllClazz(ClazzCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Clazz by criteria: {}", criteria);
        Page<ClazzDTO> page = clazzQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    //Xóa Lớp Học
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClazz(@PathVariable Long id) {
        log.debug("REST request to delete Clazz : {}", id);
        clazzService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    //Tạo mới lớp học
    @PostMapping("/create-clazz")
    public ResponseEntity<ClazzDTO> save(@RequestBody ClazzDTO clazzDTO) throws URISyntaxException {
        log.debug("REST request to save Clazz : {}", clazzDTO);
        if (clazzDTO.getIdClazz() != null) {
            throw new BadRequestAlertException("A new clazz cannot already have an idClazz", ENTITY_NAME, "idexists");
        }
        ClazzDTO result = clazzService.save(clazzDTO);
        return ResponseEntity.created(new URI("/api/create-clazz/" + result.getIdClazz()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdClazz().toString()))
            .body(result);
    }

    //Cập nhật lớp học
    @PutMapping("/update-clazz")
    public ResponseEntity<ClazzDTO> updateClazz(@RequestBody ClazzDTO clazzDTO) {
        log.debug("REST request to update clazz : {}", clazzDTO);
        if (clazzDTO.getIdClazz() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClazzDTO result = clazzService.save(clazzDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clazzDTO.getIdClazz().toString()))
            .body(result);
    }

}
