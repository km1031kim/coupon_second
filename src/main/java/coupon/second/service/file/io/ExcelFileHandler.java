package coupon.second.service.file.io;

import coupon.second.service.file.validate.ExcelFileValidator;
import coupon.second.service.file.validate.FileValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

@Slf4j
@RequiredArgsConstructor
public class ExcelFileHandler implements FileHandler {

    private final ExcelFileValidator excelFileValidator;

    @Override
    public boolean isSupported(String extension) {
        return "xlsx".equalsIgnoreCase(extension) || "xls".equalsIgnoreCase(extension);

    }

    @Override
    public void process(File file) throws IOException {
        excelFileValidator.validate(file);
    }
}
