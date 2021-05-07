package vn.st.schoolmanagement.service;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;

public class ApacheCommonsCsvDetailSubjectUtil {
    private static String csvExtension = "csv";

    public static void detailSubjectToCsv(Writer writer, List<DetailSubjectDTO> detailSubjectDTOS) throws IOException {

        try (CSVPrinter csvPrinter = new CSVPrinter(writer,
            CSVFormat.DEFAULT.withHeader("id", "mouth", "fifteenMinutes", "oneLesson", "finishTheSubject", "idSubject", "idStudent"));) {
            for (DetailSubjectDTO detailSubjectDTO : detailSubjectDTOS) {
                List<? extends Serializable> data = Arrays.asList(String.valueOf(detailSubjectDTO.getId()), detailSubjectDTO.getMouth(),
                    detailSubjectDTO.getFifteenMinutes(),detailSubjectDTO.getOneLesson()
                    ,detailSubjectDTO.getFinishTheSubject(),detailSubjectDTO.getIdStudent()
                    ,detailSubjectDTO.getIdSubject());

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            System.out.println("Writing CSV error!");
            e.printStackTrace();
        }
    }

    public static List<DetailSubjectDTO> parseCsvFile(InputStream is) {
        BufferedReader fileReader = null;
        CSVParser csvParser = null;

        List<DetailSubjectDTO> customers = new ArrayList<DetailSubjectDTO>();

        try {
            fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            csvParser = new CSVParser(fileReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                DetailSubjectDTO detailSubjectDTO = new DetailSubjectDTO(Long.parseLong(csvRecord.get("id")), csvRecord.get("mouth"),
                    csvRecord.get("fifteenMinutes"),csvRecord.get("oneLesson"),csvRecord.get("finishTheSubject"),Long.parseLong(csvRecord.get("idSubject")),Long.parseLong(csvRecord.get("idStudent")));

                customers.add(detailSubjectDTO);
            }

        } catch (Exception e) {
            System.out.println("Reading CSV Error!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                csvParser.close();
            } catch (IOException e) {
                System.out.println("Closing fileReader/csvParser Error!");
                e.printStackTrace();
            }
        }

        return customers;
    }

    public static boolean isCSVFile(MultipartFile file) {
        String extension = file.getOriginalFilename().split("\\.")[1];

        if(!extension.equals(csvExtension)) {
            return false;
        }

        return true;
    }
}
