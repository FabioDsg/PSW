<%-- 
    Document   : dataServidor
    Created on : 22/05/2017, 21:23:14
    Author     : ra21503516
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Bem-vindo. A hora no Servidor é
        <%
            java.util.Calendar now = java.util.Calendar.getInstance();
            int hour = now.get(java.util.Calendar.HOUR_OF_DAY);
            int minute = now.get(java.util.Calendar.MINUTE);
            if (hour < 10) {
                out.println("0" + hour);
            } else {
                out.println(hour);
            }
            out.println(":");
            if (minute < 10) {
                out.println("0" + minute);
            } else {
                out.println(minute);
            }
        %>
    </body>
</html>
