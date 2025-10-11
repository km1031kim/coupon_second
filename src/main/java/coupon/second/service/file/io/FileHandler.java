package coupon.second.service.file.io;

import java.io.File;
import java.io.IOException;

public interface FileHandler {


    boolean isSupported(String extension);

    void process(File file) throws IOException;

}
