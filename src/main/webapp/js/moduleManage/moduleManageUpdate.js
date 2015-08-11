$(document).ready(function(){
	$('#tt').tree({
		onClick: function(node){
			var basePath = $("input[name='basePath']").val();
			var nodes = $('#tt').tree('getSelected');
			$("#moduleDiv input[name='moduleName']").val(nodes.text);
			var parentNode = $("#tt").tree('getParent',node.target);
			if(parentNode == null){
				$("#moduleDiv input[name='parentModuleName']").val(basePath);
			}else{
				$("#moduleDiv input[name='parentModuleName']").val(parentNode.text);
			}
			$("#submitBtn").hide();
			var moduleId = nodes.id;
			$.ajax({
			       type:"POST",
			       url:basePath+"/moduleManage/ajaxQueryModule",
			       data:{"moduleId":moduleId},
			       dataType: 'json',
			       success:function(data){
				       if(data.code=='1000'){
					    	$("#moduleDiv input[name='parentId']").val(data.data.parentId);
					    	$("#moduleDiv input[name='moduleName']").val(data.data.moduleName);
					    	$("#moduleDiv input[name='srcPath']").val(data.data.srcPath);
					    	$("#moduleDiv input[name='remark']").val(data.data.remark);
					    	radioSelect($("input[name='moduleType']"),data.data.moduleType);
					    	radioSelect($("input[name='srcPathType']"),data.data.srcPathType);
				       }else{
				    	   $("#messDiv").append("<div class='alert alert-success' role='alert'>保存成功</div>");
				       }
			       },
			       error:function(data){
				       alert("系统异常："+data.returnCode);
				       return;
			       }
			});
			
		},
		onContextMenu: function(e, node){
			e.preventDefault();
			$('#tt').tree('select', node.target);
			$('#mm').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		}
	});
	$("#moduleForm").validate({
		onsubmit:true,
		onfocusout:function(element) {$(element).valid();},
		onkeyup :false,
		submitHandler:function(){
			ajaxAddModule();
		},
		rules : {
			parentModuleName:{
				required:true
			},
			moduleName:{
				required:true
			},
			srcPath:{
				required:true
			},
			srcPathType:{
				required:true
			}
		},
		messages:{
			parentModuleName:{
				required:"请选择父模块"
			},
			moduleName:{
				required:"请输入模块名"
			},
			srcPath:{
				required:"请输入资源路径"
			},
			srcPathType:{
				required:"请选择资源类型"
			}
		}
	});
});

function append(){
	$("#actionType").val("doAdd");
	$("#submitBtn").removeProp("disabled");
	var nodes = $('#tt').tree('getSelected');
	$("#moduleDiv input[name='parentModuleName']").val(nodes.text);
	$("#moduleDiv input[name='parentId']").val(nodes.id);
	$("#moduleDiv input[name='moduleName']").val("");
	$("#moduleDiv input[name='moduleType']").val("1");
	$("#moduleDiv input[name='srcPath']").val("");
	$("#moduleDiv input[name='srcPathType']").val("1");
	$("#moduleDiv input[name='remark']").val("");
	$("#submitBtn").text("确认添加");
	$("#submitBtn").show();
}

function ajaxAddModule(){
	var basePath = $("input[name='basePath']").val();
	var url = "";
	if($("#actionType").val()=="doAdd"){
		url = basePath+"/moduleManage/ajaxDoAddModule";
	}else if($("#actionType").val()=="doUpdate"){
		url = basePath+"/moduleManage/ajaxDoUpdateModule";
	}
	$.ajax({
       type:"POST",
       url:url,
       async:false,
       data:$("#moduleForm").serialize(),
       dataType: 'json',
       success:function(data){
	       if(data.code=='1000'){
	    	    $("#messDiv").text("");
	       		$("#messDiv").append("保存成功");
	       		$("#submitBtn").prop("disabled",true);
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


function edit(){
	$("#actionType").val("doUpdate");
	$("#submitBtn").removeProp("disabled");
	
	var basePath = $("input[name='basePath']").val();
	var nodes = $('#tt').tree('getSelected');
	$("#moduleDiv input[name='moduleName']").val(nodes.text);
	var parentNode = $("#tt").tree('getParent',nodes.target);
	if(parentNode == null){
		$("#moduleDiv input[name='parentModuleName']").val(basePath);
	}else{
		$("#moduleDiv input[name='parentModuleName']").val(parentNode.text);
	}
	$("#submitBtn").hide();
	var moduleId = nodes.id;
	$.ajax({
	       type:"POST",
	       url:basePath+"/moduleManage/ajaxQueryModule",
	       data:{"moduleId":moduleId},
	       dataType: 'json',
	       success:function(data){
		       if(data.code=='1000'){
			    	$("#moduleDiv input[name='parentId']").val(data.data.parentId);
			    	$("#moduleDiv input[name='id']").val(data.data.id);
			    	$("#moduleDiv input[name='moduleName']").val(data.data.moduleName);
			    	$("#moduleDiv input[name='srcPath']").val(data.data.srcPath);
			    	$("#moduleDiv input[name='remark']").val(data.data.remark);
			    	radioSelect($("input[name='moduleType']"),data.data.moduleType);
			    	radioSelect($("input[name='srcPathType']"),data.data.srcPathType);
		       }else{
		    	   $("#messDiv").append("<div class='alert alert-success' role='alert'>保存成功</div>");
		       }
	       },
	       error:function(data){
		       alert("系统异常："+data.returnCode);
		       return;
	       }
	});
	$("#submitBtn").text("确认更新");
	$("#submitBtn").show();
}
