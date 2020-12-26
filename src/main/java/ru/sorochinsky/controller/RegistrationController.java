package ru.sorochinsky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sorochinsky.model.enums.Role;
import ru.sorochinsky.model.User;
import ru.sorochinsky.repositiry.UserRepositiry;

import java.util.Collections;
import java.util.Map;

/**
 * Controller for {@link Role} registration pages.
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

@Controller
public class RegistrationController {

    @Autowired
    private UserRepositiry userRepositiry;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = userRepositiry.findByUsername(user.getUsername());
        // проверка на уникальность
        if (userFromDb != null){
            model.put("massage", "User exists!");
            return "registration";
        }

        user.setActive(true);
//        Set<Role> roles = new HashSet<>();
//        roles.add(Role.USER);
        user.setRoles(Collections.singleton(Role.USER)); //or for set "roles"
        userRepositiry.save(user);
        return "redirect:/login";
    }
}
