package vn.st.schoolmanagement.service.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.st.schoolmanagement.service.*;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;
import vn.st.schoolmanagement.service.dto.SchoolDTO;
import vn.st.schoolmanagement.service.utils.CSVExport;
import vn.st.schoolmanagement.service.utils.CSVImport;
import vn.st.schoolmanagement.service.utils.TextExport;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CSVServiceImpl implements FileService {

    private final DetailSubjectService detailSubjectService;

    private final SchoolService schoolService;
    private final TextExport textExport;


    public CSVServiceImpl(DetailSubjectService detailSubjectService,SchoolService schoolService, TextExport textExport) {
        this.detailSubjectService = detailSubjectService;
        this.schoolService = schoolService;
        this.textExport = textExport;
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
    public ByteArrayInputStream exportDataText() {
        Page<SchoolDTO> schoolDTOS = schoolService.findAll(Pageable.unpaged());
        ByteArrayInputStream in = textExport.txtExportStudentDetailSubject(schoolDTOS);
        return in;
    }

    //Export điểm học sinh ra file csv
    @Override
    public ByteArrayInputStream exportDataCsv() {
        Page<DetailSubjectDTO> detailSubjectDTOS = detailSubjectService.findAll(Pageable.unpaged());
        ByteArrayInputStream in = CSVExport.csvExportStudentDetailSubject(detailSubjectDTOS);
        return in;
    }
}
