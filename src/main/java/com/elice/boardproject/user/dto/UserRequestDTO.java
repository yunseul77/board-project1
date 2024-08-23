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

    public User toEntity() {
        return User.builder()
                .id(this.id)
                .loginId(this.loginId)
                .password(this.password)
                .name(this.name)
                .nickName(this.nickName)
                .build();
    }
}
