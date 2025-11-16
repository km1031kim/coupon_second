package coupon.second.service.file.upload;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobStorageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.UncheckedIOException;

@Slf4j
@RequiredArgsConstructor
public class AzureFileUploader implements FileUploader {

    private final BlobContainerClient blobContainerClient;

    @Override
    public String uploadFile(File localFile, String originalFilename) {
        BlobClient blobClient = blobContainerClient.getBlobClient(originalFilename);

        try {
            blobClient.uploadFromFile(localFile.getAbsolutePath(), false);
        } catch (BlobStorageException e) {
            log.info("Azure Blob Storage 예외 발생, Status : {}, Message : {}", e.getStatusCode(), e.getMessage());
            throw new RuntimeException("Azure Blob 업로드 실패", e);
        } catch (UncheckedIOException e) {
            throw new RuntimeException("로컬 파일 처리 오류 : " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("파일 업로드 중 알 수 없는 오류 발생", e);
        }
        return blobClient.getBlobUrl();
    }

    @Override
    public void deleteFile(String uploadPath) {
        BlobClient blobClient = blobContainerClient.getBlobClient(extractBlobNameFromUrl(uploadPath));

        try {
            boolean deleted = blobClient.deleteIfExists();
            if (!deleted) {
               log.warn("Azure Blob 삭제 실패. 파일이 존재하지 않거나 경로 오류: {}", uploadPath);
            }
        } catch (BlobStorageException e) {
            // Azure 서비스 레벨에서 예외 발생
            log.warn("Azure Blob Storage 삭제 중 오류 발생. 경로 : {}, 메세지 : {}",uploadPath, e.getMessage());
            throw new RuntimeException("Azure Blob 삭제 실패", e);
        }
    }

    private String extractBlobNameFromUrl(String fullUrl) {
        String containerUrl = blobContainerClient.getBlobContainerUrl();
        if (fullUrl.startsWith(containerUrl)) {
            return fullUrl.substring(containerUrl.length() + 1);
        } else {
            throw new IllegalArgumentException("제공된 URL이 현재 설정된 컨테이너 URL과 일치하지 않습니다: " + fullUrl);
        }
    }
}
