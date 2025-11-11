package coupon.second.service.file;

import coupon.second.service.file.io.CsvFileHandler;
import coupon.second.service.file.io.ExcelFileHandler;
import coupon.second.service.file.validate.CSVFileValidator;
import coupon.second.service.file.validate.ExcelFileValidator;
import coupon.second.service.file.validate.condition.FileValidateCondition;
import coupon.second.service.file.validate.condition.Header;
import coupon.second.service.file.validate.condition.HeaderCondition;
import coupon.second.service.file.validate.condition.NonEmptyRowCondition;
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
        return new ExcelFileHandler(excelFileValidator(conditions));
    }

    @Bean
    public CSVFileValidator csvFileValidator(List<FileValidateCondition> conditions) {
        return new CSVFileValidator(conditions);
    }

    @Bean
    public ExcelFileValidator excelFileValidator(List<FileValidateCondition> conditions) {
        return new ExcelFileValidator(conditions);
    }

    @Bean
    public NonEmptyRowCondition nonEmptyRowCondition() {
        return new NonEmptyRowCondition(Header.headers());
    }

    @Bean
    public HeaderCondition headerCondition() {
        return new HeaderCondition(Header.headers());
    }

}
