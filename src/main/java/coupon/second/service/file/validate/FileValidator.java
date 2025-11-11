package coupon.second.service.file.validate;

import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.IOException;

public interface FileValidator {

    void validate(File file) throws IOException, CsvValidationException;

}
