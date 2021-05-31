package vn.st.schoolmanagement.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

public interface FileService {
    void save(MultipartFile file);

    ByteArrayInputStream exportDataText();

    ByteArrayInputStream exportDataCsv();

    ByteArrayInputStream exportDataSchoolAll();
}
