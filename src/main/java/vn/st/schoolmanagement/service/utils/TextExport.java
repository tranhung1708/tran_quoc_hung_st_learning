package vn.st.schoolmanagement.service.utils;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.st.schoolmanagement.service.StudentService;
import vn.st.schoolmanagement.service.dto.ClazzDTO;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;
import vn.st.schoolmanagement.service.dto.StudentDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TextExport {

    private final StudentService studentService;

    public TextExport(StudentService studentService) {
        this.studentService = studentService;
    }

    public  ByteArrayInputStream txtExportStudentDetailSubject(Page<ClazzDTO> clazzDTOS) {
        try {
            List<String> data = new ArrayList<>();
            double checkLearning = 0;
            double countStudentGood = 0;
            double countStudentRather = 0;
            double countStudentMedium = 0;
            for (ClazzDTO dtoClazz : clazzDTOS) {
               List<StudentDTO> studentDTOList = studentService.findAllByClazzId(dtoClazz.getIdClazz());
                for (StudentDTO student : studentDTOList) {
                    data.add(DirectoryManagement.Name_Student + student.getNameStudent() + DirectoryManagement.Down_the_line);
                    data.add(DirectoryManagement.HEADER + DirectoryManagement.Down_the_line);
                    for (DetailSubjectDTO dto : student.getDetailSubjects()) {
                        data.add(dto.formatFileText() + DirectoryManagement.Down_the_line);
                        checkLearning = checkLearning != 0 ? checkLearning : dto.checkLearning();
                    }
                    double subjectAvg = student.getDetailSubjects().stream().mapToDouble(DetailSubjectDTO::avgSubject).sum();
                    double avgStudent = subjectAvg / student.getDetailSubjects().size();
                    data.add(DirectoryManagement.Avg_Student + avgStudent + DirectoryManagement.Down_the_line);

                    if (avgStudent >= 8.5 ) {
                        countStudentGood++;
                        data.add(DirectoryManagement.Student_classification + DirectoryManagement.Good_standing + DirectoryManagement.Down_the_line);
                    }
                    if (avgStudent >= 7.0 && checkLearning == 2) {
                        countStudentRather++;
                        data.add(DirectoryManagement.Student_classification + DirectoryManagement.Academic_pretty + DirectoryManagement.Down_the_line);
                    } else if (avgStudent < 7.0 && checkLearning == 3) {
                        countStudentMedium++;
                        data.add(DirectoryManagement.Student_classification + DirectoryManagement.Learning_capacity_is_average + DirectoryManagement.Down_the_line);
                    }
                }
                data.add(DirectoryManagement.Name_Class + dtoClazz.getNameClass() +DirectoryManagement.Down_the_line+ DirectoryManagement.NumberStudentGood + countStudentGood + DirectoryManagement.Down_the_line);
                countStudentGood = 0;
                data.add("--------------------------------------------------------------------"+DirectoryManagement.Down_the_line);
                data.add(DirectoryManagement.Name_Class + dtoClazz.getNameClass() +DirectoryManagement.Down_the_line+ DirectoryManagement.NumberStudentRather + countStudentRather + DirectoryManagement.Down_the_line);
                countStudentRather = 0;
                data.add("--------------------------------------------------------------------"+DirectoryManagement.Down_the_line);
                data.add(DirectoryManagement.Name_Class + dtoClazz.getNameClass() +DirectoryManagement.Down_the_line+ DirectoryManagement.NumberStudentMedium + countStudentMedium + DirectoryManagement.Down_the_line);
                countStudentMedium = 0;
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
