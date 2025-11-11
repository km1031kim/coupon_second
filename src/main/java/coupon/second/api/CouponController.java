package coupon.second.api;

import com.opencsv.exceptions.CsvValidationException;
import coupon.second.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/file")
public class CouponController {
    /**
     * 1.  쿠폰 저장 -> 스트리밍 검증
     */

    private final FileService fileService;

    @PostMapping("/upload")
    public Long upload(@RequestPart MultipartFile file) throws CsvValidationException, IOException {
        log.info("[fileInfo] contentType : " + file.getContentType() + ", originalFilename : " + file.getOriginalFilename());
        fileService.upload(file);

        return null;
    }
}
