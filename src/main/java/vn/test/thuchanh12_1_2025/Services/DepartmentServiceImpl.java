package vn.test.thuchanh12_1_2025.Services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.test.thuchanh12_1_2025.DTO.request.CreateDepartment;
import vn.test.thuchanh12_1_2025.DTO.request.GetDepartmentRequest;
import vn.test.thuchanh12_1_2025.DTO.request.UpdateDepartmentRequest;
import vn.test.thuchanh12_1_2025.Models.Department;
import vn.test.thuchanh12_1_2025.Repositories.DepartmentRepository;
import vn.test.thuchanh12_1_2025.Specification.DepartmentSpecification;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;



    @Override
    public Department createDepartment(CreateDepartment department) {
        Department existedDepartment = departmentRepository.findByName(department.getName());
        if (existedDepartment != null) {
            throw new RuntimeException("Department name already exists");
        }
        Department newDepartment = modelMapper.map(department, Department.class);
        return departmentRepository.save(newDepartment);
    }

    @Override
    public Department departmentUpdate(Integer id, UpdateDepartmentRequest department) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty()) {
            throw new RuntimeException("Department not found");
        }
        Department departmentUpdate = departmentOptional.get();
        departmentUpdate.setTotalMember(department.getTotalMember());
        departmentUpdate.setCreatedDate(department.getCreatedDate());
        return departmentRepository.save(departmentUpdate);
    }

    @Override
    public void deleteDepartment(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty()) {
            throw new RuntimeException("Department not found");
        }
        departmentRepository.deleteById(id);


    }

    @Override
    public Page<Department> getDepartmentByFilter(GetDepartmentRequest request, Pageable pageable) {
        Specification<Department> specification =
                (root, query, cb) -> cb.conjunction();
        if (request.getName() != null) {
            specification = specification.and(DepartmentSpecification.hasName(request.getName()));
        }
        if (request.getTotalNumber() != null) {
            specification = specification.and(DepartmentSpecification.hasTotalMember(String.valueOf(request.getTotalNumber())));
        }
        return departmentRepository.findAll(specification, pageable);
    }


}
