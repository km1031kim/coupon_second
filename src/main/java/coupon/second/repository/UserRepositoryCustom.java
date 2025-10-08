package coupon.second.repository;

import coupon.second.api.dto.UserResponse;
import coupon.second.api.dto.UserSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
    Page<UserResponse> searchAll(UserSearchCondition condition, Pageable pageable);
}
