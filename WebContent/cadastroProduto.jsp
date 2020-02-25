<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Produtos</title>


<!-- LINK PARA O NOSSO CSS: -->

<link rel="stylesheet" href="resources/css/estiloCadastro.css">



<!-- JQUERY MASCARA PARA VALOR  ONETÁRIO: -->
<script src="resources/js/jquery.min.js" type="text/javascript"></script>
<script src="resources/js/jquery.maskMoney.min.js"
	type="text/javascript"></script>


</head>




<body>


	<a href="acessoliberado.jsp"><img src="resources/img/enterIcon.png"
		widht="50px" height="50px" title="Anterior"></a>
	<a href="index.jsp"><img alt="Início"
		src="resources/img/homeIcon.png" widht="50px" height="50px"
		title="Home"></a>



	<center>
		<h1>Cadastro de Novo Produto:</h1>
		<h3 style="color: red">${msg}</h3>
	</center>



	<form action="salvarProduto" method="post" id="formUser">
		<!-- METODO POST PORQUE VAMOS ENVIAR OS DADOS -->



		<ul class="form-style-1">
			<li>
				<table>

					<tr>
						<td>Codigo:</td>
						<td><input type="text" id="id" readonly="readonly" name="id"
							value="${produto.id}"></td>
					</tr>

					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							style="width: 400px" value="${produto.nome}" maxlength="50"></td>
					</tr>

					<tr>
						<td>Quantidade:</td>
						<td><input type="text" id="quantidade" name="quantidade"
							value="${produto.quantidade}" maxlength="6"></td>
					</tr>

					<tr>
						<td>Valor Unitário:</td>
						<td><input type="text" id="valor" name="valor"
							data-thousands="." data-decimal="," data-precision="2"
							value="${produto.valorEmTexto}" maxlength="20"></td>
					</tr>




					<tr>
						<td>Categoria:</td>
						<td><select id="categorias" name="categoria_id">

								<c:forEach items="${categorias}" var="cat">
									<!-- USAMOS O c:forEach AQUI PORQUE OS ITEM A SEREM CARREGADOS SÃO DINÂMICOS -->
									<option value="${cat.id}" id="${cat.id}"
										<c:if test="${cat.id == produto.categoria_id}">
					<c:out value = "selected=selected" />
					</c:if>>

										<!-- VALORES DINÂMICOS Q PODEM SER ACRESCENTADOS CONFORME NECESSIDADE -->

										${cat.nome}
									</option>
								</c:forEach>
						</select></td>
					</tr>







					<tr>
						<td></td>
						<!-- BOTÃO SALVAR COM SCRIPT PARA VALIDAR SE CAMPOS ESTÃO VAZIOS: -->
						<td><input type="submit" value="Salvar"
							onclick="return validarCampos() ? true : false;"> <input
							type="submit" value="Cancelar"
							onclick="document.getElementById('formUser').action = 'salvarProduto?acao=reset'"></td>
						<!-- BOTÃO CANCELAR  -->
					</tr>
				</table>
			</li>
		</ul>

	</form>

	<form>

		<table class="customers">
			<caption class="titulo">Produtos Registrados</caption>

			<tr style="font-size: 18px; background-color: #F5DEB3;">
				<th>ID</th>
				<th>NOME PRODUTO</th>
				<th>QUANTIDADE</th>
				<th>CATEGORIA</th>
				<th>VALOR - R$</th>
				<th>VALOR TOTAL - R$</th>
				<th>DELETE</th>
				<th>EDITAR</th>
			</tr>

			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td style="font-size: 18px;"><c:out value="${produto.id}"></c:out></td>
					<td style="font-size: 18px;"><c:out value="${produto.nome}"></c:out></td>
					<td style="font-size: 18px;"><c:out
							value="${produto.quantidade}"></c:out></td>
					<td style="font-size: 18px;"><c:out
							value="${produto.categoria_id}"></c:out></td>





















					<td style="font-size: 18px;"><fmt:formatNumber type="number"
							maxFractionDigits="2" value="${produto.valor}" /></td>
					<td style="font-size: 18px;"><fmt:formatNumber type="number"
							maxFractionDigits="2"
							value="${produto.valor * produto.quantidade}" /></td>

					<!-- fmt - EXTENSÃO JSP PARA NUMEROS. URL COLOCADA LÁ EM CIMA -->

					<td><a href="salvarProduto?acao=delete&produto=${produto.id }">
							<img src="resources/img/ExcluirIcon.png" widht="32px"
							height="32px" title="Excluir">
					</a></td>
					<!-- LINK DE EXCLUIR REGISTRO -->
					<td><a
						href="salvarProduto?acao=editar&produto=${produto.id }"> <img
							src="resources/img/EditarIcon.png" widht="32px" height="32px"
							title="Editar"></a></td>
					<!-- LINK DE EDITAR O REGISTRO -->


				</tr>
			</c:forEach>

		</table>

	</form>


	<!--  AQUI A FUNÇÃO JAVASCRIPT PARA VALIDAR SE OS CAMPOS ESTÃO VAZIOS OU NÃO: -->

	<script type="text/javascript">
                  function validarCampos(){
                	  if(document.getElementById("nome").value == ''){
                		  alert('Informe o Nome !');
                		  return false;
                	  }  if(document.getElementById("quantidade").value == ''){
                		  alert('Informe a Quantidade !');
                		  return false;
                	  }  else if(document.getElementById("valor").value == ''){
                		  alert('Informe o Valor em R$ !');
                		  return false;
                	  }  


                	  return true;
                  }
                  
                  
                  </script>



</body>


<!-- SCRIPT PARA USAR A MÁSCARA DE DINHEIRO NO CAMPO VALOR: -->

<script type="text/javascript">
                      $(function(){
                    	  $('#valor').maskMoney();
                      });
                      
                      
                     // SCRIPT LIMITA QUANTIDADE DE DIGITOS DO CAMPO QUANTIDADE DE PRODUTOS:
                    	 
                    	 $(document).read(function(){
                    		 $("#quantidade").keyup(function(){
                    			 $("#quantidade").val(this.value.match(/[0-9]*/));
                    		 });
                    	 });
                      
                      </script>

</html>