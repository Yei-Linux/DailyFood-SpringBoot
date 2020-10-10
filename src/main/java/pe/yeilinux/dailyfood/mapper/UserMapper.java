package pe.yeilinux.dailyfood.mapper;

import org.modelmapper.ModelMapper;
import pe.yeilinux.dailyfood.domain.request.UserRequest;
import pe.yeilinux.dailyfood.entity.UserEntity;

public class UserMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public UserEntity convertToUserEntity(UserRequest userRequest) {
        return this.modelMapper.map(userRequest,UserEntity.class);
    }
}
