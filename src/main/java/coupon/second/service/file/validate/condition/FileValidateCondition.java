package coupon.second.service.file.validate.condition;

import java.util.List;

public interface FileValidateCondition {
    List<String> headers = Header.headers();

    void validate(String[] row, int index);
}
