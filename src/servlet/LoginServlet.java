package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DaoLogin;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoLogin daoLogin = new DaoLogin();    // INSTANCIAMOS NOSSO DAO AQUI
   
    public LoginServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 try {
		BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
		
		  String login = request.getParameter("login");
		  String senha = request.getParameter("senha");
		  
		  if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) { // VALIDAR LOGIN E SENHA
			  // SE NÃO FIZERMOS ESSA VALIDAÇÃO AQUI O SISTEMA VAI LÁ NO BANCO PRA VERIFICAR DESNECESSARIAMENTE.
		  
		 
			if(daoLogin.validarLogin(login, senha)) {  // ACESSO PERMITIDO
				  RequestDispatcher dispatcher = request.getRequestDispatcher("acessoliberado.jsp");
				  dispatcher.forward(request, response);
				  
			
				  
			  }else {       // AECESSO NEGADO
				  RequestDispatcher dispatcher = request.getRequestDispatcher("acessonegado.jsp");// CAI AQUI E DEPOIS...
				  dispatcher.forward(request, response);
				  
				  
			  }
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");  // ...VOLTA PARA TELA DE LOGIN
			  dispatcher.forward(request, response);
		}
		  
		 }catch (Exception e) {
		e.printStackTrace();
		}
	}

}
