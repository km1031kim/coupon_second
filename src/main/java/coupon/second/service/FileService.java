package coupon.second.service;

import com.opencsv.exceptions.CsvValidationException;
import coupon.second.domain.entity.FileMeta;
import coupon.second.repository.FileMetaRepository;
import coupon.second.service.dto.FileServiceResponse;
import coupon.second.service.file.io.FileHandler;
import coupon.second.service.file.upload.FileUploader;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileService {
    private final List<FileHandler> handlerList;
    private final FileUploader fileUploader;
    private final FileMetaRepository fileMetaRepository;

    @Value("${app.file.local-dir}")
    private String localUploadDir;

    @Transactional
    public FileServiceResponse upload(MultipartFile file) throws CsvValidationException, IOException {
        log.info("upload 호출");

        String originalFilename = file.getOriginalFilename();
        checkFileName(originalFilename);
        String extension = extractExtension(originalFilename);

        File localFile = new File(localUploadDir, file.getOriginalFilename());
        file.transferTo(localFile);

        String uploadPath = null;
        FileHandler handler = getHandler(extension);


        try {
            checkDuplicatedFilename(originalFilename);
            handler.process(localFile);

            log.info("업로드 시작 전");
            uploadPath = fileUploader.uploadFile(localFile, originalFilename);

            FileMeta fileMeta = FileMeta.builder()
                    .originalFilename(originalFilename)
                    .storagePath(uploadPath)
                    .extension(extension)
                    .contentType(file.getContentType())
                    .fileSize(localFile.length())
                    .build();

            FileMeta savedFileMeta = fileMetaRepository.save(fileMeta);

            return FileServiceResponse.from(savedFileMeta);
        } catch (RuntimeException e) {
            if (uploadPath != null) {
                log.info("Blob파일 삭제 시작.");
                fileUploader.deleteFile(uploadPath); // 이미 올라간 파일은 직접 지워줘야함.
            }
            throw e; // 예외를 다시 던져야 트랜잭션 롤백 확정.
        } finally {
            if (localFile.exists()) {
                boolean deleted = localFile.delete();
                if (!deleted) {
                   log.warn("로컬 파일 삭제 실패. 파일명 : {}", localFile.getAbsolutePath());
                } else {
                    log.info("로컬 파일 삭제 완료. 파일명 : {}", localFile.getAbsolutePath());
                }
            }
        }
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

    private void checkDuplicatedFilename(String originalFilename) {
        boolean isExist = fileMetaRepository.existsByOriginalFilename(originalFilename);
        if (isExist) {
            throw new EntityExistsException("같은 이름의 파일이 이미 존재합니다.");
        }
    }
}
