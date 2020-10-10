package pe.yeilinux.dailyfood.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GeneralResponse {
    private Object data;
    private String message;
}
