package vn.st.schoolmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.st.schoolmanagement.domain.DetailSubject;




@SuppressWarnings("unused")
@Repository
public interface DetailSubjectRepository extends JpaRepository<DetailSubject, Long>, JpaSpecificationExecutor<DetailSubject> {

}
