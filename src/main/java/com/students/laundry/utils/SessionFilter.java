package com.students.laundry.utils;

import com.students.laundry.entities.Session;
import com.students.laundry.repositories.specifications.SessionSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;

@Getter
public class SessionFilter {
    private Specification<Session> spec;
    private String filterParams;

    public SessionFilter(MultiValueMap params) {
        spec = Specification.where(null);

        if (params.containsKey("floor")) {
            ArrayList<String> filters = (ArrayList<String>) params.get("floor");
            String floor = filters.get(0);
            if (!floor.isEmpty() && floor != null) {
                spec = spec.and(SessionSpecifications.floorEqual(Integer.parseInt(floor)));
            }
        }

        if (params.containsKey("status")) {
            ArrayList<String> filters = (ArrayList<String>) params.get("status");
            boolean status;
            status = filters.get(0).equals("true");
            spec = spec.and(SessionSpecifications.sessionStatusLike(status));


        }
    }
}