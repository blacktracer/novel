<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="ctm" uri="http://virgin.com/custom-defined-tag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
		.done{
			background-color: red;
			color: white;
		}
	</style>
</head>
<body>
    <% pageContext.setAttribute("name","admin"); %>
	<h2>Hello World!<ctm:customDefinedTag name="${name}"/></h2>
	

</body>
</html>
