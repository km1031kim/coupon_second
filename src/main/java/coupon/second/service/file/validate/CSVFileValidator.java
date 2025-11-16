package coupon.second.service.file.validate;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import coupon.second.common.exception.InvalidHeaderException;
import coupon.second.common.exception.InvalidRowException;
import coupon.second.service.file.validate.condition.AbstractFileValidator;
import coupon.second.service.file.validate.condition.FileValidateCondition;
import coupon.second.service.file.validate.condition.HeaderValidateCondition;
import coupon.second.service.file.validate.condition.RowValidateCondition;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class CSVFileValidator extends AbstractFileValidator {

    public CSVFileValidator(List<FileValidateCondition> conditions) {
        super(conditions);
    }

    @Override
    public void validate(File file) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(file)))) {
            int index = 0;
            Iterator<String[]> iterator = reader.iterator();
            checkNextRowExists(iterator, index);

            String[] header = iterator.next();
            applyCondition(header, HeaderValidateCondition.class, index);

            checkNextRowExists(iterator, ++index);

            while (iterator.hasNext()) {
                String[] row = iterator.next();
                applyCondition(row, RowValidateCondition.class, index);
                index++;
            }
        }
    }

    private void checkNextRowExists(Iterator<?> iterator, int index) {
        if (!iterator.hasNext()) {
            if (index == 0) {
                throw new InvalidHeaderException("헤더가 존재하지 않습니다.");
            }
            throw new InvalidRowException(index + " 행 데이터가 존재하지 않습니다.");
        }
    }
}
