package com.elice.boardproject.user.dto;

import com.elice.boardproject.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    private Long id;
    private String loginId;
    private String password;

    public UserLoginDTO(User user) {
        this.id = user.getId();
        this.loginId = user.getLoginId();
        this.password = user.getPassword();
    }
}
