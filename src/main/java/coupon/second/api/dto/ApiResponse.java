package coupon.second.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import coupon.second.common.enums.ReturnCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

    private String returnCode;
    private String returnMessage;
    private T data;

    public static <T> ApiResponse<T> of(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.returnCode = ReturnCode.SUCCESS.getReturnCode();
        response.returnMessage = ReturnCode.SUCCESS.getReturnMessage();
        response.data = data;

        return response;
    }

    public static <T> ApiResponse<T> of(ReturnCode returnCode) {
        ApiResponse<T> response = new ApiResponse<>();
        response.returnCode = returnCode.getReturnCode();
        response.returnMessage = returnCode.getReturnMessage();
        return response;
    }

    public static <T> ApiResponse<T> of(T data, ReturnCode returnCode) {
        ApiResponse<T> response = new ApiResponse<>();
        response.returnCode = returnCode.getReturnCode();
        response.returnMessage = returnCode.getReturnMessage();
        response.data = data;
        return response;
    }

    public static <T> ApiResponse<T> of(ReturnCode returnCode, String returnMessage) {
        ApiResponse<T> response = new ApiResponse<>();
        response.returnCode = returnCode.getReturnCode();
        response.returnMessage = returnMessage;

        return response;
    }




}
