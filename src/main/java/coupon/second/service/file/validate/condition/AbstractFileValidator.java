package coupon.second.service.file.validate.condition;

import coupon.second.common.exception.InvalidRowException;
import coupon.second.common.util.ExcelRowReader;
import coupon.second.service.file.validate.FileValidator;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;

import java.util.Iterator;
import java.util.List;


@RequiredArgsConstructor
public abstract class AbstractFileValidator implements FileValidator {

    protected final List<FileValidateCondition> conditions;
    protected final ExcelRowReader excelRowReader;




}
