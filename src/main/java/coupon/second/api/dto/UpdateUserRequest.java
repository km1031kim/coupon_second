package coupon.second.api.dto;

import coupon.second.common.enums.UserGrade;
import coupon.second.service.dto.UserServiceRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateUserRequest {

    private UserGrade grade;

    public UserServiceRequest toServiceRequest() {
        return new UserServiceRequest(grade);
    }
}
