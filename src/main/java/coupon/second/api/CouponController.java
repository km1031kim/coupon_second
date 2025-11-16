package coupon.second.api;

import com.opencsv.exceptions.CsvValidationException;
import coupon.second.api.dto.ApiResponse;
import coupon.second.service.FileService;
import coupon.second.service.dto.FileServiceResponse;
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

    private final FileService fileService;

    @PostMapping("/upload")
    public ApiResponse<FileServiceResponse> upload(@RequestPart MultipartFile file) throws CsvValidationException, IOException {
        FileServiceResponse response = fileService.upload(file);
        return ApiResponse.of(response);
    }

    @GetMapping("/{fileId}")
    public ApiResponse<FileServiceResponse> findFile(@PathVariable("fileId") Long fileId) {
        FileServiceResponse response = fileService.find(fileId);
        return ApiResponse.of(response);
    }
}
