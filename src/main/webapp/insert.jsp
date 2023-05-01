<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<section>
		<div class="title">개 인 성 적 등 록</div>
		<div class="wrapper">
			<form name="frm" action="add">
				<table>
					<tr>
						<th>학번</th>
						<td>
						<input type="text" name="stid">
						</td>
					</tr>
					<tr>
						<th>과목코드</th>
						<td>
						<input type="text" name="subjectcode">예)SS01
						</td>
					</tr>
					<tr>
						<th>중간(30%)(0~100)</th>
						<td>
						<input type="text" name="mid">점
						</td>
					</tr>
					<tr>
						<th>기말(30%)(0~100)</th>
						<td>
						<input type="text" name="final">점
						</td>
					</tr>
					<tr>
						<th>출석(20%)(0~100)</th>
						<td>
						<input type="text" name="attend">점
						</td>
					</tr>
					<tr>
						<th>레포트(10%)(0~100)</th>
						<td>
						<input type="text" name="report">점
						</td>
					</tr>
					<tr>
						<th>기타(10%)(0~100)</th>
						<td>
						<input type="text" name="etc">점
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="insert" class="btn" onclick="fn_submit(); return false;">등 록</button>
							<button type="reset" class="btn" onclick="fn_reset();">다시쓰기</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>