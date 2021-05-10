package vn.st.schoolmanagement.service.utils;
import org.springframework.data.domain.Page;
import vn.st.schoolmanagement.domain.Subject;
import vn.st.schoolmanagement.service.dto.DetailSubjectDTO;
import vn.st.schoolmanagement.service.dto.StudentDTO;
import vn.st.schoolmanagement.service.dto.SubjectDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextExport {
    private static final String HEADER = "Môn Học     | Miệng     | 15 Phút   | 1 Tiếng   |Kết thúc môn|  Trung Bình Môn";

    public static ByteArrayInputStream txtExportStudentDetailSubject(Page<StudentDTO> students) {
        try {
            String TEXT_AF = "    | ";
            String TEXT_BEFORE = "   ";
            List<StringBuilder> data = new ArrayList<>();
            for (StudentDTO student : students) {
                StringBuilder record = new StringBuilder();
                record.append("Tên Học Sinh: ").append(student.getNameStudent());
                record.append('\n');
                data.add(record);
                record = new StringBuilder();
                record.append(HEADER);
                record.append('\n');
                data.add(record);
                record = new StringBuilder();
                for (DetailSubjectDTO dto : student.getDetailSubjects()) {
                    record.append(dto.getSubject().getName()).append(TEXT_AF);
                    record.append(TEXT_BEFORE).append(dto.getMouth()).append(TEXT_AF);
                    record.append(TEXT_BEFORE).append(dto.getFifteenMinutes()).append(TEXT_AF);
                    record.append(TEXT_BEFORE).append(dto.getOneLesson()).append(TEXT_AF);
                    record.append(TEXT_BEFORE).append(dto.getFinishTheSubject()).append(TEXT_AF);
                    record.append(TEXT_BEFORE).append(dto.avg());
                    record.append('\n');
                }
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
