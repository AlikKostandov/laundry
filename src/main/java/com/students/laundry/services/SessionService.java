package com.students.laundry.services;


import com.students.laundry.entities.Session;
import com.students.laundry.exceptions.ResourceNotFoundException;
import com.students.laundry.repositories.SessionRepository;
import lombok.AllArgsConstructor;
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


//    String curHour = "16:00";
//    Calendar cal = new GregorianCalendar();
//        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(curHour.substring(0, 2)));
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        System.out.println(cal.getTime());
}
