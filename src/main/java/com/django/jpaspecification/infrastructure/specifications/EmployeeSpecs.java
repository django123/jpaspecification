package com.django.jpaspecification.infrastructure.specifications;

import com.django.jpaspecification.domain.Employee;
import com.django.jpaspecification.domain.Phone;
import com.django.jpaspecification.domain.PhoneType;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public final class EmployeeSpecs {

    public static Specification<Employee> getEmployeeByNickNameSpec(String nickname){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("nickname"),nickname);
        };
    }


    public static Specification<Employee> getEmployeeByFirstNameSpec(String firstName){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("firstName"),firstName);
        });
    }

    public static Specification<Employee> getEmployeeByLastName(String lastName){

        return ((root, query, criteriaBuilder) -> {
           return criteriaBuilder.equal(root.get("lastName"), lastName);
        });
    }

    public static Specification<Employee> getEmployeesByPhoneTypeSpec(PhoneType phoneType) {

        return (root, query, criteriaBuilder) -> {

            Join<Employee, Phone> phoneJoin = root.join("phones", JoinType.LEFT);

            return criteriaBuilder.equal(phoneJoin.get("type"), phoneType);

        };

    }

    //Employees by first or last name (would be possible to chain the ".equal" with ".and" or ".notNull")

    public static Specification<Employee> getEmployeesByFirstNameOrLastName(String firstName, String lastName) {

        return (root, query, criteriaBuilder) -> {

            return criteriaBuilder.or(

                    criteriaBuilder.equal(root.get("firstName"), firstName),

                    criteriaBuilder.equal(root.get("lastName"), lastName)

            );

        };

    }



    //Employees by nickname like %nick%
    public static Specification<Employee> getEmployeesByNicknameLike(String nick) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("nickname"), "%"+nick+"%");
        };


    }




}
