package coupon.second.api;

import coupon.second.api.dto.*;
import coupon.second.common.enums.ReturnCode;
import coupon.second.service.UserQueryService;
import coupon.second.service.UserService;
import coupon.second.service.dto.UserServiceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserQueryService userQueryService;

    @PostMapping
    public ApiResponse<UserResponse> register(@RequestBody @Valid CreateUserRequest request) {
        UserServiceResponse serviceResponse = userService.save(request.toServiceRequest());
        return ApiResponse.of(UserResponse.of(serviceResponse));
    }

    @GetMapping
    public ApiResponse<Page<UserResponse>> findUsers(@RequestBody UserSearchCondition condition, Pageable pageable) {
        return ApiResponse.of(userQueryService.searchAll(condition, pageable));
    }


    @PatchMapping("/{userId}")
    public ApiResponse<UserResponse> update(@PathVariable("userId") Long userId, @RequestBody UpdateUserRequest request) {
        UserServiceResponse serviceResponse = userService.update(userId, request.toServiceRequest());
        return ApiResponse.of(UserResponse.of(serviceResponse));
    }


    @DeleteMapping("/{userId}")
    public ApiResponse<?> delete(@PathVariable("userId") Long userId) {
        userService.delete(userId);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }
}
