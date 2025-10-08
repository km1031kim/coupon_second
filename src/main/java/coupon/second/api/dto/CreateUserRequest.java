package coupon.second.api.dto;

import coupon.second.common.enums.UserGrade;
import coupon.second.service.dto.UserServiceRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateUserRequest {

    @NotBlank @Email
    private String email;

    @NotBlank
    private String password;

    private UserGrade grade;

    public UserServiceRequest toServiceRequest() {

        return Optional.ofNullable(grade)
                .map(grade -> new UserServiceRequest(email, password, grade))
                .orElseGet(() -> new UserServiceRequest(email, password, UserGrade.BRONZE));

    }
}
