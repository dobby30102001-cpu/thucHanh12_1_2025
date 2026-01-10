package vn.test.thuchanh12_1_2025.Specification;

import org.springframework.data.jpa.domain.Specification;
import vn.test.thuchanh12_1_2025.Models.Department;

public class DepartmentSpecification {
    public static Specification<Department> hasName(final String name) {
        return (root, query, cb) -> cb.equal(root.get("name"), name);
    }

    public static Specification<Department> hasTotalMember(final String role) {
        return (root, query, cb) -> cb.equal(root.get("totalMember"), role);
    }

}
