package com.elice.boardproject.user.service;

import com.elice.boardproject.user.dto.UserLoginDTO;
import com.elice.boardproject.user.dto.UserRequestDTO;
import com.elice.boardproject.user.entity.User;
import com.elice.boardproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public void signUp(UserRequestDTO userRequestDTO) {
        userRepository.save(userRequestDTO.toEntity());
    }

    // 로그인
    public String login(UserLoginDTO userLoginDTO) {
        // 사용자 조회
        User user = userRepository.findByLoginId(userLoginDTO.getLoginId());
        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }

        // 비밀번호 검증
        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return "로그인 성공";
    }
}
