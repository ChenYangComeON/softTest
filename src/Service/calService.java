package Service;

public class calService {

    public static String calWeekDay(int year, int month, int day) {
        if (month == 1 || month == 2) {
            month += 12;
            year--;
        }

        int resWeek = (day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year / 100 + year / 400) % 7;

        resWeek++;

        return transformWeek(resWeek);
    }

    private static String transformWeek(int resWeek) {
        String res = null;
        switch (resWeek) {
            case 7:
                res = "星期日";
                break;
            case 1:
                res = "星期一";
                break;
            case 2:
                res = "星期二";
                break;
            case 3:
                res = "星期三";
                break;
            case 4:
                res = "星期四";
                break;
            case 5:
                res = "星期五";
                break;
            case 6:
                res = "星期六";
                break;
        }

        return res;
    }


}
