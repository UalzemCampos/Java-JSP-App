<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ualzem Campos</title>




<!-- OBSERVAÇÃO: O ARQUIVO web.xml DESTE PROJETO TEM ALTERAÇÕES PARA PODERMOS USAR SERVLETS:

<display-name>AulasJSP</display-name>
  <welcome-file-list>
    <welcome-file>index.jsp</welcome-file>
  </welcome-file-list>
  <filter>
    <filter-name>filter</filter-name>
    <filter-class>filter.Filter</filter-class>
  </filter>
</web-app>
 -->







<!-- LINK PARA O NOSSO CSS: -->

<link rel="stylesheet" href="resources/css/estiloLogin2.css">
<link rel="stylesheet" href="resources/css/estiloLogin3.css">


</head>
<body>

	<div class="login-page">

		<center>
			<h1 class="h2Edit">Projeto</h1>
		</center>
		<center>
			<h1 class="h2Edit">JSP + Servlets + JDBC</h1>
		</center>

		<center>
			<spam class="spamEdit">
			<b>USUÁRIO:</b> admin <br>
			<b>SENHA:</b> admin</spam>
		</center>

		<div class="form">
			<form action="LoginServlet" method="post" class="login-form">
				<strong>Login:</strong> <input type="text" id="login" name="login"
					value="" placeholder="Insira seu login"> <br /> <strong>Senha:</strong>
				<input type="password" id="senha" name="senha" value=""
					placeholder="Insira sua senha"> <br />
				<button type="submit" value="Logar">
					<strong>Logar</strong>
				</button>
			</form>
		</div>
		<center>
			<h1 class="h1Edit">Ualzem Campos</h1>
		</center>
		<center>
			<a href="http://www.linkedin.com/in/ualzem-campos"><img
				src="resources/img/logoLinkedin.png" widht="32px" height="32px" /></a>
		</center>

	</div>


</body>
</html>