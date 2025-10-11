package coupon.second.domain.entity;

import coupon.second.common.enums.StorageType;
import coupon.second.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileMeta extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String originalFilename;
    private String storedFilename;
    private String storagePath;
    private String extension;
    private String contentType;
    private long fileSize;

    @Enumerated(EnumType.STRING)
    private StorageType storageType;

    @Builder
    public FileMeta(String originalFilename,
                    String storedFilename,
                    String storagePath,
                    String extension,
                    String contentType,
                    long fileSize,
                    StorageType storageType) {
        this.originalFilename = originalFilename;
        this.storedFilename = storedFilename;
        this.storagePath = storagePath;
        this.extension = extension;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.storageType = storageType;
    }
}
