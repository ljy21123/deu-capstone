<%-- 
    Document   : index
    Created on : 2024. 3. 12., 오후 6:43:05
    Author     : USER
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>2장</title>
    </head>
    <body>

        <h1>첫 번째 JSP 웹 응용프로그램</h1>
        <hr>
        <br> 

        <p>
            1. JSP 주석: 	<%-- 볼 수 없음 --%>
        </p> 

        <p>
            2. HTML 주석:	<!-- 볼 수 있음 / 1 + 2 = ${1+2} -->
        </p> 

        <p>
            3. 선언(declaration) 사용: &nbsp;
            <%!
                int a = 1;
                int b = 2;

                public String getName() {
                    return "철수";
                }
            %>
            a + b = <%=a + b%>
        </p> 

        <p>
            4. 스크립트 표현식(expression): <br>
            현재 시각: <%=new java.util.Date()%>, <br>
            현재 시각 (since 1.8): <%= java.time.LocalDateTime.now().toString()%>, <br> 
            현재 날짜 (since 1.8, no Time): <%= java.time.LocalDate.now()%>, <br>
            현재 시각 (since 1.8, no Date): <%= java.time.LocalTime.now().toString()%>, <br>
            <%= getName()%>
        </p>

        <p>
            5. 스크립틀릿(scriptlet) 사용:
            <%
                int c = 3;
                int d = 4;
            %>

            c + d = 	<%=c + d%> 	<br>

            <%
                for (int i = 0; i < 5; i++) {
            %>
            i = <%=i%> &nbsp;
            <%
                }
            %>
        </p>

        <%@include file="/WEB-INF/jspf/main_footer.jspf"%>
    </body>
</html>