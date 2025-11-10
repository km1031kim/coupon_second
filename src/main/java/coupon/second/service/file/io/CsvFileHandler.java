package coupon.second.service.file.io;

import coupon.second.service.file.validate.FileValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CsvFileHandler implements FileHandler {

    private final FileValidator fileValidator;

    @Override
    public boolean isSupported(String extension) {
        return "csv".equalsIgnoreCase(extension);
    }

    @Override
    public void process(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // 헤더값이 customer_id가 맞는지
            // 회원 목록이 비어있지 않은지
            // 각각의 회원번호가 유효한지는 검사하지 않습니다.
            String s = br.readLine();
            log.info(s);

        }
    }
}

