package vn.st.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import vn.st.schoolmanagement.domain.Student;
import vn.st.schoolmanagement.service.dto.StudentDTO;

import java.util.List;

@SuppressWarnings("unused")
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    List<Student> findAllByClazz_IdClazz(Long id);
}
