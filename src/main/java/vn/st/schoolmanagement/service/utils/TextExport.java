package vn.st.schoolmanagement.service.utils;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.st.schoolmanagement.service.ClazzService;
import vn.st.schoolmanagement.service.DetailSubjectService;
import vn.st.schoolmanagement.service.StudentService;
import vn.st.schoolmanagement.service.dto.ClazzDTO;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;
import vn.st.schoolmanagement.service.dto.SchoolDTO;
import vn.st.schoolmanagement.service.dto.StudentDTO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TextExport {

    private final StudentService studentService;
    private final DetailSubjectService detailSubjectService;
    private final ClazzService clazzService;

    public TextExport(StudentService studentService, DetailSubjectService detailSubjectService, ClazzService clazzService) {
        this.studentService = studentService;
        this.detailSubjectService = detailSubjectService;
        this.clazzService = clazzService;
    }

    public ByteArrayInputStream txtExportStudentDetailSubject(Page<SchoolDTO> schoolDTOS) {
        try {
            List<String> data = new ArrayList<>();
            for (SchoolDTO schoolDTO : schoolDTOS) {
                data.add(schoolDTO.getNameSchools() + DirectoryManagement.Down_the_line);
                List<ClazzDTO> schoolDTOList = clazzService.findAllByShoolId(schoolDTO.getIdSchool());
                for (ClazzDTO dtoClazz : schoolDTOList) {
                    List<StudentDTO> studentDTOList = studentService.findAllByClazzId(dtoClazz.getIdClazz());
                    for (StudentDTO student : studentDTOList) {
                        data.add(DirectoryManagement.Name_Student + student.getNameStudent() + DirectoryManagement.Down_the_line);
                        data.add(DirectoryManagement.HEADER + DirectoryManagement.Down_the_line);
                        List<DetailSubjectDTO> detailSubjectDTOS = detailSubjectService.findAllByStudentId(student.getId());
                        for (DetailSubjectDTO dto : detailSubjectDTOS) {
                            data.add(dto.formatFileText() + DirectoryManagement.Down_the_line);
                            DirectoryManagement.checkLearning = DirectoryManagement.checkLearning != DirectoryManagement.ZeroNumber ? DirectoryManagement.checkLearning : dto.checkLearning();
                        }
                        double subjectAvg = student.getDetailSubjects().stream().mapToDouble(DetailSubjectDTO::avgSubject).sum();
                        double avgStudent = subjectAvg / student.getDetailSubjects().size();
                        data.add(DirectoryManagement.Avg_Student + avgStudent + DirectoryManagement.Down_the_line);

                        if (avgStudent >= DirectoryManagement.CompareBirthExcellent) {
                            DirectoryManagement.countStudentGood++;
                            DirectoryManagement.countStudentGood1++;
                            data.add(DirectoryManagement.Student_classification + DirectoryManagement.Good_standing + DirectoryManagement.Down_the_line);
                        }
                        if (avgStudent >= DirectoryManagement.Compare_Number && DirectoryManagement.checkLearning == DirectoryManagement.academic) {
                            DirectoryManagement.countStudentRather++;
                            data.add(DirectoryManagement.Student_classification + DirectoryManagement.Academic_pretty + DirectoryManagement.Down_the_line);
                        } else if (avgStudent < DirectoryManagement.Compare_Number && DirectoryManagement.checkLearning == DirectoryManagement.medium) {
                            DirectoryManagement.countStudentMedium++;
                            data.add(DirectoryManagement.Student_classification + DirectoryManagement.Learning_capacity_is_average + DirectoryManagement.Down_the_line);
                        }
                    }
                    data.add(DirectoryManagement.Name_Class + dtoClazz.getNameClass() + DirectoryManagement.Down_the_line + DirectoryManagement.NumberStudentGood + DirectoryManagement.countStudentGood + DirectoryManagement.Down_the_line);
                    DirectoryManagement.countStudentGood = DirectoryManagement.ZeroNumber;
                    data.add("--------------------------------------------------------------------" + DirectoryManagement.Down_the_line);
                    data.add(DirectoryManagement.Name_Class + dtoClazz.getNameClass() + DirectoryManagement.Down_the_line + DirectoryManagement.NumberStudentRather + DirectoryManagement.countStudentRather + DirectoryManagement.Down_the_line);
                    DirectoryManagement.countStudentRather = DirectoryManagement.ZeroNumber;
                    data.add("--------------------------------------------------------------------" + DirectoryManagement.Down_the_line);
                    data.add(DirectoryManagement.Name_Class + dtoClazz.getNameClass() + DirectoryManagement.Down_the_line + DirectoryManagement.NumberStudentMedium + DirectoryManagement.countStudentMedium + DirectoryManagement.Down_the_line);
                    DirectoryManagement.countStudentMedium = DirectoryManagement.ZeroNumber;
                }
                data.add(DirectoryManagement.Total_number_of_excellent_students_of_the_whole_school + DirectoryManagement.countStudentGood1 +DirectoryManagement.Down_the_line);
                DirectoryManagement.countStudentGood1 = DirectoryManagement.ZeroNumber;
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
