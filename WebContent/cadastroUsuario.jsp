<%@page import="beans.BeanCursoJsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usu�rios</title>


<!-- LINK PARA O NOSSO CSS: -->

<link rel="stylesheet" href="resources/css/estiloCadastro.css">
<link rel="stylesheet" href="resources/css/style.css">


<script src="text/javascript" src="resources/js/jquery-3.2.0.min.js"></script>
<!-- JQUERY PARA MASCARAS DE TELEFONE -->
<script src="text/javascript" src="resources/js/jquery.mask.min.js" /></script>





<!-- Adicionando JQuery PARA O CEP: -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous">
    
    
   
    
    
    
    </script>


<script src="https://kit.fontawesome.com/0114e4a937.js"
	crossorigin="anonymous"></script>



</head>






<body>

	<a href="acessoliberado.jsp"><img src="resources/img/enterIcon.png"
		widht="50px" height="50px" title="Anterior"></a>
	<a href="index.jsp"><img alt="In�cio"
		src="resources/img/homeIcon.png" widht="50px" height="50px"
		title="Home"></a>


	<center>
		<h1>Cadastro de Novo Usu�rio:</h1>
		<h3 style="color: red">${msg}</h3>
	</center>



	<form action="salvarUsuario" method="post" id="formUser" onsubmit=""
		enctype="multipart/form-data">
		<!-- METODO POST PORQUE VAMOS ENVIAR OS DADOS   -     ESSE ENCTYPE � PARA ENVIAR FOTOS -->



		<ul class="form-style-1">
			<li>
				<table>

					<tr>

						<td>Codigo:</td>
						<td><input type="text" id="id" readonly="readonly" name="id"
							value="${user.id}"></td>


						<td>CEP:</td>
						<td><input name="cep" type="text" id="cep"
							onblur="consultaCEP();" value="${user.cep}" /></td>

					</tr>




					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							placeholder="crie o seu login" maxlength="10"
							value="${user.login}"></td>

						<td>Rua::</td>
						<td><input name="rua" type="text" id="rua"
							value="${user.rua}" maxlength="50" /></td>

					</tr>




					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							placeholder="crie sua senha" value="${user.senha}" maxlength="8"></td>

						<td>Bairro::</td>
						<td><input name="bairro" type="text" id="bairro"
							value="${user.bairro}" maxlength="30" /></td>
					</tr>




					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}" maxlength="50"></td>

						<td>Cidade::</td>
						<td><input name="cidade" type="text" id="cidade"
							value="${user.cidade}" maxlength="50" /></td>
					</tr>





					<tr>
						<div class="form-group"></div>
						<td>Estado::</td>
						<td><input name="estado" type="text" id="estado"
							value="${user.estado}" maxlength="22" /></td>


						<td>IBGE::</td>
						<td><input name="ibge" type="text" id="ibge"
							value="${user.ibge}" maxlength="20" /></td>

					</tr>






					<tr>



						<td>ATIVO:</td>
						<td><input type="checkbox" id="ativo" name="ativo"
							<% // ESSA TAG � DO JSP E PERMITE ESCREVER JAVA DENTRO DO HTML
						
						// AQUI COME�AM AS CONDI��ES JAVA PARA O EDITAR USUARIO SE EST� ATIVO OU N�O:
							if (request.getAttribute("user") != null){
								BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
								if (user.isAtivo()){
									out.print(" ");   // PARA SEPARAR AS CONDI��ES ABAIXO
									out.print("checked=\"chedked\"");   // CHECKED = MARCADO
									out.print(" ");
								}
							}
						%> /></td>









					</tr>




					<tr>




						<td>Sexo:</td>
						<td><input type="radio" name="sexo"
							<% // ESSA TAG � DO JSP E PERMITE ESCREVER JAVA DENTRO DO HTML
						
						// AQUI COME�AM AS CONDI��ES JAVA PARA O EDITAR O SEXO DO USUARIO MASCULINO:
							if (request.getAttribute("user") != null){
								BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
								if (user.getSexo().equalsIgnoreCase("masculino")){
									out.print(" ");   // PARA SEPARAR AS CONDI��ES ABAIXO
									out.print("checked=\"chedked\"");   // CHECKED = MARCADO
									out.print(" ");
								}
							}
						%>
							value="masculino">Masculino <input type="radio"
							name="sexo"
							<% // ESSA TAG � DO JSP E PERMITE ESCREVER JAVA DENTRO DO HTML
						
						// AQUI COME�AM AS CONDI��ES JAVA PARA O EDITAR O SEXO DO USUARIO FEMININO:
							if (request.getAttribute("user") != null){
								BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
								if (user.getSexo().equalsIgnoreCase("feminino")){
									out.print(" ");   // PARA SEPARAR AS CONDI��ES ABAIXO
									out.print("checked=\"chedked\"");   // CHECKED = MARCADO
									out.print(" ");
								}
							}
						%>
							value="feminino">Feminino</td>




						<td>Foto:</td>
						<td><input type="file" name="foto"> <!-- CSS INLINE PARA ESCONDER O TYPE(style="display:none;") --></td>
						<!-- PARA FAZER UPLOAD DE FOTOS -->









					</tr>
					<!-- NA PASTA WebContent/WEB-INF/lib FOI ADICIONADO 2 BIBLIOTECAS PARA UPLOAD DE ARQUIVOS -->




					<tr>



						<td>Perfil:</td>
						<td><select id="perfil" name="perfil"
							style="width: 185px; height: 30px;">
								<option value="nenhum">-- SELECIONE --</option>
								<option value="administrador"
									<% // ESSA TAG � DO JSP E PERMITE ESCREVER JAVA DENTRO DO HTML
						
						// AQUI COME�AM AS CONDI��ES JAVA PARA O VER QUAL OP��O EST� SELECIONADA:
							if (request.getAttribute("user") != null){
								BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
								if (user.getPerfil().equalsIgnoreCase("administrador")){
									out.print(" ");   // PARA SEPARAR AS CONDI��ES ABAIXO
									out.print("selected=\"selected\"");   // SELECTED = SELECIONADO
									out.print(" ");
								}
							}
						%>>Administrador</option>



								<option value="gerente"
									<% // ESSA TAG � DO JSP E PERMITE ESCREVER JAVA DENTRO DO HTML
						
						// AQUI COME�AM AS CONDI��ES JAVA PARA O VER QUAL OP��O EST� SELECIONADA:
							if (request.getAttribute("user") != null){
								BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
								if (user.getPerfil().equalsIgnoreCase("gerente")){
									out.print(" ");   // PARA SEPARAR AS CONDI��ES ABAIXO
									out.print("selected=\"selected\"");   // SELECTED = SELECIONADO
									out.print(" ");
								}
							}
						%>>Gerente</option>





								<option value="atendente"
									<% // ESSA TAG � DO JSP E PERMITE ESCREVER JAVA DENTRO DO HTML
						
						// AQUI COME�AM AS CONDI��ES JAVA PARA O VER QUAL OP��O EST� SELECIONADA:
							if (request.getAttribute("user") != null){
								BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
								if (user.getPerfil().equalsIgnoreCase("atendente")){
									out.print(" ");   // PARA SEPARAR AS CONDI��ES ABAIXO
									out.print("selected=\"selected\"");   // SELECTED = SELECIONADO
									out.print(" ");
								}
							}
						%>>Atendente</option>




								<option value="secretario"
									<% // ESSA TAG � DO JSP E PERMITE ESCREVER JAVA DENTRO DO HTML
						
						// AQUI COME�AM AS CONDI��ES JAVA PARA O VER QUAL OP��O EST� SELECIONADA:
							if (request.getAttribute("user") != null){
								BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
								if (user.getPerfil().equalsIgnoreCase("secretario")){
									out.print(" ");   // PARA SEPARAR AS CONDI��ES ABAIXO
									out.print("selected=\"selected\"");   // SELECTED = SELECIONADO
									out.print(" ");
								}
							}
						%>>Secret�rio(a)</option>




								<option value="suporte"
									<% // ESSA TAG � DO JSP E PERMITE ESCREVER JAVA DENTRO DO HTML
						
						// AQUI COME�AM AS CONDI��ES JAVA PARA O VER QUAL OP��O EST� SELECIONADA:
							if (request.getAttribute("user") != null){
								BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
								if (user.getPerfil().equalsIgnoreCase("suporte")){
									out.print(" ");   // PARA SEPARAR AS CONDI��ES ABAIXO
									out.print("selected=\"selected\"");   // SELECTED = SELECIONADO
									out.print(" ");
								}
							}
						%>>Suporte</option>




								<option value="telefonista"
									<% // ESSA TAG � DO JSP E PERMITE ESCREVER JAVA DENTRO DO HTML
						
						// AQUI COME�AM AS CONDI��ES JAVA PARA O VER QUAL OP��O EST� SELECIONADA:
							if (request.getAttribute("user") != null){
								BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
								if (user.getPerfil().equalsIgnoreCase("telefonista")){
									out.print(" ");   // PARA SEPARAR AS CONDI��ES ABAIXO
									out.print("selected=\"selected\"");   // SELECTED = SELECIONADO
									out.print(" ");
								}
							}
						%>>Call
									Center</option>





						</select></td>



						<td>Curr�culo:</td>
						<td>
							<!-- PARA FAZER UPLOAD DE PDF --> <input type="file"
							name="curriculo" value="Curriculo">
						</td>







					</tr>


					<!-- NA PASTA WebContent/WEB-INF/lib FOI ADICIONADO 2 BIBLIOTECAS PARA UPLOAD DE ARQUIVOS -->




					<tr>
						<td></td>
						<!-- BOT�O SALVAR COM SCRIPT PARA VALIDAR SE CAMPOS EST�O VAZIOS: -->
						<td><input type="submit" value="Salvar"
							onclick="return validarCampos() ? true : false;"> <input
							type="submit" value="Cancelar"
							onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"></td>
						<!-- BOT�O CANCELAR  -->
					</tr>
				</table>
			</li>
		</ul>

	</form>





	<form method="post" action="servletPesquisa">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>

						<td>Procurar:</td>

						<td>
							<div class="search-box">
								<input type="text" id="descricaoconsulta"
									name="descricaoconsulta" / placeholder="Digite um nome">
						</td>
						</div>
						<td><input type="submit" value="Pesquisar" name="pesquisar" /></td>

					</tr>
				</table>
			</li>
		</ul>
	</form>


	<form>

		<table class="customers">
			<caption class="titulo">Usu�rios Registrados</caption>

			<tr style="font-size: 18px; background-color: #F5DEB3;">
				<th>ID</th>
				<th>FOTO</th>
				<th>CURR�CULO</th>
				<th>NOME</th>
				<th>TELEFONES</th>
				<th>DELETE</th>
				<th>EDITAR</th>

			</tr>

			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td style="font-size: 18px;"><c:out value="${user.id}"></c:out></td>

					<c:if test="${user.fotoBase64Miniatura.isEmpty() == false }">
						<!-- SE N�O EST� VAZIO MOSTRA ICONE FOTO -->
						<td><a
							href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}"><img
								src='<c:out value="${user.fotoBase64Miniatura}"/>'
								alt="Imagem User" title="Imagem User" width="32px" height="32px" />
						</a></td>
					</c:if>
					<c:if test="${user.fotoBase64Miniatura == null }">
						<td><img src="resources/img/UserPadrao.png" alt="Image User"
							widht="32px" height="32px"
							onclick="alert('N�o possui foto cadastrada.')"></td>
					</c:if>


					<c:if test="${user.curriculoBase64.isEmpty() == false }">
						<td><a
							href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}">
								<img src="resources/img/PDFicon.png" alt="Curr�culo"
								widht="32px" height="32px">
						</a></td>
					</c:if>

					<c:if test="${user.curriculoBase64 == null }">
						<td><img src="resources/img/semPDFicon.png" alt="Curr�culo"
							widht="32px" height="32px"
							onclick="alert('N�o possui PDF do Curr�culo.')"></td>
					</c:if>


					<td style="font-size: 18px;"><c:out value="${user.nome}"></c:out></td>



					<td><a href="salvarTelefones?acao=addFone&user=${user.id}">
							<img src="resources/img/phone.png" alt="Telefones" widht="32px"
							height="32px" title="Telefones">
					</a></td>
					<!-- LINK DE EDITAR O TELEFONE -->


					<td><a href="salvarUsuario?acao=delete&user=${user.id }">
							<img src="resources/img/ExcluirIcon.png" alt="Excluir"
							widht="32px" height="32px" title="Excluir"
							onclick="return confirm('TEM CERTEZA QUE DESEJA EXCLUIR?');">
					</a></td>
					<!-- LINK DE EXCLUIR REGISTRO -->


					<td><a href="salvarUsuario?acao=editar&user=${user.id }">
							<img src="resources/img/EditarIcon.png" alt="Editar" widht="32px"
							height="32px" title="Editar">
					</a></td>
					<!-- LINK DE EDITAR O REGISTRO -->





				</tr>
			</c:forEach>

		</table>

	</form>


	<!--  AQUI A FUN��O JAVASCRIPT PARA VALIDAR SE OS CAMPOS EST�O VAZIOS OU N�O: -->

	<script type="text/javascript">
                  function validarCampos(){
                	  if(document.getElementById("login").value == ''){
                		  alert('Informe o Login !');
                		  return false;
                	  }  if(document.getElementById("senha").value == ''){
                		  alert('Informe a Senha !');
                		  return false;
                	  }  else if(document.getElementById("nome").value == ''){
                		  alert('Informe o Nome !');
                		  return false;
                	  }  
                	  //if(document.getElementById("telefone").value == ''){
                		 // alert('Informe o Telefone !');
                		  //return false;
                	 // }



                	  return true;
                  }
                  
                  
                  // FUN��O JAVASCRIPT PARA CONSULTAR CEP:
                	  
                	  function consultaCEP(){
                	  var cep = $("#cep").val();
                	  
                	  
                	  
                	  
                	  
                	//Consulta o webservice viacep.com.br/
                      $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {
                    	  
                    	  
                    	
                          if (!("erro" in dados)) {  // SE O CEP EST� CORRETO PREENCHE SOZINHO OS CAMPOS ABAIXO:
                        	  
                        	  $("#rua").val(dados.logradouro);
                              $("#bairro").val(dados.bairro);
                              $("#cidade").val(dados.localidade);
                              $("#estado").val(dados.uf);
                              $("#ibge").val(dados.ibge);
                              
                          } 
                          else {
                              //SE CEP pesquisado n�o foi encontrado OU N�O FOI DIGITADO NENHUM CEP:.
                             
                              
                              $("#cep").val('');
                              $("#rua").val('');
                              $("#bairro").val('');
                              $("#cidade").val('');
                              $("#estado").val('');
                              $("#ibge").val('');
                              
                              alert("CEP n�o encontrado.");
                          }
                      });
                 
                	  
                	  
                	  
                	  
                  }
                  
                	  
                  
                  
                  </script>

	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>




</body>
</html>