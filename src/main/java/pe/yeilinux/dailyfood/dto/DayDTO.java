package pe.yeilinux.dailyfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DayDTO {
    private Long dayId;
    private Long userId;
    private Object weekSchema;
}
