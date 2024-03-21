<%-- 
    Document   : index
    Created on : 2024. 3. 14., 오전 10:37:58
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDateTime" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset=UTF-8">
        <meta name="viewport" content="width=device-width, inital_scale=1.0">
        <title>page 지시어 사용 방법</title>
    </head>
    <body>
        지금 시간은 <%= LocalDateTime.now().toString()%>입니다.
        
        <%@ include file="/WEB-INF/jspf/main_footer.jspf"%>
        
    </body>
</html>
