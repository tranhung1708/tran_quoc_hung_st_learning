package vn.st.schoolmanagement.service.utils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.data.domain.Page;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;

public class CSVExport {
    private static final String[] HEADER = {"STT" ,  "Môn Học",  "Miệng"  , "15 Phút"  ,"1 Tiết" ,"Kết thúc môn","Trung Bình Môn"};

    public static ByteArrayInputStream csvExportStudentDetailSubject(Page<DetailSubjectDTO> detailSubjectDTOS) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL).withHeader(HEADER);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (DetailSubjectDTO dto : detailSubjectDTOS) {
                List<String> data = Arrays.asList(
                    String.valueOf(dto.getId()),
                    String.valueOf(dto.getSubject().getName()),
                    String.valueOf(dto.getMouth()),
                    String.valueOf(dto.getFifteenMinutes()),
                    String.valueOf(dto.getOneLesson()),
                    String.valueOf(dto.getFinishTheSubject()),
                    String.valueOf(dto.avgSubject())
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}

