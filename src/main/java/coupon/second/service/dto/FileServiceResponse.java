package coupon.second.service.dto;

import coupon.second.domain.entity.FileMeta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class FileServiceResponse {
    private Long id;
    private String storedPath;

    public static FileServiceResponse from(FileMeta fileMeta) {
        return FileServiceResponse.builder()
                .id(fileMeta.getId())
                .storedPath(fileMeta.getStoragePath())
                .build();
    }
}
