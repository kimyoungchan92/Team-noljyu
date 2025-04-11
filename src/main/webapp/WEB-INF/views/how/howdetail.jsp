<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $("#howdelete").click(function() {
        var hownum = $("#hownum").val();
        var value = confirm("정말로 삭제하시겠습니까?");
        if (value == true) {
            $.ajax({
                type: "POST",
                url: "howdelete",
                data: { "hownum": hownum },
                async: true,
                success: function(bigo) {
                    if (bigo == "success") { 
                        alert("삭제가 완료되었습니다");
                        location.href="./";
                    } else {
                        alert("정상적으로 처리되지 않았습니다");
                        location.href="main";
                    }
                },
                error: function() {
                    alert("에러가 발생했습니다. 다시 시도해주세요");
                }
            });
        }
    });
});


</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="hidden" name="hownum" value="${dto.hownum}" id="hownum">
<table border="1" width="800px" align="center">
	<tr>
		<th>아이디</th>
		<td>${dto.id}</td>
	</tr>
	
	<tr>
		<th>제목</th>
		<td>${dto.howtitle}</td>
	</tr>
	
	<tr>
		<th>내용</th>
		<td>${dto.howdetail}</td>
	</tr>
	
	<tr>
		<th>이미지</th>
		<td><img alt="" src="./image/${dto.howimg}"></td>
	</tr>
	
	<tr>
		<td colspan="2" style="text-align: center;">
		<button onclick="location.href='howupdate?hownum=${dto.hownum}'">수정</button>
		<button id="howdelete">삭제</button>
	</tr>
</table>

<form action="howreviewsave">
<input type="hidden" value="${dto.hownum}" name="hownum">
<input type="hidden" value="${dto.id}" name="id">
<table border="1" align="center" width="800px">
	<tr>
		<th><textarea rows="10" cols="70" name="review"></textarea></th>
		<td><input type="submit" value="전송"></td>
	</tr>
</table>
</form>


<table border="1" align="center" width="800px" style="margin-bottom: 100px">
<c:forEach items="${list}" var="rr">
	<tr>
		<th>
		<c:forEach var="i" begin="0" end="${rr.indent}">
			<c:choose>
				<c:when test="${i<rr.indent}">
					&emsp;
				</c:when>
				<c:when test="${i==rr.indent}">
					<img alt="" src="./image/reviewicon.png" height="30px">
				</c:when>
			</c:choose>
			
		</c:forEach>
		${rr.review}</th>
		<td>
		<button onclick="location.href='howrere?reviewnum=${rr.reviewnum}'">댓글달기</button>
	</tr>
</c:forEach>	
</table>

</body>
</html>