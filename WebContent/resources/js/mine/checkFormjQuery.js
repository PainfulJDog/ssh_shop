/*
 * My97 DatePicker 4.72 Release
 * License: http://www.my97.net/dp/license.asp
 */
function checkFormByjQuery(){
	if($("#username").val()==''){
		alert("用户名不能为空jQ！");
		$("#username").focus();
		return false;
	}
	if($("#password").val()==''){
		alert("密码不能为空jQ！");
		$("#username").focus();
		return false;
	}
	if($("#password").val()!=$("#repassword").val()){
		alert("两次密码不一致jQ！");
		return false;
	}
}
