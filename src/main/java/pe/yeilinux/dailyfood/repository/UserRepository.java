package pe.yeilinux.dailyfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.yeilinux.dailyfood.dto.UserDTO;
import pe.yeilinux.dailyfood.entity.UserEntity;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET user= ?2 , dad_last_name = ?3, mom_last_name =?4, full_name =?5, phone= ?6 WHERE user_id =?1",nativeQuery = true)
    void updateUser(Long userId, String user, String dadLastName, String momLastName, String fullName, String phone);

    @Query(value="SELECT new pe.yeilinux.dailyfood.dto.UserDTO(user.email,user.password,user.user,user.userId) FROM UserEntity user WHERE user.email =?1")
    UserDTO getUserByEmail(String email);
}
