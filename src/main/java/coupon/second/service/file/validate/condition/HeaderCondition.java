package coupon.second.service.file.validate.condition;

import coupon.second.common.exception.InvalidHeaderException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;

import java.util.List;


@Order(1)
@RequiredArgsConstructor
public class HeaderCondition implements HeaderValidateCondition {

    private final List<String> headers;

    @Override
    public void validate(String[] row) {
        validateHeaderNotNull(row);
        validateHeaderSize(row);
        validateHeaderValues(row);
    }

    private void validateHeaderNotNull(String[] row) {
        if (row == null) {
            throw new InvalidHeaderException("CSV 헤더가 존재하지 않습니다.");
        }
    }

    private void validateHeaderSize(String[] row) {
        if (row.length != headers.size()) {
            throw new InvalidHeaderException("헤더 길이가 다릅니다. 실제: " + row.length + ", 기대: " + headers.size()
            );
        }
    }

    private void validateHeaderValues(String[] row) {
        for (int i = 0; i < headers.size(); i++) {
            if (!headers.get(i).equals(row[i])) {
                throw  new InvalidHeaderException("헤더 검증 실패. 실제 : " + row[i] + ", 기대 : " + headers.get(i));
            }
        }
    }
}
