package pe.yeilinux.dailyfood.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.yeilinux.dailyfood.dto.UserDTO;
import pe.yeilinux.dailyfood.service.UserService;

import java.util.ArrayList;

@Service
public class UserSecurityServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = this.userService.getUserInformation(username);
        if(user == null){
            throw new UsernameNotFoundException("Error");
        }
        return new User(user.getEmail(),user.getPassword(), new ArrayList<>());
    }
}
