<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MySQL 데이터 가져오기</title>
</head>
<body>
    <h1>MySQL 데이터 가져오기 예제</h1>
    <%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet" %>
    <%
        // MySQL 데이터베이스 연결 정보 설정
        String url = "jdbc:mysql://localhost:3306/capstone";
        String username = "root";
        String password = "1234";

        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결
            Connection conn = DriverManager.getConnection(url, username, password);

            // SQL 쿼리 실행
            String sql = "SELECT * FROM test";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // 결과 출력
            out.println("<ul>");
            while (resultSet.next()) {
                out.println("<li>" + resultSet.getString("msg") + "</li>");
            }
            out.println("</ul>");

            // 리소스 해제
            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            out.println("데이터베이스 연결 또는 쿼리 실행 중 오류 발생: " + e.getMessage());
        }
    %>
</body>
</html>
