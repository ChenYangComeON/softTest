package servlet;

import Service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "shiftServlet")
public class shiftServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String todayDate = request.getParameter("date");
        String[] today_date = todayDate.split("-");

        int year = Integer.parseInt(today_date[0]);
        int month = Integer.parseInt(today_date[1]);
        int day = Integer.parseInt(today_date[2]);

        String today = year + "-" + month + "-" + day;

        String todayWeek = calService.calWeekDay(year, month, day);

        Solar solar = new Solar(day, month, year);
        Lunar lunar = SolartoLunar.SolarToLunar(solar);
        String[] lunar_today = {String.valueOf(lunar.lunarYear),
                String.valueOf(lunar.lunarMonth),
                String.valueOf(lunar.lunarDay),
                SolartoLunar.lunarYearToGanZhi(lunar.lunarYear)};
        String todayLunarDate = lunar_today[0] + "  " + lunar_today[1] + "  " + lunar_today[2] + "  " + lunar_today[3];


        String nextDay = dateService.getNextDay(year, month, day);
        String[] next_date = nextDay.split("-");

        int next_year = Integer.parseInt(next_date[0]);
        int next_month = Integer.parseInt(next_date[1]);
        int next_day = Integer.parseInt(next_date[2]);

        String nextWeek = calService.calWeekDay(next_year, next_month, next_day);
        Solar solar_next = new Solar(next_day, next_month, next_year);
        Lunar lunar1 = SolartoLunar.SolarToLunar(solar_next);
        String[] lunar_next = {String.valueOf(lunar1.lunarYear),
                String.valueOf(lunar1.lunarMonth),
                String.valueOf(lunar1.lunarDay),
                SolartoLunar.lunarYearToGanZhi(lunar1.lunarYear)};
        String nextLunarDate = lunar_next[0] + "  " + lunar_next[1] + "  " + lunar_next[2] + "  " + lunar_next[3];


        String host = "123.206.70.190";
        String user = "root";
        String password = "ddl753421";
        String command = "cd ~ && python3 lunar.py -t";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd F");
        String[] ymd =  sdf.format(new test().tomorrow(new Date())).split(" ");
        String[] lymd = sdf.format(new test().yesterday(new Date())).split(" ");
        String command2 = "cd ~ && python3 lunar.py -y "+ymd[0] +  " -m "+ymd[1] + " -d " + ymd[2];
        String command3 = "cd ~ && python3 lunar.py -y "+lymd[0] +  " -m "+lymd[1] + " -d " + lymd[2];
        todayLunarDate = new SSHshell(host,user,password,22,command).exec();
        nextLunarDate =  new SSHshell(host,user,password,22,command2).exec();
        String lastLunarDate = new SSHshell(host,user,password,22,command3).exec();
        String lastDay = lymd[0] + "-" + lymd[1] + "-" + lymd[2];
        String lastWeek = "星期"+lymd[3];

        request.setAttribute("today", today);
        request.setAttribute("week", todayWeek);
        request.setAttribute("lunar", todayLunarDate);
        request.setAttribute("nextDay", nextDay);
        request.setAttribute("nextWeek", nextWeek);
        request.setAttribute("nextLunar", nextLunarDate);
        /*request.setAttribute("lastDay", lastDay);
        request.setAttribute("lastWeek", lastWeek);
        request.setAttribute("lastLunar", lastLunarDate);*/

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
