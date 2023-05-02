function fn_reset () {
	alert("정보를 지우고 처음부터 다시 입력합니다!");
}

function fn_submit(){
	var fn = document.frm;
	
	if(fn.stid.value ==""){
	alert("학번이 입력되지 않았습니다!");
	fn.stid.focus();
	return false;
	}
	if(fn.dtcode.value ==""){
	alert("과목코드가 입력되지 않았습니다!");
	fn.dtcode.focus();
	return false;
	}
	if(fn.mid.value ==""){
	alert("중간(30%)(0~100)이 입력되지 않았습니다!");
	fn.mid.focus();
	return false;
	}
	if(fn.finall.value ==""){
	alert("기말(30%)(0~100)이 입력되지 않았습니다!");
	fn.finall.focus();
	return false;
	}
	if(fn.attend.value ==""){
	alert("출석(20%)(0~100)이 입력되지 않았습니다!");
	fn.attend.focus();
	return false;
	}
	if(fn.report.value ==""){
	alert("레포트(10%)(0~100)이 입력되지 않았습니다!");
	fn.report.focus();
	return false;
	}
	if(fn.etc.value ==""){
	alert("기타(10%)(0~100)이 입력되지 않았습니다!");
	fn.etc.focus();
	return false;
	}
	fn.submit();
}