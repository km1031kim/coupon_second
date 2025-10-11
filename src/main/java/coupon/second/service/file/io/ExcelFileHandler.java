package coupon.second.service.file.io;

import coupon.second.service.file.validate.FileValidator;
import lombok.RequiredArgsConstructor;

import java.io.File;

@RequiredArgsConstructor
public class ExcelFileHandler implements FileHandler {

    private final FileValidator fileValidator;

    @Override
    public boolean isSupported(String extension) {
        return "xlsx".equalsIgnoreCase(extension) || "xls".equalsIgnoreCase(extension);

    }

    @Override
    public void process(File file) {
    }
}
