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
                data.add(schoolDTO.getNameSchools() + DirectoryManagement.downTheLine);
                List<ClazzDTO> schoolDTOList = clazzService.findAllByShoolId(schoolDTO.getIdSchool());
                for (ClazzDTO dtoClazz : schoolDTOList) {
                    List<StudentDTO> studentDTOList = studentService.findAllByClazzId(dtoClazz.getIdClazz());
                    for (StudentDTO student : studentDTOList) {
                        data.add(DirectoryManagement.nameStudent + student.getNameStudent() + DirectoryManagement.downTheLine);
                        data.add(DirectoryManagement.HEADER + DirectoryManagement.downTheLine);
                        List<DetailSubjectDTO> detailSubjectDTOS = detailSubjectService.findAllByStudentId(student.getId());
                        for (DetailSubjectDTO dto : detailSubjectDTOS) {
                            data.add(dto.formatFileText() + DirectoryManagement.downTheLine);
                            DirectoryManagement.checkLearning = DirectoryManagement.checkLearning != DirectoryManagement.zeroNumber ? DirectoryManagement.checkLearning : dto.checkLearning();
                        }
                        double subjectAvg = student.getDetailSubjects().stream().mapToDouble(DetailSubjectDTO::avgSubject).sum();
                        double avgStudent = subjectAvg / student.getDetailSubjects().size();
                        data.add(DirectoryManagement.avgStudent + avgStudent + DirectoryManagement.downTheLine);

                        if (avgStudent >= DirectoryManagement.compareBirthExcellent) {
                            DirectoryManagement.countStudentGood++;
                            DirectoryManagement.countStudentGood1++;
                            data.add(DirectoryManagement.studentClassIfication + DirectoryManagement.goodStanding + DirectoryManagement.downTheLine);
                        }
                        if (avgStudent >= DirectoryManagement.compareNumber && DirectoryManagement.checkLearning == DirectoryManagement.academic) {
                            DirectoryManagement.countStudentRather++;
                            data.add(DirectoryManagement.studentClassIfication + DirectoryManagement.academicPretty + DirectoryManagement.downTheLine);
                        } else if (avgStudent < DirectoryManagement.compareNumber && DirectoryManagement.checkLearning == DirectoryManagement.medium) {
                            DirectoryManagement.countStudentMedium++;
                            data.add(DirectoryManagement.studentClassIfication + DirectoryManagement.learningCapacityIsAverage + DirectoryManagement.downTheLine);
                        }
                    }
                    data.add(DirectoryManagement.nameClass + dtoClazz.getNameClass() + DirectoryManagement.downTheLine + DirectoryManagement.numberStudentGood + DirectoryManagement.countStudentGood + DirectoryManagement.downTheLine);
                    DirectoryManagement.countStudentGood = DirectoryManagement.zeroNumber;
                    data.add("--------------------------------------------------------------------" + DirectoryManagement.downTheLine);
                    data.add(DirectoryManagement.nameClass + dtoClazz.getNameClass() + DirectoryManagement.downTheLine + DirectoryManagement.numberStudentRather + DirectoryManagement.countStudentRather + DirectoryManagement.downTheLine);
                    DirectoryManagement.countStudentRather = DirectoryManagement.zeroNumber;
                    data.add("--------------------------------------------------------------------" + DirectoryManagement.downTheLine);
                    data.add(DirectoryManagement.nameClass + dtoClazz.getNameClass() + DirectoryManagement.downTheLine + DirectoryManagement.numberStudentMedium + DirectoryManagement.countStudentMedium + DirectoryManagement.downTheLine);
                    DirectoryManagement.countStudentMedium = DirectoryManagement.zeroNumber;
                }
                data.add(DirectoryManagement.totalNumberOfExcellentStudentsOfTheWholeSchool + DirectoryManagement.countStudentGood1 +DirectoryManagement.downTheLine);
                DirectoryManagement.countStudentGood1 = DirectoryManagement.zeroNumber;
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
