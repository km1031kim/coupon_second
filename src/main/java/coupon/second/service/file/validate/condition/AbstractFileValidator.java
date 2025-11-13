package coupon.second.service.file.validate.condition;

import coupon.second.service.file.validate.FileValidator;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public abstract class AbstractFileValidator implements FileValidator {

    private final List<FileValidateCondition> conditions;

    protected void applyCondition(String[] row, Class<? extends FileValidateCondition> conditionType, int index) {
        for (FileValidateCondition condition : conditions) {
            if (conditionType.isInstance(condition)) {
                condition.validate(row, index);
            }
        }
    }
}
