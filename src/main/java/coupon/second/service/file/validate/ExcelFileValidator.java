package coupon.second.service.file.validate;

import coupon.second.common.util.ExcelRowReader;
import coupon.second.service.file.validate.condition.AbstractFileValidator;
import coupon.second.service.file.validate.condition.FileValidateCondition;
import coupon.second.service.file.validate.condition.HeaderCondition;
import coupon.second.service.file.validate.condition.RowValidateCondition;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class ExcelFileValidator extends AbstractFileValidator {

    private final ExcelRowReader excelRowReader;

    public ExcelFileValidator(List<FileValidateCondition> conditions, ExcelRowReader excelRowReader) {
        super(conditions);
        this.excelRowReader = excelRowReader;
    }

    @Override
    public void validate(File file) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file))) {
            XSSFSheet firstSheet = workbook.getSheetAt(0);

            XSSFRow headerRow = firstSheet.getRow(0);
            excelRowReader.checkRowIsNotNull(headerRow, 0);
            String[] header = excelRowReader.readRowToStringArray(headerRow);
            applyCondition(header, HeaderCondition.class, 0);

            int startRowIndex = headerRow.getRowNum() + 1;
            int lastRowIndex = firstSheet.getLastRowNum();
            for (int i = startRowIndex; i <= lastRowIndex; i++) {
                XSSFRow dataRow = firstSheet.getRow(i);
                excelRowReader.checkRowIsNotNull(dataRow, i);
                String[] row = excelRowReader.readRowToStringArray(dataRow);
                applyCondition(row, RowValidateCondition.class, i);
            }
        }
    }
}
