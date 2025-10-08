package coupon.second.api.dto;

import coupon.second.common.enums.UserGrade;
import lombok.Data;

@Data
public class UserSearchCondition {
    private UserGrade gradeGoe;
    private UserGrade gradeLoe;
    private String email;
    private Boolean userStatus;
}
