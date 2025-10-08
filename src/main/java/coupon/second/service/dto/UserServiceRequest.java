package coupon.second.service.dto;

import coupon.second.common.enums.UserGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserServiceRequest {
    private String email;
    private String password;
    private UserGrade grade;


    public UserServiceRequest(UserGrade grade) {
        this.grade = grade;
    }
}
