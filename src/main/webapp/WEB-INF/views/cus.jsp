<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="wrap" align="center">
      <h1>글쓰기</h1>
      <form name="frm" method="get" action="putCus">
         <table>
            <tr>
               <th>아이디</th>
               <td><input type="text" name="id"> 
               </td>
            </tr>
            <tr>
               <th>이름</th>
               <td><input type="text" size="70" name="name"> </td>
            </tr>
            <tr>
               <th>비밀번호</th>
               <td><textarea cols="70" rows="15" name="pwd"></textarea></td>
            </tr>
         </table>
         <br>
         <br> <input type="submit" value="등록"> 
         <input type="reset"value="다시 작성"> 
      </form>
   </div>
</body>
</html>