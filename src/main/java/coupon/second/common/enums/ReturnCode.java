package coupon.second.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ReturnCode {

    SUCCESS(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase()),
    CREATED(String.valueOf(HttpStatus.CREATED.value()), HttpStatus.CREATED.getReasonPhrase()),
    BAD_REQUEST(String.valueOf(HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST.getReasonPhrase()),
    UNAUTHORIZED(String.valueOf(HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED.getReasonPhrase()),
    FORBIDDEN(String.valueOf(HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN.getReasonPhrase()),
    NOT_FOUND(String.valueOf(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND.getReasonPhrase()),
    INTERNAL_ERROR(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
    CONFLICT(String.valueOf(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT.getReasonPhrase());


    private final String returnCode;
    private final String returnMessage;

}
