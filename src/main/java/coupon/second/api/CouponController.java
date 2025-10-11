package coupon.second.api;

import coupon.second.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public Long upload(@RequestParam MultipartFile file) throws IOException {
       fileService.upload(file);

        return null;
    }
}
