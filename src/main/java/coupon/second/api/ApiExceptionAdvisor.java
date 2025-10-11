package coupon.second.api;

import coupon.second.api.dto.ApiResponse;
import coupon.second.common.enums.ReturnCode;
import coupon.second.common.exception.UserAlreadyInactiveException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionAdvisor {


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponse<?> handleHttpMessageNotReadableException() {
        return ApiResponse.of(ReturnCode.BAD_REQUEST, "요청 바디가 비어 있거나 형식이 잘못되었습니다.");
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> result = e.getFieldErrors().stream().collect(Collectors.toMap(
                FieldError::getField,
                fe -> Objects.requireNonNullElse(fe.getDefaultMessage(), "Validation Failed")
        ));

        return ApiResponse.of(result, ReturnCode.BAD_REQUEST);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ApiResponse<?> handleEntityExistsException() {
        return ApiResponse.of(ReturnCode.CONFLICT, "user is already existed");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ApiResponse<?> handleEntityNotFoundException() {
        return ApiResponse.of(ReturnCode.BAD_REQUEST, "no user is found");
    }

    @ExceptionHandler(UserAlreadyInactiveException.class)
    public ApiResponse<?> handleUserAlreadyInactiveException() {
        return ApiResponse.of(ReturnCode.BAD_REQUEST, "user is already inactivated");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ApiResponse<?> handleMaxSizeException() {
        return ApiResponse.of(ReturnCode.BAD_REQUEST, "파일 크기가 너무 큽니다. 최대 100MB까지 가능합니다.");
    }
}
