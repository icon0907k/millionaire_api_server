package afterwork.millionaire.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    /**
     * 날짜를 "yyyyMMdd" 형식으로 반환하는 메소드 (주말 제외한 전일)
     * @param isYesterday 전일을 구할지 여부 (true: 전일, false: 오늘)
     * @return 날짜를 "yyyyMMdd" 형식으로 반환
     */
    public static String getFormattedDate(boolean isYesterday) {
        // 한국 시간대 (Asia/Seoul)
        ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");

        // 현재 날짜를 한국 시간대 기준으로 구하기
        LocalDate date = LocalDate.now(koreaZoneId);

        // 전일 날짜 계산 (주말인 경우 금요일로 조정)
        if (isYesterday) {
            date = date.minusDays(1);

            // 전일이 일요일인 경우 금요일로 설정
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                date = date.minusDays(2);  // 일요일에서 2일 빼면 금요일
            }
            // 전일이 토요일인 경우 금요일로 설정
            else if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                date = date.minusDays(1);  // 토요일에서 1일 빼면 금요일
            }
        }

        // 날짜 형식 정의 (yyyyMMdd)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // 형식에 맞게 날짜 변환
        return date.format(formatter);
    }

    public static void main(String[] args) {
        // 예시: 전일 날짜 구하기
        System.out.println(getFormattedDate(true)); // 전일 날짜 (주말 제외)
    }
}
