package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.model.Role;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController (UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> showAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/showUser")
    public User showUser(Principal principal) {
        return userService.findByUsername(principal.getName());
    }


    @PostMapping("/add")
    public void create(@RequestBody User user) {
        userService.saveUser(user);
    }

    @GetMapping("/roles")
    public List<Role> roles() {
        return roleService.getAllRoles();
    }

    @PatchMapping("/update")
    public void update(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") long id) {
        userService.deleteUserById(id);
    }
}
