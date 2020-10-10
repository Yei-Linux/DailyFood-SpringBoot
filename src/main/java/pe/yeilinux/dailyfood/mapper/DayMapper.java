package pe.yeilinux.dailyfood.mapper;

import org.modelmapper.ModelMapper;
import pe.yeilinux.dailyfood.domain.request.DaySchemaRequest;
import pe.yeilinux.dailyfood.entity.DayEntity;

public class DayMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public DayEntity convertToDayEntity(DaySchemaRequest daySchemaRequest) {
        return this.modelMapper.map(daySchemaRequest,DayEntity.class);
    }
}
