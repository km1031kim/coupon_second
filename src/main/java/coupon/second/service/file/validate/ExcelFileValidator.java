package coupon.second.service.file.validate;

import coupon.second.common.exception.InvalidRowException;
import coupon.second.common.util.ExcelRowReader;
import coupon.second.service.file.validate.condition.FileValidateCondition;
import coupon.second.service.file.validate.condition.HeaderCondition;
import coupon.second.service.file.validate.condition.RowValidateCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ExcelFileValidator implements FileValidator {

    private final List<FileValidateCondition> conditions;
    private final ExcelRowReader excelRowReader;


    /**
     * XSSFSheet -> Iterator() -> next() 시 빈 셀은 건너뛴다.
     * row 인덱스에 접근해서 해결해야 함.
     * @param file
     * @throws IOException
     */
    @Override
    public void validate(File file) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file))) {
            XSSFSheet firstSheet = workbook.getSheetAt(0);
            XSSFRow headerRow = firstSheet.getRow(0);
            checkRowIsNotNull(headerRow);
            applyCondition(headerRow, HeaderCondition.class);

            int startRowIndex = headerRow.getRowNum() + 1;
            int lastRowIndex = firstSheet.getLastRowNum();
            for (int i = startRowIndex; i <= lastRowIndex; i++) {
                XSSFRow dataRow = firstSheet.getRow(i);
                checkRowIsNotNull(dataRow);
                applyCondition(dataRow, RowValidateCondition.class);
            }
        }
    }

    private void checkRowIsNotNull(Row row) {
        if (row == null) {
            throw new InvalidRowException("행 데이터가 존재하지 않습니다.");
        }
    }


    private void applyCondition(Row row, Class<? extends FileValidateCondition> conditionType) {
        String[] rowArray = excelRowReader.readRowToStringArray(row);

        for (FileValidateCondition condition : conditions) {
            if (conditionType.isInstance(condition)) {
                condition.validate(rowArray);
            }
        }
    }
}
