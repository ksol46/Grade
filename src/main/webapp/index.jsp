<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<div class="title">성적관리 프로그램</div>
		<pre>
		성적관리 프로그램을 작성하는 프로그램이다.
		프로그램 작업순서
		1. 학생테이블을 생성한다.
		2. 교과목테이블을 생성한다.
		3. 성적테이블을 생성한다.
		4. 학생테이블, 교과목테이블, 성적테이블의 입력데이터들은 샘플데이터와 동일하게 입력하여 프로그램 작성과
		   테스트를 실시하여야 한다.
		5. 전체성적조회는 전체 학생의 성적 정보를 확인한다.
		6. 개인별성적통계는 개인의 총점 및 평균점수를 조회한다.
		</pre>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>