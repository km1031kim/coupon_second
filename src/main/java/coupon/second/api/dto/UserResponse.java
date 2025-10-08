package coupon.second.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import coupon.second.common.enums.UserGrade;
import coupon.second.service.dto.UserServiceResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String email;
    private UserGrade grade;


    public static UserResponse of(UserServiceResponse serviceResponse) {
        return new UserResponse(serviceResponse.getId(), serviceResponse.getEmail(), serviceResponse.getGrade().getLevel());
    }

    @QueryProjection
    public UserResponse(Long id, String email, int gradeLevel) {
        this.id = id;
        this.email = email;
        this.grade = UserGrade.fromLevel(gradeLevel);
    }
}
