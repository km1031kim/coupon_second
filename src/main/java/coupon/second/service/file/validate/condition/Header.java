package coupon.second.service.file.validate.condition;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Header {
    CUSTOMER_ID("customer_id"),
    ADDRESS("address");

    private final String header;

    Header(String header) {
        this.header = header;
    }

    public static List<String> headers() {
        return Arrays.stream(Header.values()).map(Header::getHeader).toList();
    }
}
