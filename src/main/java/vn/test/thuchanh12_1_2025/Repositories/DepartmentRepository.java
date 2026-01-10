package vn.test.thuchanh12_1_2025.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.test.thuchanh12_1_2025.Models.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>, JpaSpecificationExecutor<Department> {

    Department findByName(String name);
}

