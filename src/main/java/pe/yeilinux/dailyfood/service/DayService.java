package pe.yeilinux.dailyfood.service;

import pe.yeilinux.dailyfood.domain.DaySchema;
import pe.yeilinux.dailyfood.domain.request.DaySchemaRequest;
import pe.yeilinux.dailyfood.dto.DayDTO;
import pe.yeilinux.dailyfood.entity.DayEntity;

import java.util.List;
import java.util.Map;

public interface DayService {
    DayDTO getDaySchemaByUserId(Long userId);
    void updateDaySchema(Long daySchemaId,DaySchemaRequest daySchemaRequest);
    void updateDaySchemaByUserId(Long userId,DaySchemaRequest daySchemaRequest);
    Map<String, Long> updateWeekSchema(List<DaySchema> weekSchema);
    Map<String, Integer> updateWeekSchemaByDay(Map<String, Integer> weekSchema,String day,Integer foodId,Boolean isUpdate) ;
    DayEntity insertDaySchema(DaySchemaRequest dayEntity);
    void removeFoodDetailOfWeekSchema(Long userId,String day);
}
