package masterpian0.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import masterpian0.model.Role;
import masterpian0.model.User;
import masterpian0.service.RoleService;
import masterpian0.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/")
    public String showAll(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "admin/users";
    }

    @GetMapping("/admin/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/user";
    }

    @GetMapping("/admin/new")
    public String addUser(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @PostMapping("/admin/")
    public String create(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/admin/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        Set<Role> roles = new HashSet<>(roleService.getRoles());
        model.addAttribute("roles", roles);
        model.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
    }

    @PostMapping("/admin/{id}")
    public String updateUser(
            @ModelAttribute("user") User user,
            @RequestParam("roles") String[] roles
    ) {
        Set<Role> roleSet = new HashSet<>();
        for (String st : roles) {
            Role role = roleService.getRoleByName(st);
            roleSet.add(role);
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}


