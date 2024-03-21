<%-- 
    Document   : index
    Created on : 2024. 3. 14., 오전 11:29:04
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, inital_scale=1.0">
        <title>Inculde Test 프로젝트</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        본문 내용이 들어갈 자리입니다.
        <jsp:directive.include file="/WEB-INF/jspf/footer.jspf" />
            
    </body>
</html>
