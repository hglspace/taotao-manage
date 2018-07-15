<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/commons/common-js.jsp"></jsp:include>
<script type="text/javascript">
  function test(){
	  var data=$.get('/rest/item/querycat','id=3',function(data){
		  alert(JSON.parse(data).name);
	  });
  }
</script>
</head>
<body>
<a href="javaScript:void(0);" onclick="test();">点我</a>

</body>
</html>