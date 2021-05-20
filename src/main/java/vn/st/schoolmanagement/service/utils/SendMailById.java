package vn.st.schoolmanagement.service.utils;

import org.springframework.stereotype.Service;
import vn.st.schoolmanagement.service.DetailSubjectService;
import vn.st.schoolmanagement.service.MailService;
import vn.st.schoolmanagement.service.StudentService;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;
import vn.st.schoolmanagement.service.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

@Service
public class SendMailById {
    private final StudentService studentService;
    private final DetailSubjectService detailSubjectService;
    private final MailService mailService;

    public SendMailById(StudentService studentService, DetailSubjectService detailSubjectService, MailService mailService) {
        this.studentService = studentService;
        this.detailSubjectService = detailSubjectService;
        this.mailService = mailService;
    }

    //send gmail đến từng học sinh theo id của học sinh
    public void sendMailById(Long id) {
        Optional<StudentDTO> studentDTO = studentService.findOneById(id);
        if (studentDTO.isPresent()) {
            StudentDTO student = studentDTO.get();
            StringBuilder data = new StringBuilder();
            data.append(DirectoryManagement.nameStudent).append(student.getNameStudent()).append(DirectoryManagement.downTheLine);
            data.append(DirectoryManagement.HEADER + DirectoryManagement.downTheLine);
            List<DetailSubjectDTO> detailSubjectDTOS = detailSubjectService.findAllByStudentId(student.getId());
            for (DetailSubjectDTO dto : detailSubjectDTOS) {
                data.append(dto.formatFileText()).append(DirectoryManagement.downTheLine);
            }
            double subjectAvg = student.getDetailSubjects().stream().mapToDouble(DetailSubjectDTO::avgSubject).sum();
            double avgStudent = subjectAvg / student.getDetailSubjects().size();
            data.append(DirectoryManagement.avgStudent).append(avgStudent).append(DirectoryManagement.downTheLine);
            student.setLoadFile(data.toString());
            mailService.sendEmailFromTemplateDetailSubject(student);
        }
    }
}
