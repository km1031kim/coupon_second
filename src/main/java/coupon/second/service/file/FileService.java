package coupon.second.service.file;

import com.opencsv.exceptions.CsvValidationException;
import coupon.second.service.file.io.FileHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class FileService {
    private final List<FileHandler> handlerList;
    private static final String TEMP_PREFIX = "temp-";

    @Value("${app.file.local-dir}")
    private String localUploadDir;

    public FileService(List<FileHandler> handlerList) {
        this.handlerList = handlerList;
    }

    public String upload(MultipartFile file) throws CsvValidationException, IOException {

        String originalFilename = file.getOriginalFilename();
        checkFileName(originalFilename);

        String extension = extractExtension(originalFilename);

        File tempFile = new File(localUploadDir, file.getOriginalFilename());
        file.transferTo(tempFile);


        FileHandler handler = getHandler(extension);
        try {
            handler.process(tempFile);
            log.info("파일처리 끝");
        } finally {
            tempFile.delete();
        }

        return tempFile.getAbsolutePath();
    }


    private void checkFileName(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("파일 이름이 없습니다.");
        }
    }

    private String extractExtension(String originalFilename) {
        int dotIndex = originalFilename.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == originalFilename.length() - 1) {
            throw new IllegalArgumentException("확장자가 없습니다: " + originalFilename);
        }
        return originalFilename.substring(dotIndex + 1).toLowerCase();
    }


    private FileHandler getHandler(String extension) {
        Optional<FileHandler> handler = handlerList.stream()
                .filter(h -> h.isSupported(extension))
                .findFirst();
        
        if (handler.isEmpty()) {
            throw new IllegalArgumentException("지원하지 않는 파일 확장자: " + extension);
        }

        return handler.get();
    }
}
