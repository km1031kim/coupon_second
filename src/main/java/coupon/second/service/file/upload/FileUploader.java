package coupon.second.service.file.upload;

import java.io.File;

public interface FileUploader {

    String uploadFile(File file, String storedFilename);

    void deleteFile(String uploadPath);
}
