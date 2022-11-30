package com.students.laundry.services;


import com.students.laundry.entities.Session;
import com.students.laundry.exceptions.ResourceNotFoundException;
import com.students.laundry.repositories.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    public List<Session> getAllWindows() {
        return sessionRepository.findAll();
    }

    public Session findById(Long id) {
        return sessionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("session not found!"));
    }

    public Page<Session> getAllWindows(Specification<Session> spec, int page, int size) {
        return sessionRepository.findAll(spec, PageRequest.of(page, size));
    }





//    String curHour = "16:00";
//    Calendar cal = new GregorianCalendar();
//        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(curHour.substring(0, 2)));
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        System.out.println(cal.getTime());
}
