function pageJS(page)
{
    try{
        $("input[name='pageNum']").val(page);
        $("form[name='pageForm']").submit();
    }
    catch(e){
        alert("Page Form Error."+e);
    }
}

function pagePlusJS(pages)
{
    try{
        var page = $('#page').val();
        if(isNaN(page) || page < 1 || parseInt(page) > parseInt(pages) || page.indexOf(".") == 1)
        {
            alert("请输入正确的页数");
        }
        else
        {
            //$('#pageForm').submit();
        	$("form[name='pageForm']").submit();
        }
    }
    catch(e){
        alert("Page Form Error.");
    }
}