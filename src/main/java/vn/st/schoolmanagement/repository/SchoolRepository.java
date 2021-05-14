package vn.st.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;
import vn.st.schoolmanagement.domain.Schools;

@SuppressWarnings("unused")
@Repository
public interface SchoolRepository extends JpaRepository<Schools, Long>, JpaSpecificationExecutor<Schools> {
}
