function checkchina(str){
	var reg = /^[\u0391-\uFFE5]+$/;
    return reg.test(str);
} 

function checkenglish(str){
	var reg = /^[A-Za-z]+$/;
    return reg.test(str);
} 


function checkpassword(str){
	var  password=jQuery("#password").val();
	var  againpassword =jQuery("#againpassword").val();
	 if(password!= againpassword){
       return false;
    }
    return true;
}


function checkdata(str){
	var reg = /^[A-Za-z0-9]+$/;
    return reg.test(str);
} 


function checkemail(str){
	var reg =/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    return reg.test(str);
} 


function checkPhone(str){
	var reg =/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
    return reg.test(str);
} 

function checkMobile(str){
	var reg =/^((\(\d{3}\))|(\d{3}\-))?1[358]\d{9}$/;
    return reg.test(str);
} 

function checkUrl(str){
	var reg =/^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
    return reg.test(str);
}



function checklongitude(str){
	var reg =/^-?(?:(?:180(?:\.0{1,5})?)|(?:(?:(?:1[0-7]\d)|(?:[1-9]?\d))(?:\.\d{1,5})?))$/;
    return reg.test(str);
}


function checklatitude(str){
	var reg =/^-?(?:90(?:\.0{1,5})?|(?:[1-8]?\d(?:\.\d{1,5})?))$/;
    return reg.test(str);
}

function checkDouble(str){
	var reg = /^[-\+]?\d+(\.\d+)?$/;
	return reg.test(str);

}

function checkIngeter(str){
	var reg =/^[-\+]?\d+$/;
	return reg.test(str);
}

function greaterThan0(str){
	if(checkIngeter(str)){
		if(parseInt(str) > 0){
			return true;
		}
	}
	return false;
}

// 添加验证方法 (身份证号码验证)
	jQuery.validator.addMethod("isIdCardNo", function(value, element) {   
		return this.optional(element) || checkidcard(value);   
	}); 
	
	//添加中文验证方法
	jQuery.validator.addMethod("chinaNo", function(value, element) {
		return this.optional(element) || checkchina(value);   
	}); 
	

	//添加英文验证方法
	jQuery.validator.addMethod("englishNo", function(value, element) {
		return this.optional(element) || checkenglish(value);   
	}); 
	
	
	//添加英文验证方法
	jQuery.validator.addMethod("dataNo", function(value, element) {
		return this.optional(element) || checkdata(value);   
	}); 


	//添加邮箱验证方法
	jQuery.validator.addMethod("emailNo", function(value, element) {
		return this.optional(element) || checkemail(value);   
	}); 


	//添加电话验证方法
	jQuery.validator.addMethod("phoneNo", function(value, element) {
		return this.optional(element) || checkPhone(value);   
	}); 


	//添加手机验证方法
	jQuery.validator.addMethod("mobileNo", function(value, element) {
		return this.optional(element) || checkMobile(value);   
	}); 


	//添加url验证方法
	jQuery.validator.addMethod("urlNo", function(value, element) {
		return this.optional(element) || checkUrl(value);   
	}); 

	//添加经度验证方法
	jQuery.validator.addMethod("longitudeNo", function(value, element) {
		return this.optional(element) || checklongitude(value);   
	}); 

	//添加纬度验证方法
	jQuery.validator.addMethod("latitudeNo", function(value, element) {
		return this.optional(element) || checklatitude(value);   
	}); 

	//添加double验证方法
	jQuery.validator.addMethod("doubleNo", function(value, element) {
		return this.optional(element) || checkDouble(value);   
	}); 

	//添加ingeter验证方法
	jQuery.validator.addMethod("integerNo", function(value, element) {
		return this.optional(element) || checkIngeter(value);   
	}); 
	
	//添加大于0的验证方法
	jQuery.validator.addMethod("greaterThan0", function(value, element) {
		return this.optional(element) || greaterThan0(value);   
	});
	
	//验证密码
	jQuery.validator.addMethod("passwordNo", function(value,element) {
		return this.optional(element) || checkpassword(value);   
	}); 
	//验证手机和电话
	jQuery.validator.addMethod("isPhone", function(value,element) {   
		return this.optional(element) || checkMobile(value) || checkPhone(value);
	});   