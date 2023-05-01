<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="DTO.dto" %>
<%
request.setCharacterEncoding("utf-8");
ArrayList<dto> list = new ArrayList<dto>();
list = (ArrayList<dto>) request.getAttribute("list");
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
		<div class="title">전체성적조회</div>
		<div class="wrapper">
		<table>
			<tr>
			<th>학번</th>
			<th>성명</th>
			<th>성별</th>
			<th>과목명</th>
			<th>전공구분</th>
			<th>담당교수</th>
			<th>중간</th>
			<th>기말</th>
			<th>출석</th>
			<th>레포트</th>
			<th>기타</th>
			<th>점수</th>
			<th>등급</th>
			</tr>
			<% for(dto d : list){ %>
			<tr>
			<td><%=d.getStid() %></td>
			<td><%=d.getStname() %></td>
			<td><%=d.getGender() %></td>
			<td><%=d.getSubject() %></td>
			<td><%=d.getClasses() %></td>
			<td><%=d.getProfessor() %></td>
			<td><%=d.getMid() %></td>
			<td><%=d.getFinall() %></td>
			<td><%=d.getAttend() %></td>
			<td><%=d.getReport() %></td>
			<td><%=d.getEtc() %></td>
			<td><%=d.getScore() %></td>
			<td><%=d.getGrade() %></td>
			</tr>
			<%} %>
		</table>
		</div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>