package coupon.second.service.file.validate;

import coupon.second.service.file.validate.condition.FileValidateCondition;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FileValidator {

    private final List<FileValidateCondition> conditions;

    public void validate(String[] columns, long lineNumber) {
        if (lineNumber == 1) {
            // 헤더 검증만 수행
        } else {
            // 데이터 검증 수행
        }
    }
}
