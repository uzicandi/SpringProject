<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item Write View</title>
</head>
<body>
		<select id="category2">
			<c:forEach var="cate" items="${catelist}">
				<option <c:if test="${cate.parent==cate.name}"/> value="${cate.name}">${cate.name}</option>
			</c:forEach>
		</select>			
</body>
</html>