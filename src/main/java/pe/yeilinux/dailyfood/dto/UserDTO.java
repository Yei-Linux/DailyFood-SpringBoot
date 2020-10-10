package pe.yeilinux.dailyfood.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String user;
    private String email;
    private String password;
    public UserDTO() {}
    public UserDTO(String email, String password, String user,Long id) {
        this.email = email;
        this.password = password;
        this.user = user;
        this.id = id;
    }
}
