package coupon.second.service.file;

import coupon.second.service.file.io.CsvFileHandler;
import coupon.second.service.file.io.ExcelFileHandler;
import coupon.second.service.file.validate.FileValidator;
import coupon.second.service.file.validate.condition.HeaderCondition;
import coupon.second.service.file.validate.condition.MemberListNotEmptyCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FileBeanConstructor {

    @Bean
    public FileValidator fileValidator() {
        return new FileValidator(List.of(
                new HeaderCondition(),
                new MemberListNotEmptyCondition()
        ));
    }

    @Bean
    public CsvFileHandler csvFileHandler(FileValidator fileValidator) {
        return new CsvFileHandler(fileValidator);
    }

    @Bean
    public ExcelFileHandler excelFileHandler(FileValidator fileValidator) {
        return new ExcelFileHandler(fileValidator);
    }

    @Bean
    public FileService fileService(CsvFileHandler csvFileHandler, ExcelFileHandler excelFileHandler) {
        return new FileService(List.of(csvFileHandler, excelFileHandler));
    }
}
