package coupon.second.service.file;

import coupon.second.common.util.ExcelRowReader;
import coupon.second.service.file.io.CsvFileHandler;
import coupon.second.service.file.io.ExcelFileHandler;
import coupon.second.service.file.validate.CSVFileValidator;
import coupon.second.service.file.validate.ExcelFileValidator;
import coupon.second.service.file.validate.condition.FileValidateCondition;
import coupon.second.service.file.validate.condition.HeaderCondition;
import coupon.second.service.file.validate.condition.MandatoryRowCondition;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FileServiceBeanConstructor {

    @Bean
    public FileService fileService(CsvFileHandler csvFileHandler, ExcelFileHandler excelFileHandler) {
        return new FileService(List.of(csvFileHandler, excelFileHandler));
    }

    @Bean
    public CsvFileHandler csvFileHandler(List<FileValidateCondition> conditions) {
        return new CsvFileHandler(csvFileValidator(conditions));
    }

    @Bean
    public ExcelFileHandler excelFileHandler(List<FileValidateCondition> conditions) {
        return new ExcelFileHandler(excelFileValidator(conditions, excelRowReader()));
    }

    @Bean
    public CSVFileValidator csvFileValidator(List<FileValidateCondition> conditions) {
        return new CSVFileValidator(conditions);
    }

    @Bean
    public ExcelFileValidator excelFileValidator(List<FileValidateCondition> conditions, ExcelRowReader excelRowReader) {
        return new ExcelFileValidator(conditions, excelRowReader);
    }

    @Bean
    public MandatoryRowCondition nonEmptyRowCondition() {
        return new MandatoryRowCondition();
    }

    @Bean
    public HeaderCondition headerCondition() {
        return new HeaderCondition();
    }

    @Bean
    public ExcelRowReader excelRowReader() {
        return new ExcelRowReader(new DataFormatter());
    }

}
