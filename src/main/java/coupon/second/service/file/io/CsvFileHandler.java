package coupon.second.service.file.io;

import com.opencsv.exceptions.CsvValidationException;
import coupon.second.service.file.validate.CSVFileValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CsvFileHandler implements FileHandler {

    private final CSVFileValidator csvFileValidator;

    @Override
    public boolean isSupported(String extension) {
        return "csv".equalsIgnoreCase(extension);
    }

    @Override
    public void process(File file) throws CsvValidationException, IOException {
        csvFileValidator.validate(file);
    }
}

