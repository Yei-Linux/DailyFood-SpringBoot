package pe.yeilinux.dailyfood.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class JpaConverterJsonHelper implements AttributeConverter<Object,String> {
    public final static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(Object attribute) {
        try{
            return objectMapper.writeValueAsString(attribute);
        }catch (JsonProcessingException e){
            return null;
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        try{
            return objectMapper.readValue(dbData, Object.class);
        }catch (IOException e) {
            return null;
        }
    }
}
