package vn.test.thuchanh12_1_2025.Services;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.test.thuchanh12_1_2025.DTO.request.CreateDepartment;
import vn.test.thuchanh12_1_2025.DTO.request.GetDepartmentRequest;
import vn.test.thuchanh12_1_2025.DTO.request.UpdateDepartmentRequest;
import vn.test.thuchanh12_1_2025.Models.Department;

public interface DepartmentService {
    Department createDepartment(CreateDepartment department);

    Department departmentUpdate(Integer id,  UpdateDepartmentRequest department);

    void deleteDepartment(Integer id);

    Page<Department> getDepartmentByFilter(GetDepartmentRequest request, Pageable pageable);

}
