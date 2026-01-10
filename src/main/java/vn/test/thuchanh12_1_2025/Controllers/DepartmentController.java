package vn.test.thuchanh12_1_2025.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.test.thuchanh12_1_2025.Common.BaseResponse;
import vn.test.thuchanh12_1_2025.DTO.request.*;
import vn.test.thuchanh12_1_2025.Models.Department;
import vn.test.thuchanh12_1_2025.Services.DepartmentService;


@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    //api tạo department
    @PostMapping("/departments")
    public ResponseEntity<BaseResponse<Department>> createDepartment(@RequestBody @Valid CreateDepartment department) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(departmentService.createDepartment(department), "Create department successfully"));
    }

    // api update department
    @PutMapping("/departments/update")
    public ResponseEntity<BaseResponse<Department>> updateDepartment(@RequestParam Integer id, @RequestBody @Valid UpdateDepartmentRequest departmentUpdate) {
        return ResponseEntity.ok(new BaseResponse<>(departmentService.departmentUpdate(id, departmentUpdate), "Update department successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse<Void>> deleteDepartment(@RequestParam Integer id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok(new BaseResponse<>(null, "Delete department successfully"));

    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<Page<Department>>> searchDepartment(GetDepartmentRequest request, Pageable pageable) {
        Page<Department> getDepartment = departmentService.getDepartmentByFilter(request, pageable);
        return ResponseEntity.ok(new BaseResponse<>(getDepartment, "Get departments successfully"));
    }


}
