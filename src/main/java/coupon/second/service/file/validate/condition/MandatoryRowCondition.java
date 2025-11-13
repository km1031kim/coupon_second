package coupon.second.service.file.validate.condition;

import coupon.second.common.exception.InvalidRowException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MandatoryRowCondition implements RowValidateCondition {

    @Override
    public void validate(String[] row, int index) {
        validateRowSize(row, index);
        validateRowValues(row, index);
    }

    private void validateRowSize(String[] row, int index) {
        if (headers.size() != row.length) {
            throw new InvalidRowException((index + 1) + " 행의 입력 컬럼 수가 다릅니다. " +
                    "헤더 컬럼 수 : " + headers.size() + ", " + (index + 1) + " 행 컬럼 수 : " + row.length);
        }
    }


    private void validateRowValues(String[] row, int index) {
        for (int i = 0; i < row.length; i++) {
            String tuple = row[i];
            if (tuple.isBlank()) {
                throw new InvalidRowException((index + 1) + " 행 데이터가 비어있습니다. 컬럼: " + headers.get(i) + ", 값: '" + tuple + "'");
            }
        }
    }
}
