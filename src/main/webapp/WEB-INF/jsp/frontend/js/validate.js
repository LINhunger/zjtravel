/**
 * @author Administrator
 */
function initValidator(base){
	
	$("#thisForm").validate({
		onkeyup:false,
		//设置验证规则   
		rules: {
			"username": {
				required: true,
				userNameCheck: true
			},
			"password": {
				required: true,
				rangelength: [6, 12]
			},
			"passwordAgain": {
				required: true,
				rangelength: [6, 12],
				equalTo: "#passWord"
			},
			"corpName": {
				required: true
			},
			"phone": {
				required: true,
				isMobile: true
			},
			"email": {
				required: true,
				isEmail: true
			}
		},
		//设置错误信息  
		messages: {
			"username": {
				required: "请输入用户名",
				userNameCheck: "用户名为4-20位的汉字、字符或数字"
			},
			"password": {
				required: "请输入密码",
				rangelength: "密码长度为6-12位"
			},
			"passwordAgain": {
				required: "请再次输入密码",
				rangelength: "密码长度为6-12位",
				equalTo: "两次输入密码不相同"
			},
			"corpName": {
				required: "请输入公司名称"
			},
			"phone": {
				required: "请输入手机号码",
				isMobile: "请输入有效的手机号码"
			},
			"email": {
				required: "请输入邮箱",
				isEmail: "请正确填写邮箱格式"
			}
		},
		errorElement:"font",
		errorPlacement: function(error, element){
			error.appendTo(element.parent().find(".tipinfo"));
		},success:"valid"
	});

}
