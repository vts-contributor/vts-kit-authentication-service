package auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class MessageResponse implements Serializable {
    private Object data;
    private String message;
    private int code;
}
