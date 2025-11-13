package coupon.second.service.file.validate;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import coupon.second.common.exception.InvalidRowException;
import coupon.second.service.file.validate.condition.AbstractFileValidator;
import coupon.second.service.file.validate.condition.FileValidateCondition;
import coupon.second.service.file.validate.condition.HeaderValidateCondition;
import coupon.second.service.file.validate.condition.RowValidateCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CSVFileValidator implements FileValidator {

    private final List<FileValidateCondition> conditions;


    @Override
    public void validate(File file) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(file)))) {

            Iterator<String[]> iterator = reader.iterator();
            checkNextRowExists(iterator);

            String[] header = iterator.next();
            applyCondition(header, HeaderValidateCondition.class);

            checkNextRowExists(iterator);

            while (iterator.hasNext()) {
                String[] row = iterator.next();
                applyCondition(row, RowValidateCondition.class);
            }
        }
    }


    protected void checkNextRowExists(Iterator<?> iterator) {
        if (!iterator.hasNext()) {
            throw new InvalidRowException("행 데이터가 존재하지 않습니다.");
        }
    }


    private void applyCondition(String[] row, Class<? extends FileValidateCondition> conditionType) {
        for (FileValidateCondition condition : conditions) {
            if (conditionType.isInstance(condition)) {
                condition.validate(row);
            }
        }
    }
}
