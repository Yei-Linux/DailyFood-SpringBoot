package pe.yeilinux.dailyfood.domain.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class UserRequest {
    private Long userId;
    private String user;
    private String password;
    private String email;
    private String dadLastName;
    private String momLastName;
    private String fullName;
    private String phone;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}
