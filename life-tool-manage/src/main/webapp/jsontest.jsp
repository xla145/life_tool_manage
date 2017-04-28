<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post">
		<table>
			<tr>
			<tr>
				<td><input type="button" value="生成" onclick="commit()" /></td>
			</tr>
		</table>
		<table id="ulist" border="2">
		</table>
	</form>
</body>
<script type="text/javascript" src="js/json2.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

	var str = "{\"name\":\"User\", \"table\": \"t_user8\",\"content\":" +
	" [{\"name\": \"id\",\"type\": \"java.lang.Integer\",\"db_type\": \"int primary key auto_increment\"},{" +
	"\"name\": \"name\",\"type\": \"java.lang.String\",\"length\": \"50\",\"db_type\": \"varchar(20)\"},{" +
	"\"name\": \"password\", \"type\": \"java.lang.String\",\"length\": \"50\",\"db_type\":\"varchar(50)\"}]}";
	function commit(){
	    $.ajax(
	            {
	             url:"save",
	             type : "post",
	             data:{table:str},
	             dataType : "json",
	             success : function(data) {
					alert("success!");
				}
	                }
	            );
	    
	}
	
</script>

</html>