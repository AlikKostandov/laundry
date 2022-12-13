package com.students.laundry.controllers;

import com.students.laundry.entities.Session;
import com.students.laundry.entities.User;
import com.students.laundry.services.SessionService;
import com.students.laundry.services.UserService;
import com.students.laundry.utils.SessionFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.LinkedList;


@Controller
@AllArgsConstructor
public class HomeController {

    private final SessionService sessionService;

    private final UserService userService;

    @GetMapping("/home")
    public String showHome(Model model,
                           @RequestParam(name = "p", defaultValue = "1") Integer pageIndex,
                           @RequestParam(required = false) MultiValueMap params
    ) {
        //
        StringBuilder paramsLine = new StringBuilder("");
        SessionFilter sessionFilter = new SessionFilter(params);
        Page<Session> page = sessionService.getAllWindows(sessionFilter.getSpec(), pageIndex - 1, 9);
        model.addAttribute("sessions", page.getContent());

        try {
            LinkedList<String> paramFloor = (LinkedList) params.get("floor");
            if (paramFloor == null) {
                paramFloor = new LinkedList<>();
                paramFloor.add("");
            } else {
                paramsLine.append("&floor=").append(paramFloor.get(0));
            }

            model.addAttribute("floor", paramFloor.get(0));


            LinkedList<String> paramSessionStatus = (LinkedList) params.get("status");
            if (paramSessionStatus == null) {
                paramSessionStatus = new LinkedList<>();
                paramSessionStatus.add("");
            } else {
                paramsLine.append("&status=").append(paramSessionStatus.get(0));
            }
            model.addAttribute("status", paramSessionStatus.get(0));

            
            LinkedList<String> paramUserPassNumber=(LinkedList) params.get("passNumber");
            if(paramUserPassNumber == null){
                paramUserPassNumber = new LinkedList<>();
                paramUserPassNumber.add("");
            }else {
                paramsLine.append("&passNumber=").append(paramUserPassNumber.get(0));
            }
            model.addAttribute("passNumber",paramUserPassNumber.get(0));


            model.addAttribute("paramsLine", paramsLine);

        } catch (Exception e) {
        }


        return "home-page";
    }


    @GetMapping("home/{id}")
    public String showWindow(@PathVariable Long id, Model model) {
        model.addAttribute("ses", sessionService.findById(id));
        return "session-page";
    }


    @GetMapping("home/occupy/{id}")
    public String occupyWindow(@PathVariable Long id, Principal principal) {
        User user = userService.findByPassNumber(principal.getName()).get();
        sessionService.occupyWindow(id, user);
        return "redirect:/home";
    }

    @GetMapping("home/cancel/{id}")
    public String cancelOccupy(@PathVariable Long id, Principal principal) {
        User user = userService.findByPassNumber(principal.getName()).get();
        sessionService.cancelWindowOccupy(id);
        return "redirect:/home";
    }


}
