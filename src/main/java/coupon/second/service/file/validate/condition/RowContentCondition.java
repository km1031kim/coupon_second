package coupon.second.service.file.validate.condition;

import coupon.second.common.exception.InvalidRowException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RowContentCondition implements RowValidateCondition {

    private final List<String> headers;

    @Override
    public void validate(String[] row) {
        validateRowSize(row);
        validateRowValues(row);
    }

    private void validateRowSize(String[] row) {
        if (headers.size() != row.length) {
            throw new InvalidRowException("행 길이가 다릅니다. 실제 : " + row.length + ", 기대 : " + headers.size());
        }
    }

    private void validateRowValues(String[] row) {
        for (int i = 0; i < row.length; i++) {
            String tuple = row[i];
            if (tuple.isBlank()) {
                throw new InvalidRowException("행 데이터가 비어있습니다. 컬럼: " + headers.get(i) + ", 값: '" + tuple + "'");
            }
        }
    }
}
