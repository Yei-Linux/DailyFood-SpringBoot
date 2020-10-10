package pe.yeilinux.dailyfood.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pe.yeilinux.dailyfood.domain.request.UserRequest;
import pe.yeilinux.dailyfood.dto.UserDTO;
import pe.yeilinux.dailyfood.entity.UserEntity;
import pe.yeilinux.dailyfood.mapper.UserMapper;
import pe.yeilinux.dailyfood.repository.UserRepository;
import pe.yeilinux.dailyfood.service.DayService;
import pe.yeilinux.dailyfood.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DayService dayService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private UserMapper userMapper = new UserMapper();

    @Override
    public UserDTO getUserInformation(String email) {
        try {
            return this.userRepository.getUserByEmail(email);
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public UserEntity insertUser(UserRequest userRequest) {
        try {
            userRequest.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
            return this.userRepository.save(this.userMapper.convertToUserEntity(userRequest));
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public void updateUser(UserRequest userRequest,Long userId) {
        try {
            this.userRepository.updateUser(userId,userRequest.getUser(),userRequest.getDadLastName(),userRequest.getMomLastName(),userRequest.getFullName(),userRequest.getPhone());
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
