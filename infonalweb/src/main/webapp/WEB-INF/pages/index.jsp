<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir='/WEB-INF/tags' prefix='sc'%>
<!DOCTYPE html>
<html lang="tr">
<head>
<link
	href="<c:url value="/resources/css/jquery-ui-1.10.4.custom.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.10.2.js"/>"></script>
<script
	src="<c:url value="/resources/js/jquery-ui-1.10.4.custom.min.js"/>"></script>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/main.js"/>"></script>

<title>Kullanıcı Listesi Sayfası</title>
</head>
<body>

	<div class="center">
		<table id="users">
			<tr>
				<td colspan="5" align="center"><h3>Kullanıcı Listesi</h3></td>
			</tr>
			<tr>
				<td colspan="5" align="center"><button id="opener"
						style="height: 30px; width: 150px" align="center">Yeni
						Kullanıcı</button></td>
			</tr>
			<tr>
				<td>İsim</td>
				<td>Soyisim</td>
				<td>Telefon Numarası</td>
				<td>Kullanıcı Güncelle</td>
				<td>Kullanıcıyı Sil</td>
			</tr>
		</table>

	</div>

	<div id="wrapper" class="dialog">
		<form name="employeeForm" accept-charset="ISO-8859-1"
			action="/infonalweb/rest/recaptcha" method="post">
			<table cellpadding="0" cellspacing="0" border="1" class="GridOne">
				<tr>
					<td>İsim</td>
					<td><input type="text" name="firstName" id="firstName"
						value=${firstname}></td>
				</tr>
				<tr>
					<td>Soyisim</td>
					<td><input type="text" name="lastName" id="lastName"
						value=${lastname}></td>
				</tr>
				<tr>
					<td>Telefon Numarası</td>
					<td><input type="text" name="email" id="email"
						value=${phonenumber}></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><font color="red"><label
							id="choose"">${message}</label></font> <br /> <sc:captcha /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Yeni Kullanıcı" " id="create" />
					<td>
				</tr>
			</table>
		</form>

	</div>
	<div id="update" class="dialog">
		<form name="employeeForm2" method="post">
			<table cellpadding="0" cellspacing="0" border="1" class="GridOne">
				<tr>
					<td>İsim</td>
					<td><input type="text" name="firstName" id="firstName2"
						value=""></td>
				</tr>
				<tr>
					<td>Soyisim</td>
					<td><input type="text" name="lastName" id="lastName2" value=""></td>
				</tr>
				<tr>
					<td>Telefon Numarası</td>
					<td><input type="text" name="email" id="phoneNumber2" value=""></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button"
						value="Güncelle" id="b2" onclick="updateuser();"></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="modal"></div>
</body>
</html>