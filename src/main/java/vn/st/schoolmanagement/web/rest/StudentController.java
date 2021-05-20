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
import vn.st.schoolmanagement.service.StudentQueryService;
import vn.st.schoolmanagement.service.StudentService;
import vn.st.schoolmanagement.service.dto.StudentCriteria;
import vn.st.schoolmanagement.service.dto.StudentDTO;
import vn.st.schoolmanagement.web.rest.errors.BadRequestAlertException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    private final Logger log = LoggerFactory.getLogger(StudentController.class);
    private static final String ENTITY_NAME = "Student";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StudentService studentService;
    private final StudentQueryService studentQueryService;

    public StudentController(StudentService studentService, StudentQueryService studentQueryService) {
        this.studentService = studentService;
        this.studentQueryService = studentQueryService;
    }

    //    Lấy dữ liệu của student
    @GetMapping("/get-all-student")
    public ResponseEntity<List<StudentDTO>> getAllStudent(StudentCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Student by criteria: {}", criteria);
        Page<StudentDTO> page = studentQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    //xóa student theo id
    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.debug("REST request to delete Student : {}", id);
        studentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    //tạo mới student
    @PostMapping("/create-student")
    public ResponseEntity<StudentDTO> save(@RequestBody StudentDTO studentDTO) throws URISyntaxException {
        log.debug("REST request to save student : {}", studentDTO);
        if (studentDTO.getId() != null) {
            throw new BadRequestAlertException("A new clazz cannot already have an idStudent", ENTITY_NAME, "idexists");
        }
        StudentDTO result = studentService.save(studentDTO);
        return ResponseEntity.created(new URI("/api/create-student/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    //    chỉnh sửa student
    @PutMapping("/update-student")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {
        log.debug("REST request to update student : {}", studentDTO);
        if (studentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StudentDTO result = studentService.save(studentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, studentDTO.getId().toString()))
            .body(result);
    }
}
