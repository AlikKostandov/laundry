package com.students.laundry.controllers;

import com.students.laundry.entities.Session;
import com.students.laundry.services.SessionService;
import com.students.laundry.utils.SessionFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;


@Controller
@AllArgsConstructor
public class HomeController {

    private final SessionService sessionService;

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
        
        try{
            LinkedList<String> paramFloor=(LinkedList) params.get("floor");
            if(paramFloor == null){
                paramFloor = new LinkedList<>();
                paramFloor.add("");
            }else {
                paramsLine.append("&floor=").append(paramFloor.get(0));
            }

            model.addAttribute("floor",paramFloor.get(0));
            
            
            LinkedList<String> paramSessionStatus=(LinkedList) params.get("status");
            if(paramSessionStatus == null){
                paramSessionStatus = new LinkedList<>();
                paramSessionStatus.add("");
            }else {
                paramsLine.append("&status=").append(paramSessionStatus.get(0));
            }
            model.addAttribute("status",paramSessionStatus.get(0));


            model.addAttribute("paramsLine",paramsLine);

        }catch (Exception e){}
        

        return "home-page";
    }

    @PostMapping("home/{id}")
    public String showSession(@PathVariable Long id, Model model) {
        model.addAttribute("sessions", sessionService.findById(id));
        return "redirect:/home";
    }

//    @GetMapping
//    public String showAllBooks(Model model,
//                               @RequestParam(name = "p", defaultValue = "1") Integer pageIndex,
//                               @RequestParam Map<String, String> params
//    ) {
//        BookFilter bookFilter = new BookFilter(params);
//        Page<Book> page = bookService.findAll(bookFilter.getSpec(), pageIndex - 1, 5);
//        model.addAttribute("books", page.getContent());
//        return "store-page";
//    }

}
