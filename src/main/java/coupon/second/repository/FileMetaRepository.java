package coupon.second.repository;

import coupon.second.domain.entity.FileMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMetaRepository extends JpaRepository<FileMeta, Long> {

    boolean existsByOriginalFilename(String originalFilename);
}
