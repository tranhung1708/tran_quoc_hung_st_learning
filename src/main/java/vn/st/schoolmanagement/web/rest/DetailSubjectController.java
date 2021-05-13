package vn.st.schoolmanagement.web.rest;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.st.schoolmanagement.service.impl.CSVServiceImpl;
import vn.st.schoolmanagement.service.DetailSubjectQueryService;
import vn.st.schoolmanagement.service.DetailSubjectService;
import vn.st.schoolmanagement.service.dto.*;
import vn.st.schoolmanagement.web.rest.errors.BadRequestAlertException;
import org.springframework.core.io.Resource;

import java.net.URI;
import java.net.URISyntaxException;
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
    private final CSVServiceImpl csvService;

    public DetailSubjectController(DetailSubjectService detailSubjectService, DetailSubjectQueryService detailSubjectQueryService, CSVServiceImpl csvService) {
        this.detailSubjectService = detailSubjectService;
        this.detailSubjectQueryService = detailSubjectQueryService;
        this.csvService = csvService;
    }

    //Lấy dữ liệu theo yêu cầu 4
    @GetMapping("/detail-subjects")
    public ResponseEntity<List<DetailSubjectDTO>> getAllDetailSubject(DetailSubjectCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Student by criteria: {}", criteria);
        Page<DetailSubjectDTO> page = detailSubjectQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    //Thêm điểm bằng danh sách điểm
    @PostMapping("/create-detail-subject")
    public ResponseEntity<DetailSubjectDTO> save(@RequestBody DetailSubjectDTO subjectDTO) throws URISyntaxException {
        log.debug("REST request to save DetailSubject : {}", subjectDTO);
        if (subjectDTO.getId() != null) {
            throw new BadRequestAlertException("A new clazz cannot already have an id", ENTITY_NAME, "idexists");
        }
        DetailSubjectDTO result = detailSubjectService.save(subjectDTO);
        return ResponseEntity.created(new URI("/api/create-detail-subject/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    //Thêm danh sách điểm bằng một list
    @PostMapping("/create-list-detail-subject")
    public ResponseEntity<List<DetailSubjectDTO>> saveList(@RequestBody List<DetailSubjectDTO> detailSubjectDTOS) {
        log.debug("REST request to save list DetailSubject : {}", detailSubjectDTOS);
        List<DetailSubjectDTO> detailSubjectDTOS1 = detailSubjectService.saveAll(detailSubjectDTOS);
        return new ResponseEntity<>(detailSubjectDTOS1, HttpStatus.CREATED);
    }

    //thêm điểm bằng một file csv
    @PostMapping("/create-csv-detail-subject")
    public ResponseEntity<DetailSubjectDTO> uploadFile(@RequestParam("file") MultipartFile file) {
        log.debug("REST request to save list DetailSubject : {}", file);
        csvService.save(file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Tải file điểm của từng học sinh bằng file text(đang làm chưa xong)
    @GetMapping("/get-file-text")
    public ResponseEntity<Resource> getFileText() {
        String filename = "studentSubject.txt";
        InputStreamResource file = new InputStreamResource(csvService.getFile());
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("text/plain"))
            .body(file);
    }
    //Tải file điểm của từng học sinh bằng file csv (đang làm chưa xong)
    @GetMapping("/get-file-csv")
    public ResponseEntity<Resource> getFileCSV() {
        String filename = "detailSubject.csv";
        InputStreamResource file = new InputStreamResource(csvService.load());
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/csv"))
            .body(file);
    }
}
