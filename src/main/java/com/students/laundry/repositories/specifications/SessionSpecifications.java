package com.students.laundry.repositories.specifications;


import com.students.laundry.entities.Session;
import org.springframework.data.jpa.domain.Specification;

public class SessionSpecifications {

    public static Specification<Session> floorEqual(int floor) {
        return (Specification<Session>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("floor"), floor);  // where b.price >= minPrice
    }

    public static Specification<Session> sessionStatusLike(boolean status) {
        return (Specification<Session>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }
}
