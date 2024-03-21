<%-- 
    Document   : index2
    Created on : 2024. 3. 14., 오전 11:02:44
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="show_error.jsp"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, inital_scale=1.0">
        <title>page 지시어의 오류 관련 속성 사용 예제</title>
    </head>
    <body>
        <%= 5 / 0 %>
        
        <%@ include file="/WEB-INF/jspf/main_footer.jspf" %>
    </body>
</html>
