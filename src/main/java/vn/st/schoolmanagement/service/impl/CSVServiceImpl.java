package vn.st.schoolmanagement.service.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.st.schoolmanagement.service.DetailSubjectService;
import vn.st.schoolmanagement.service.FileService;
import vn.st.schoolmanagement.service.StudentService;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;
import vn.st.schoolmanagement.service.dto.StudentDTO;
import vn.st.schoolmanagement.service.utils.CSVExport;
import vn.st.schoolmanagement.service.utils.CSVImport;
import vn.st.schoolmanagement.service.utils.TextExport;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CSVServiceImpl implements FileService {

    private final DetailSubjectService detailSubjectService;

    private final StudentService studentService;


    public CSVServiceImpl(DetailSubjectService detailSubjectService, StudentService studentService) {
        this.detailSubjectService = detailSubjectService;
        this.studentService = studentService;
    }

    @Override
    public void save(MultipartFile file) {
        try {
            List<DetailSubjectDTO> detailSubjectDTOS = CSVImport.csvDetailSubject(file.getInputStream());
            detailSubjectService.saveAll(detailSubjectDTOS);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    //Export điểm học sinh ra file text
    @Override
    public ByteArrayInputStream getFile() {
        Page<StudentDTO> studentDTOS = studentService.findAll(Pageable.unpaged());
        ByteArrayInputStream in = TextExport.txtExportStudentDetailSubject(studentDTOS);
        return in;
    }

    //Export điểm học sinh ra file csv
    @Override
    public ByteArrayInputStream load() {
        Page<DetailSubjectDTO> detailSubjectDTOS = detailSubjectService.findAll(Pageable.unpaged());
        ByteArrayInputStream in = CSVExport.csvExportStudentDetailSubject(detailSubjectDTOS);
        return in;
    }
}
