function radioSelect(radioObj,value){
	$.each(radioObj,function(i,item){
		if($(item).val()==value){
			$(item).prop("checked",true);
			return;
		}else{
			$(item).prop("checked",false);
		}
	});
}

function selectSelect(selectObj,value){
	selectObj.val(value);
}