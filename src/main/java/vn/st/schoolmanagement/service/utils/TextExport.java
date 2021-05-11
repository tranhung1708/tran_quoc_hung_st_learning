package vn.st.schoolmanagement.service.utils;

import org.springframework.data.domain.Page;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;
import vn.st.schoolmanagement.service.dto.StudentDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TextExport {
    public static Object txtExportStudentDetailSubject(Page<StudentDTO> students) {
        try {
            List<StringBuilder> data = new ArrayList<>();
            for (StudentDTO student : students) {
                StringBuilder record = new StringBuilder();
                record.append(DirectoryManagement.Name_Student).append(student.getNameStudent());
                record.append(DirectoryManagement.Down_the_line);
                data.add(record);
                record = new StringBuilder();
                record.append(DirectoryManagement.HEADER);
                record.append(DirectoryManagement.Down_the_line);
                data.add(record);
                record = new StringBuilder();
                for (DetailSubjectDTO dto : student.getDetailSubjects()) {
                    record.append(dto.getSubject().getName()).append(DirectoryManagement.TEXT_AF);
                    record.append(DirectoryManagement.TEXT_BEFORE).append(dto.getMouth()).append(DirectoryManagement.TEXT_AF);
                    record.append(DirectoryManagement.TEXT_BEFORE).append(dto.getFifteenMinutes()).append(DirectoryManagement.TEXT_AF);
                    record.append(DirectoryManagement.TEXT_BEFORE).append(dto.getOneLesson()).append(DirectoryManagement.TEXT_AF);
                    record.append(DirectoryManagement.TEXT_BEFORE).append(dto.getFinishTheSubject()).append(DirectoryManagement.TEXT_AF);
                    record.append(DirectoryManagement.TEXT_BEFORE).append(dto.avgSubject());
                    record.append(DirectoryManagement.Down_the_line);
                    dto.checkAvgStudent();
                }
                System.out.println();
                double avg = student.getDetailSubjects().stream().mapToDouble(DetailSubjectDTO::avgSubject).sum();
                double avgStudent = avg / student.getDetailSubjects().size();

                record.append(DirectoryManagement.Avg_Student).append(avgStudent);
                record.append(DirectoryManagement.Down_the_line);
                if (avgStudent >= 8.5) {
                    record.append(DirectoryManagement.Student_classification).append(DirectoryManagement.Good_standing);
                    record.append(DirectoryManagement.Down_the_line);
                }
                if (avgStudent >= 7.0) {
                    record.append(DirectoryManagement.Student_classification).append(DirectoryManagement.Academic_pretty);
                    record.append(DirectoryManagement.Down_the_line);
                } else {
                    record.append(DirectoryManagement.Student_classification).append(DirectoryManagement.Learning_capacity_is_average);
                }
                record.append(DirectoryManagement.Down_the_line);
                data.add(record);
            }
            ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
            for (StringBuilder record : data) {
                output.write(record.toString().getBytes());
            }
            output.close();
            return new ByteArrayInputStream(output.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
