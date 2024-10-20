package ru.itmentor.spring.boot_security.demo.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.entity.UserEntity;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/register-page")
    public String registerPage() {
        return "registerPage";
    }

    @GetMapping("/users-list")
    public String listUsers(ModelMap modelMap) {
        List<UserEntity> userList = userService.findAll();
        modelMap.addAttribute("userList", userList);
        return "user_list";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditUserForm(@PathVariable int id, ModelMap modelMap) {
        userService.findById(id).ifPresent(user -> modelMap.addAttribute("user", user));
        return "user_form_edit";
    }

    @PostMapping("/users/edit")
    public String editUser(@ModelAttribute UserEntity user) {
        userService.update(user);
        return "redirect:/admin/users-list";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteById(id);
        return "redirect:/admin/users-list";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute UserEntity userEntity) {
        userService.save(userEntity);
        return "redirect:/";
    }
}



	
