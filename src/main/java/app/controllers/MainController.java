package app.controllers;

import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/")
    public String loginPage(Model model) {
        return "login";
    }

    @RequestMapping(value = "/admin")
    public String adminPage(Model model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        model.addAttribute("listRoles", roleService.getAllRoles());
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        return "admin/adminDashboard";
    }

    @RequestMapping(value = "/user")
    public String userPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", auth.getPrincipal());
        return "user/mainPage";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user, Model model) {
        Set<Role> roles = roleService.getSetOfRoles(user.getRoles());
        User newUser = new User(user.getLogin(), user.getPassword());
        newUser.setRoles(roles);

        userService.addUser(newUser);
        roleService.addRole(roles);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute User user, Model model) {
        user.setRoles(roleService.getSetOfRoles(user.getRoles()));

        roleService.addRole(user.getRoles());
        userService.updateUser(user);

        return "redirect:/admin";
    }
}
