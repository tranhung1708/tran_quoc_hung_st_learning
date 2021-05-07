package vn.st.schoolmanagement.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import com.opencsv.CSVWriter;
import com.opencsv.bean.*;

import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;

public class OpenCsvDetailSubjectUtil {
    public static List<DetailSubjectDTO> parseCsvFile(InputStream is) {
        String[] CSV_HEADER = {"id", "mouth", "fifteenMinutes", "oneLesson", "finishTheSubject", "idSubject", "idStudent"};
        Reader fileReader = null;
        CsvToBean<DetailSubjectDTO> csvToBean = null;

        List<DetailSubjectDTO> detailSubject = new ArrayList<DetailSubjectDTO>();

        try {
            fileReader = new InputStreamReader(is);

            ColumnPositionMappingStrategy<DetailSubjectDTO> mappingStrategy = new ColumnPositionMappingStrategy<DetailSubjectDTO>();

            mappingStrategy.setType(DetailSubjectDTO.class);
            mappingStrategy.setColumnMapping(CSV_HEADER);

            csvToBean = new CsvToBeanBuilder<DetailSubjectDTO>(fileReader).withMappingStrategy(mappingStrategy).withSkipLines(1)
                .withIgnoreLeadingWhiteSpace(true).build();

            detailSubject = csvToBean.parse();

            return detailSubject;
        } catch (Exception e) {
            System.out.println("Reading CSV Error!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Closing fileReader/csvParser Error!");
                e.printStackTrace();
            }
        }
        return detailSubject;
    }


    public static void detailSubjectToCsv(Writer writer, List<DetailSubjectDTO> detailSubjectDTOS) {
        String[] CSV_HEADER = {"id", "mouth", "fifteenMinutes", "oneLesson", "finishTheSubject", "idSubject", "idStudent"};

        StatefulBeanToCsv<DetailSubjectDTO> beanToCsv = null;

        try {
            // write List of Objects
            ColumnPositionMappingStrategy<DetailSubjectDTO> mappingStrategy =
                new ColumnPositionMappingStrategy<DetailSubjectDTO>();

            mappingStrategy.setType(DetailSubjectDTO.class);
            mappingStrategy.setColumnMapping(CSV_HEADER);

            beanToCsv = new StatefulBeanToCsvBuilder<DetailSubjectDTO>(writer)
                .withMappingStrategy(mappingStrategy)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .build();

            beanToCsv.write(detailSubjectDTOS);
        } catch (Exception e) {
            System.out.println("Writing CSV error!");
            e.printStackTrace();
        }
    }
}

