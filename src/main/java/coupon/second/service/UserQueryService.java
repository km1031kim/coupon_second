package coupon.second.service;

import coupon.second.api.dto.UserResponse;
import coupon.second.api.dto.UserSearchCondition;
import coupon.second.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryService {

    private final UserRepository userRepository;

    public Page<UserResponse> searchAll(UserSearchCondition condition, Pageable pageable) {
        return userRepository.searchAll(condition, pageable);
    }
}
