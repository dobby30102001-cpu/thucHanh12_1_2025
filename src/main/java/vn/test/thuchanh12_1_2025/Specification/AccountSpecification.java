package vn.test.thuchanh12_1_2025.Specification;

import org.springframework.data.jpa.domain.Specification;
import vn.test.thuchanh12_1_2025.Models.Account;

public class AccountSpecification {
    public static Specification<Account> hasUsername(String username) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("username"), username);
    }

    public static Specification<Account> hasFirstName(String firstname) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("firstName"), firstname);

    }

    public static Specification<Account> hasLastName(String lastname) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("lastName"), lastname);
    }

    public static Specification<Account> hasRole(String role) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("role"), role);
    }
}
