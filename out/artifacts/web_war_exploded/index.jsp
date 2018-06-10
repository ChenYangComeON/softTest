<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>日期查询工具</title>
    <script language="JavaScript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
</head>
<body style="text-align:center">
<form action="/shiftServlet" method="post">
    <div style="width:600px;margin-left:auto;margin-right:auto;">
        请选择查询日期：<input required name="date" class="Wdate" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"/>&nbsp;&nbsp;
        <input type="submit" value="查询">
        <br/>
    </div>
</form>

<div style="width:600px;margin-left:auto;margin-right:auto;">
    <%
        String today = (String) request.getAttribute("today");
        String week = (String) request.getAttribute("week");
        String lunar = (String) request.getAttribute("lunar");
        String nextDay = (String) request.getAttribute("nextDay");
        String nextWeek = (String) request.getAttribute("nextWeek");
        String nextLunar = (String) request.getAttribute("nextLunar");
        String lastDay = (String) request.getAttribute("lastDay");
        String lastWeek = (String) request.getAttribute("lastWeek");
        String lastLunar = (String) request.getAttribute("lastLunar");


        if (week == null) {
            week = "";
        }
        if (lunar == null) {
            lunar = "";
        }
        if (today == null) {
            today = "";
        }
        if (nextDay == null) {
            nextDay = "";
        }
        if (nextWeek == null) {
            nextWeek = "";
        }
        if (nextLunar == null) {
            nextLunar = "";
        }
    %>
    您选择的日期是：<%=today%>
    <br>
    今天是：<%=week%>
    <br>
    今天的农历日期：<%=lunar%>
    <br>
    明天的日期是：<%=nextDay%>
    <br>
    明天是：<%=nextWeek%>
    <br>
    明天的农历日期：<%=nextLunar%>
    <br>
</div>
</body>
</html>