package ru.itmentor.spring.boot_security.demo.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itmentor.spring.boot_security.demo.entity.UserEntity;
import ru.itmentor.spring.boot_security.demo.security.CurrentUser;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String customLoginPage() {
        return "customLogin";
    }

    @GetMapping("/user")
    public String user(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        final UserEntity user = currentUser.getUser();
        final List<UserEntity> userList = userService.findByUsername(user.getUsername());
        modelMap.addAttribute("userList", userList);
        return "user";
    }

    @GetMapping("/default")
    public String redirect(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser.getAuthorities().stream().
                anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN"))) {
            return "redirect:/admin/users-list";
        } else {
            return "redirect:/user";
        }
    }
}


