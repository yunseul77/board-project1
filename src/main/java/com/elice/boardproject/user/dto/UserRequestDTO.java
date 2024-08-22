package com.elice.boardproject.user.dto;

import com.elice.boardproject.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String nickName;

    public UserRequestDTO(User user) {
        this.id = user.getId();
        this.loginId = user.getLoginId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.nickName = user.getNickName();
    }
}
