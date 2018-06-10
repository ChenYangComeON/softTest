package Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {
    public static void main(String[] args) throws ParseException {
//        String host = "123.206.70.190";
//        String user = "root";
//        String password = "ddl753421";
//        String command = "cd ~ && python3 lunar.py -t";
//        String res = new SSHshell(host,user,password,22,command).exec();
//        System.out.println(res);
        /*Calendar now = Calendar.getInstance();
        Integer year = now.get(Calendar.YEAR);
        Integer month = now.get(Calendar.MONTH) + 1;
        Integer day = now.get(Calendar.DAY_OF_MONTH);
        System.out.println(year+"="+month+"="+day);*/

        //new Service.test().dateTest();
       /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
        System.out.println(sdf.format(new test().tomorrow(new Date())));
        System.out.println(sdf.format(new test().yesterday(new Date())));*/

        String host = "123.206.70.190";
        String user = "root";
        String password = "ddl753421";
        String command = "cd ~ && python3 lunar.py -t";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd F");
        String[] ymd =  sdf.format(new test().tomorrow(new Date())).split(" ");
        //System.out.println(sdf.format(new test().yesterday(new Date())));
        String command2 = "cd ~ && python3 lunar.py -y "+ymd[0] +  " -m "+ymd[1] + " -d " + ymd[2];
        String todayLunarDate = new SSHshell(host,user,password,22,command).exec();
        String nextLunarDate =  new SSHshell(host,user,password,22,command2).exec();

        System.out.println(todayLunarDate);
        System.out.println(nextLunarDate);

    }

    public void dateTest() {
        Date today = new Date();
        for(int i=0;i<10;i++) {
            today = yesterday(today);
            System.out.println(today);
        }
        System.out.println("------------");
        for(int i=0;i<10;i++) {
            today = tomorrow(today);
            System.out.println(today);
        }

    }

    /**
     * 返回昨天
     * @param today
     * @return
     */
    public Date yesterday(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        return calendar.getTime();
    }

    /**
     * 返回明天
     * @param today
     * @return
     */
    public Date tomorrow(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        return calendar.getTime();
    }
}
