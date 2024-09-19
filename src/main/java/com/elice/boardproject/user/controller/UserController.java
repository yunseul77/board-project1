package com.elice.boardproject.user.controller;

import com.elice.boardproject.user.dto.UserLoginDTO;
import com.elice.boardproject.user.dto.UserRequestDTO;
import com.elice.boardproject.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/signUp")
    public String showSignUpPage() {
        return "user/signUp";
    }

    // 회원가입
    @PostMapping("/signUp")
    public String signUp(@RequestParam String loginId,
                         @RequestParam String password,
                         @RequestParam String name,
                         @RequestParam String nickName) {

        UserRequestDTO userRequestDTO = new UserRequestDTO(loginId, password, name, nickName);
        userService.signUp(userRequestDTO);

        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "user/login";
    }

    // 로그인
    @PostMapping("/login")
    public String login(@ModelAttribute UserLoginDTO userLoginDTO) {
        userService.login(userLoginDTO);

        return "redirect:/post/postList";
    }
}
