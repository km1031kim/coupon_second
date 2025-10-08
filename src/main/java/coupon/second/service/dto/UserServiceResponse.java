package coupon.second.service.dto;

import coupon.second.common.enums.UserGrade;
import coupon.second.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserServiceResponse {

    private Long id;
    private String email;
    private UserGrade grade;

    public static UserServiceResponse of(User user) {
        return new UserServiceResponse(user.getId(), user.getEmail(), UserGrade.fromLevel(user.getGradeLevel()));
    }
}

