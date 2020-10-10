package pe.yeilinux.dailyfood.service;

import pe.yeilinux.dailyfood.domain.request.UserRequest;
import pe.yeilinux.dailyfood.dto.UserDTO;
import pe.yeilinux.dailyfood.entity.UserEntity;

public interface UserService {
    UserDTO getUserInformation(String email);
    UserEntity insertUser(UserRequest userRequest);
    void updateUser(UserRequest userRequest,Long userId);
}
