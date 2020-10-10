package pe.yeilinux.dailyfood.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.yeilinux.dailyfood.domain.DaySchema;
import pe.yeilinux.dailyfood.domain.request.DaySchemaRequest;
import pe.yeilinux.dailyfood.dto.DayDTO;
import pe.yeilinux.dailyfood.entity.DayEntity;
import pe.yeilinux.dailyfood.mapper.DayMapper;
import pe.yeilinux.dailyfood.repository.DayRepository;
import pe.yeilinux.dailyfood.service.DayService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DayServiceImpl implements DayService {
    @Autowired
    private DayRepository dayRepository;
    private DayMapper dayMapper = new DayMapper();

    @Override
    public DayDTO getDaySchemaByUserId(Long userId) {
        try{
            return this.dayRepository.getDaySchemaByUserId(userId);
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public void updateDaySchema(Long daySchemaId,DaySchemaRequest daySchemaRequest) {
        try{
            this.dayRepository.updateDaySchema(daySchemaId,daySchemaRequest.getUserId(),new ObjectMapper().writeValueAsString(daySchemaRequest.getWeekSchema()));
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public void updateDaySchemaByUserId(Long userId, DaySchemaRequest daySchemaRequest) {
        try{
            this.dayRepository.updateDaySchemaByUserId(userId,new ObjectMapper().writeValueAsString(daySchemaRequest.getWeekSchema()));
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public Map<String, Long> updateWeekSchema(List<DaySchema> weekSchema) {
        try{
            Map<String, Long> weekSchemaMap = new HashMap<>();
            for(DaySchema daySchema: weekSchema) {
                weekSchemaMap.put(daySchema.getDay(),daySchema.getFoodId());
            }
            return weekSchemaMap;
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public Map<String, Integer> updateWeekSchemaByDay(Map<String, Integer> weekSchema,String day,Integer foodId,Boolean isUpdate) {
        try {
            if (isUpdate) {
                Integer foodIdToChange = null;
                String dayToChange = "";
                for (Map.Entry<String, Integer> entry : weekSchema.entrySet()) {
                    if (entry.getKey().equalsIgnoreCase(day)) {
                        foodIdToChange = entry.getValue();
                    }
                    if (entry.getValue().equals(foodId)) {
                        dayToChange = entry.getKey();
                    }
                }
                for (Map.Entry<String, Integer> entry : weekSchema.entrySet()) {
                    if (entry.getKey().equalsIgnoreCase(day)) {
                        weekSchema.put(entry.getKey(), foodId);
                    }
                }
                if (!dayToChange.equalsIgnoreCase("")) {
                    weekSchema.put(dayToChange, foodIdToChange);
                } else {
                    weekSchema.put(dayToChange, 0);
                }
                return weekSchema;
            }
            for (Map.Entry<String, Integer> entry : weekSchema.entrySet()) {
                if (entry.getKey().equalsIgnoreCase(day)) {
                    weekSchema.put(entry.getKey(), foodId);
                }
            }
            return weekSchema;
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public DayEntity insertDaySchema(DaySchemaRequest daySchemaRequest) {
        try{
            return this.dayRepository.save(this.dayMapper.convertToDayEntity(daySchemaRequest));
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public void removeFoodDetailOfWeekSchema(Long userId, String day) {
        try {
            DayDTO dayDTO = this.getDaySchemaByUserId(userId);
            Map<String, Integer> weekSchemaMap = this.updateWeekSchemaByDay((Map<String,Integer>) dayDTO.getWeekSchema(),day,0,false);
            DaySchemaRequest daySchemaRequest = new DaySchemaRequest();
            daySchemaRequest.setWeekSchema(weekSchemaMap);
            this.updateDaySchemaByUserId(userId,daySchemaRequest);
        }catch (Exception e){
            throw new Error(e.getMessage());
        }
    }
}
