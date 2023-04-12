package auth.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpFormRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
