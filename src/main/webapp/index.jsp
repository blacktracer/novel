<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h2>Hello World! I am integrate eclipse and git! Game Over!</h2>
	<div id="app"></div>
	<!-- include artTemplate.js -->
	<script src="https://raw.githubusercontent.com/aui/artTemplate/master/dist/template.js"></script>
	<script id="test" type="text/html">
		<h1>{{title}}</h1>
		<ul>
    		{{each list as value i}}
       	 		<li>索引 {{i + 1}} ：{{#value}}</li>
    		{{/each}}
		</ul>
	</script>
	<script type="text/javascript">
		var data = {
		    title: '标签',
		    list: ['文艺', '博客', '摄影', '电影', '民谣', '旅行', '吉他', '<a href="https://www.baidu.com/">百度</a>']
		};
		var html = template('test', data);
		document.getElementById('app').innerHTML = html;
	</script>
	
</body>
</html>
