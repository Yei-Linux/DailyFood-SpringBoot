package pe.yeilinux.dailyfood.domain.request;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DaySchemaRequest {
    private Long dayId;
    private Long userId;
    private Object weekSchema = this.getInitialSchema();

    public Map<String,Object> getInitialSchema() {
        Map<String,Object> daySchemaInitial = new HashMap<>();
        daySchemaInitial.put("monday",0);
        daySchemaInitial.put("thursday",0);
        daySchemaInitial.put("wednesday",0);
        daySchemaInitial.put("tuesday",0);
        daySchemaInitial.put("friday",0);
        daySchemaInitial.put("saturday",0);
        daySchemaInitial.put("sunday",0);
        return daySchemaInitial;
    }
}
