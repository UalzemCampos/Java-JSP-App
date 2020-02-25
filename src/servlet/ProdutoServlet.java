package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProdutos;
import dao.DaoProduto;


/**
 * Servlet implementation class ProdutoServlet
 */
@WebServlet("/salvarProduto" )
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoProduto daoProduto = new DaoProduto();
       
   
    public ProdutoServlet() {
        super();
        
    }

	                     // PARA EXCLUIR:
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
		String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listartodos";
		String produto = request.getParameter("produto");
		
		RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
		
		
		if (acao != null && acao.equalsIgnoreCase("delete")) {
			daoProduto.delete(produto);
     
			request.setAttribute("produtos",  daoProduto.listar());
			
		}        
		                // AQUI PARA EDITAR USUARIO:
		
		else if (acao != null && acao.equalsIgnoreCase("editar")) {
			BeanProdutos beanProdutos = daoProduto.consultar(produto);
			
			request.setAttribute("produto",  beanProdutos);
			
			
		}else if (acao != null && acao.equalsIgnoreCase("listartodos")) {
			
			request.setAttribute("produtos",  daoProduto.listar());
			

		}
		
		request.setAttribute("categorias", daoProduto.listaCategorias());    // LISTA A CATEGORIA DOS PRODUTOS
		view.forward(request, response);
		
	}catch (Exception e) {
		e.printStackTrace();	
	}
	}
	
	
	
	

	            // PARA INCLUIR E SALVAR USARIO NOVO:
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		
		String acao = request.getParameter("acao");   // PARA O BOTÃO CANCELAR NO EIDTAR USUARIO
		if (acao!= null && acao.equalsIgnoreCase("reset")) {
			
			
			 // PARA FICAR NA MESMA PÁGINA APÓS CANCELAR EDIÇÃO DE USUARIO:
			
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				
				request.setAttribute("produtos",  daoProduto.listar());
				view.forward(request, response);
				
			}catch (Exception e) {
				e.printStackTrace();
					
			}
			
		}else {
		
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String quantidade = request.getParameter("quantidade");
		String valor = request.getParameter("valor");
		String valor_total = request.getParameter("valor_total");
		String categoria = request.getParameter("categoria_id");
		
		
	
		
		
		try {

			String msg = null;
			boolean podeInserir = true;
			
			
			
			if (nome == null || nome.isEmpty()) {
				msg = "Nome deve ser informado";
				podeInserir = false;

			}
			
			
			
			else if (quantidade == null || quantidade.isEmpty()) {
				msg = "Quantidade deve ser informado";
				podeInserir = false;

			}
			
			

			else if (valor == null || valor.isEmpty()) {
				msg = "Valor R$ deve ser informado";
				podeInserir = false;

			}
			
			
	
			
			
			
			else if (id == null || id.isEmpty()
					&& !daoProduto.validarProduto(nome)) {// QUANDO
														// FDOR
														// PRODUTO
														// NOVO
				msg = "Produto já existe com o mesmo nome!";
				podeInserir = false;

			}

			BeanProdutos produto = new BeanProdutos();
			produto.setNome(nome);
			produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			produto.setCategoria_id(Long.parseLong(categoria));

			if (quantidade != null && !quantidade.isEmpty()) {
				
				
				
				produto.setQuantidade(Double.parseDouble(quantidade));
			}
			
                   
			        // CONVERTER VIRGULA AO INSERIR DADOS POR PONTO (BANCO SO ACEITA PONTO)
			if (valor != null && !valor.isEmpty()) {
				String valorParse = valor.replaceAll("\\.", "");
				valorParse = valorParse.replaceAll("\\,", ".");
				produto.setValor(Double.parseDouble(valorParse));
			}

			if (msg != null) {
				request.setAttribute("msg", msg);
			} else if (id == null || id.isEmpty()
					&& daoProduto.validarProduto(nome) && podeInserir) {

				daoProduto.salvar(produto);

			} else if (id != null && !id.isEmpty() && podeInserir) {
				daoProduto.atualizar(produto);
			}

			if (!podeInserir) {
				request.setAttribute("produto", produto);
			}

			RequestDispatcher view = request
					.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("produtos", daoProduto.listar());       // LISTA OS PRODUTOS
			request.setAttribute("categorias", daoProduto.listaCategorias());    // LISTA A CATEGORIA DOS PRODUTOS
			view.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

}


		
		
		
		
		
		











		
		
		
		
		
		
		
		
		
		
		
		
		
		/**
		
		BeanProdutos produto = new BeanProdutos();
		produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
		produto.setNome(nome);
		produto.setQuantidade(Double.parseDouble(quantidade));   
		produto.setValor(Double.parseDouble(valor));           
		
		
		try {
			
			
			String msg = null;
			boolean podeInserir = true;
			
			
			
				
			
			

			if (id == null || id.isEmpty()
					&& !daoProduto.validarProduto(nome)) {//QUANDO FOR PRODUTO NOVO
				msg = "Produto já existe com o mesmo nome!";
				podeInserir = false;

			} 

			if (msg != null) {
				request.setAttribute("msg", msg);
			}

			if (id == null || id.isEmpty()
					&& daoProduto.validarProduto(nome) && podeInserir) {

				daoProduto.salvar(produto);

			} else if (id != null && !id.isEmpty() && podeInserir) {
				daoProduto.atualizar(produto);
			}
			
			if (!podeInserir){
				request.setAttribute("user", produto);
			}

			RequestDispatcher view = request
					.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("produtos", daoProduto.listar());
			view.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

}
**/