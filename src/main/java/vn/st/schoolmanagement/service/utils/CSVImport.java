package vn.st.schoolmanagement.service.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;

public class CSVImport {

    public static List<DetailSubjectDTO> csvDetailSubject(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                 CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<DetailSubjectDTO> tutorials = new ArrayList<DetailSubjectDTO>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                DetailSubjectDTO tutorial = new DetailSubjectDTO();
                tutorial.setMouth(Double.parseDouble(csvRecord.get("mouth")));
                tutorial.setFifteenMinutes(Double.parseDouble(csvRecord.get("fifteenMinutes")));
                tutorial.setOneLesson(Double.parseDouble(csvRecord.get("oneLesson")));
                tutorial.setFinishTheSubject(Double.parseDouble(csvRecord.get("finishTheSubject")));
                tutorial.setIdSubject(Long.parseLong(csvRecord.get("idSubject")));
                tutorial.setIdStudent(Long.parseLong(csvRecord.get("idStudent")));
                tutorials.add(tutorial);
            }
            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
