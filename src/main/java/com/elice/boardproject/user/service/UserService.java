package com.elice.boardproject.user.service;

import com.elice.boardproject.user.dto.UserRequestDTO;
import com.elice.boardproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 회원가입
    public void signUp(UserRequestDTO userRequestDTO) {
        userRepository.save(userRequestDTO.toEntity());
    }
}
