package coupon.second.service.file.validate;

import coupon.second.service.file.validate.condition.FileValidateCondition;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.List;

@RequiredArgsConstructor
public class ExcelFileValidator implements FileValidator {

    private final List<FileValidateCondition> conditions;


    @Override
    public void validate(File file) {

    }
}
