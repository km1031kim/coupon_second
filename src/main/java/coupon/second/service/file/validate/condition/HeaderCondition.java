package coupon.second.service.file.validate.condition;

import coupon.second.common.exception.InvalidHeaderException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class HeaderCondition implements HeaderValidateCondition {

    @Override
    public void validate(String[] row) {
        validateHeaderSize(row);
        validateHeaderValues(row);
    }

    private void validateHeaderSize(String[] row) {
        if (row.length != headers.size()) {
            throw new InvalidHeaderException("헤더 길이가 다릅니다. 헤더길이:" + headers.size() + ", 입력길이:" + row.length);
        }
    }

    private void validateHeaderValues(String[] row) {
        for (int i = 0; i < headers.size(); i++) {
            if (!headers.get(i).equals(row[i])) {
                throw new InvalidHeaderException("헤더 검증 실패. 헤더:" + headers.get(i) + ", 입력:" + row[i]);
            }
        }
    }
}
