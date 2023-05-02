<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="DTO.dto"  %>
<%
request.setCharacterEncoding("utf-8");
ArrayList<dto> slist = new ArrayList<dto>();
slist = (ArrayList<dto>) request.getAttribute("slist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<section>
		<div class="title">개인별 성적통계</div>
		<div class="wrapper">
		<table style="">
			<tr>
			<th>학위과정</th>
			<th>학번</th>
			<th>성명</th>
			<th>수강교과목수</th>
			<th>총점</th>
			<th>평균</th>
			</tr>
			<% for (dto d : slist) { %>
			<tr>
			<td><%=d.getCourse() %></td>
			<td><%=d.getStid() %></td>
			<td><%=d.getStname() %></td>
			<td><%=d.getSubcount() %></td>
			<td><%=d.getTotalscore() %></td>
			<td><%=d.getAverage() %></td>
			</tr>
			<%} %>
		</table>
		</div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>