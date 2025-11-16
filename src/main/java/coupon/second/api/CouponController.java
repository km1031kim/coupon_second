package coupon.second.api;

import com.opencsv.exceptions.CsvValidationException;
import coupon.second.api.dto.ApiResponse;
import coupon.second.common.enums.ReturnCode;
import coupon.second.service.dto.FileServiceResponse;
import coupon.second.service.FileService;
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
        FileServiceResponse fileServiceResponse = fileService.upload(file);
        return ApiResponse.of(fileServiceResponse);
    }
}
