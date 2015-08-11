$().ready(function(){
		$("#addForm").validate({
			onsubmit:true,
			onfocusout:function(element) {$(element).valid();},
			onkeyup :false,
			submitHandler:function(){
				ajaxAddModule();
			},
			rules : {
				moduleName:{
					required:true
				},
				status:{
					required:true
				}
			},
			messages:{
				moduleName:{
					required:"系统名称不能为空"
				},
				status:{
					required:"请选择状态"
				}
			}
		});
	});

	function ajaxAddModule(){
		$.ajax({
            type:"POST",
            url:basePath+"/moduleManage/ajaxDoAddSySModule",
            async:false,
            data:$("#addForm").serialize(),
            dataType: 'json',
            success:function(data){
            	if(data.code=='1000'){
           	 		$("#messDiv").append("<div class='alert alert-success' role='alert'>保存成功</div>");
           	 	}else{
           	 		alert("保存失败:"+data.returnCode);
           	 	}
            },
            error:function(data){
            	alert("系统异常："+data.returnCode);
            	return;
            }
         });
	}
