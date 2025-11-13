package coupon.second.common.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;

import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;

@Slf4j
@RequiredArgsConstructor
public class ExcelRowReader {
    private final DataFormatter formatter;


    /**
     * 헤더 리스트와 동일한 크기를 갖는 String 배열을 생성한 후 row 를 순회하며 배열을 채워나갑니다.
     * Null, Blank 셀의 경우 Cell 을 신규로 만들어서 반환합니다.
     * 헤더 및 로우의 길이 비교는 HeaderCondition 및 RowCondition에서 검증합니다.
     * @param row
     * @return String[] rowStr
     */
    public String[] readRowToStringArray(Row row) {
        String[] rowStr = new String[row.getLastCellNum()];
        for (int i = 0; i < rowStr.length; i++) {
            Cell cell = row.getCell(i, CREATE_NULL_AS_BLANK);
            rowStr[i] = formatter.formatCellValue(cell);
        }
        return rowStr;
    }

}
