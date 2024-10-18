package javaAuthentication.app.properAuthentication.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private String userName;
    private Long userId;

    private String email;
    private String password;
    private LocalDateTime date = LocalDateTime.now();

}
