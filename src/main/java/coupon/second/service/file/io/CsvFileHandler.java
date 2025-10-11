package coupon.second.service.file.io;

import coupon.second.service.file.validate.FileValidator;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@RequiredArgsConstructor
public class CsvFileHandler implements FileHandler {

    private final FileValidator fileValidator;



    @Override
    public boolean isSupported(String extension) {
        return "csv".equalsIgnoreCase(extension);
    }

    @Override
    public void process(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            long lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                String[] columns = line.split(",");

                // 검증 로직 호출
                fileValidator.validate(columns, lineNumber);
            }
        }
    }
}

