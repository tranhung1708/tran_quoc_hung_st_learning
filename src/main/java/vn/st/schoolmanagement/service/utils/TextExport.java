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
    public static ByteArrayInputStream txtExportStudentDetailSubject(Page<StudentDTO> students) {
        try {
            List<String> data = new ArrayList<>();
            double checkLearning = 0;
            for (StudentDTO student : students) {
                data.add(DirectoryManagement.Name_Student + student.getNameStudent() + DirectoryManagement.Down_the_line);
                data.add(DirectoryManagement.HEADER + DirectoryManagement.Down_the_line);
                for (DetailSubjectDTO dto : student.getDetailSubjects()) {
                    data.add(dto.formatFileText() + DirectoryManagement.Down_the_line);
                    System.out.println(checkLearning);
                    checkLearning = checkLearning != 0 ? checkLearning : dto.checkLearning();
                }
                double subjectAvg = student.getDetailSubjects().stream().mapToDouble(DetailSubjectDTO::avgSubject).sum();
                double avgStudent = subjectAvg / student.getDetailSubjects().size();
                data.add(DirectoryManagement.Avg_Student + avgStudent + DirectoryManagement.Down_the_line);
                if (avgStudent >= 8.5 && checkLearning == 1) {
                    data.add(DirectoryManagement.Student_classification + DirectoryManagement.Good_standing + DirectoryManagement.Down_the_line);
                }
                if (avgStudent >= 7.0 && checkLearning == 2) {
                    data.add(DirectoryManagement.Student_classification + DirectoryManagement.Academic_pretty + DirectoryManagement.Down_the_line);
                } else if (avgStudent < 7.0 && checkLearning == 3) {
                    data.add(DirectoryManagement.Student_classification + DirectoryManagement.Learning_capacity_is_average + DirectoryManagement.Down_the_line);
                }
            }
            ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
            for (String record : data) {
                output.write(record.getBytes());
            }
            output.close();
            return new ByteArrayInputStream(output.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
