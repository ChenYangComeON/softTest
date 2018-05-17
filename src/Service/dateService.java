package Service;


public class dateService {

    public static String getNextDay(int year, int month, int day) {

        String res = null;

        //根据年月日 判断是否为闰年 月份是否为2月和大月
        boolean isLeap = false;
        boolean isTwo = false;
        boolean isBig = false;
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            isLeap = true;
        }

        if (month == 2) {
            isTwo = true;
            isBig = false;
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            isBig = true;
            isTwo = false;
        } else {
            isBig = false;
            isTwo = false;
        }
        //日期为最后一天
        if (year == 12 && month == 12 && day == 31) {
            res = (year + 1) + "-1-1";
        }
        //闰年的2.29
        else if (isLeap && isTwo && (day == 29)) {
            res = year + "-3-1";
        }
        //闰年的2.28
        else if (isLeap && isTwo && (day == 28)) {
            res = year + "-2-29";
        }
        //非闰年的2.28
        else if (!isLeap && isTwo && (day == 28)) {
            res = year + "-3-1";
        }
        //大月的30号
        else if (isBig && (day == 30)) {
            res = year + "-" + month + "-31";
        }
        //大月的31号
        else if (isBig && (day == 31)) {
            res = year + "-" + (month + 1) + "-1";
        }
        //小月的30号
        else if (!isBig && (day == 30)) {
            res = year + "-" + (month + 1) + "-1";
        }
        //其余的正常情况
        else {
            res = year + "-" + month + "-" + (day + 1);
        }

        return res;
    }
}
