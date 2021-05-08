package vn.st.schoolmanagement.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {

    private final DetailSubjectService detailSubjectService;

    public CSVService(DetailSubjectService detailSubjectService) {
        this.detailSubjectService = detailSubjectService;
    }

    public void save(MultipartFile file) {
        try {
            List<DetailSubjectDTO> detailSubjectDTOS = CSVHelper.csvToTutorials(file.getInputStream());
            detailSubjectService.saveAll(detailSubjectDTOS);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
