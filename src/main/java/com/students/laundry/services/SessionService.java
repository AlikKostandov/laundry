package com.students.laundry.services;


import com.students.laundry.entities.Session;
import com.students.laundry.entities.User;
import com.students.laundry.exceptions.ResourceNotFoundException;
import com.students.laundry.repositories.SessionRepository;
import com.students.laundry.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SessionService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    public List<Session> getAllWindows() {
        return sessionRepository.findAll();
    }

    public Session findById(Long id) {
        return sessionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("session not found!"));
    }

    public Page<Session> getAllWindows(Specification<Session> spec, int page, int size) {
        return sessionRepository.findAll(spec, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "sessionStartTime")));
    }


    @Transactional
    public void occupyWindow(Long id, User user){
        Session sessionFromDB = sessionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Session '%s' not found", id)));

        sessionFromDB.setUser(userRepository.findByPassNumber(user.getPassNumber()).get());
        sessionFromDB.setStatus(true);
        sessionRepository.save(sessionFromDB);
    }

    @Transactional
    public void cancelWindowOccupy(Long id){
        Session sessionFromDB = sessionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Session '%s' not found", id)));

        sessionFromDB.setUser(null);
        sessionFromDB.setStatus(false);
        sessionRepository.save(sessionFromDB);
    }



//    String curHour = "16:00";
//    Calendar cal = new GregorianCalendar();
//        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(curHour.substring(0, 2)));
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        System.out.println(cal.getTime());
}
