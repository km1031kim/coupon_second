package coupon.second.service.file.validate;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import coupon.second.common.exception.InvalidHeaderException;
import coupon.second.service.file.validate.condition.FileValidateCondition;
import coupon.second.service.file.validate.condition.HeaderCondition;
import coupon.second.service.file.validate.condition.HeaderValidateCondition;
import coupon.second.service.file.validate.condition.RowValidateCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CSVFileValidator implements FileValidator {

    private final List<FileValidateCondition> conditions;

    @Override
    public void validate(File file) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(file)))) {
            String[] header = reader.readNext();
            for (FileValidateCondition condition : conditions) {
                if (condition instanceof HeaderValidateCondition) {
                    condition.validate(header);
                }
            }

            // CSVReader의 readNext() 메서드는 파일 끝에 도달시 null을 반환한다.
            // 파일 중간에서 null을 반환하는 상황과 분리해야 한다.
            while (true) {
                String[] row = reader.readNext();

                for (FileValidateCondition condition : conditions) {
                    if (condition instanceof RowValidateCondition) {
                        condition.validate(row);
                    }
                }
            }
        }
    }
}
