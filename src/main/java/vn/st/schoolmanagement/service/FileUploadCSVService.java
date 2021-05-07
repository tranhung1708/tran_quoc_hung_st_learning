package vn.st.schoolmanagement.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.st.schoolmanagement.repository.DetailSubjectRepository;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;
import vn.st.schoolmanagement.service.mapper.DetailSubjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;


@Service
public class FileUploadCSVService {

    private final DetailSubjectMapper detailSubjectMapper;

    private final DetailSubjectRepository detailSubjectRepository;
    private final DetailSubjectService detailSubjectService;


    public FileUploadCSVService(DetailSubjectMapper detailSubjectMapper, DetailSubjectRepository detailSubjectRepository, DetailSubjectService detailSubjectService) {
        this.detailSubjectMapper = detailSubjectMapper;
        this.detailSubjectRepository = detailSubjectRepository;
        this.detailSubjectService = detailSubjectService;
    }

    public void store(InputStream file) {
        try {
            List<DetailSubjectDTO> detailSubjectDTOS = ApacheCommonsCsvDetailSubjectUtil.parseCsvFile(file);
            detailSubjectService.saveAll(detailSubjectDTOS);
//            detailSubjectRepository.saveAll(detailSubjectDTOS.stream().map(detailSubjectMapper::toEntity).collect(Collectors.toList()));
        } catch (Exception e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }


    public void loadFile(Writer writer) throws IOException {
        try {
            List<DetailSubjectDTO> detailSubjectDTOS = (List<DetailSubjectDTO>) detailSubjectService.findAll(Pageable.unpaged());
            ApacheCommonsCsvDetailSubjectUtil.detailSubjectToCsv(writer, detailSubjectDTOS);
        } catch (Exception e) {
            throw new RuntimeException("Fail! -> Message = " + e.getMessage());
        }
    }
}
