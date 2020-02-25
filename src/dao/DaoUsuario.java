package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {
	
	private Connection connection;
	
	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanCursoJsp usuario) {
		
		try {
		String sql = "insert into usuario(login, senha, nome, cep, rua, bairro, cidade, estado, ibge,"
				+ " fotobase64, contenttype, contenttypecurriculo, curriculobase64, fotobase64miniatura, ativo, sexo, perfil)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insert = connection.prepareStatement(sql);
		insert.setString(1,  usuario.getLogin());
		insert.setString(2,  usuario.getSenha());
		insert.setString(3,  usuario.getNome());
		insert.setString(4,  usuario.getCep());
		insert.setString(5,  usuario.getRua());
		insert.setString(6,  usuario.getBairro());
		insert.setString(7,  usuario.getCidade());
		insert.setString(8,  usuario.getEstado());
		insert.setString(9,  usuario.getIbge());
		insert.setString(10, usuario.getFotoBase64());
		insert.setString(11, usuario.getContentType());
		insert.setString(12, usuario.getContentTypeCurriculo());
		insert.setString(13, usuario.getCurriculoBase64());
		insert.setString(14, usuario.getFotoBase64Miniatura());
		insert.setBoolean(15, usuario.isAtivo());
		insert.setString(16, usuario.getSexo());
		insert.setString(17, usuario.getPerfil());
		
		insert.execute();
		connection.commit();
		
	}catch (Exception e){
		e.printStackTrace();
		
		try {
			connection.rollback();
		}catch (SQLException e1) {
			e1.printStackTrace();
			
		}
	}
}
	
	
	// AQUI O METODO QUE CRIA 1 LISTA PRA MOSTRAR OS USUARIOS PESQUISADOS:
	public List<BeanCursoJsp> listar(String descricaoconsulta) throws SQLException{
		String sql = "SELECT * FROM usuario WHERE login <> 'admin' AND LOWER(nome) LIKE LOWER('%" + descricaoconsulta + "%') ORDER BY nome";
		return consultarUsuarios(sql);
		
	}
	
	
	
	
	              // AQUI O METODO QUE CRIA 1 LISTA PRA MOSTRAR OS USUARIOS ADICIONADOS:
	
	public List<BeanCursoJsp> listar() throws Exception{
		
		
		String sql = "select * from usuario where login <> 'admin'"; // ESCONDE O USUARIO ADMIN (ASSIM ELE NÃO PODE SER EDITADO POR QUEM USA O SISTEMA)
		
		return consultarUsuarios(sql);
		
		
	
	}

	private List<BeanCursoJsp> consultarUsuarios(String sql) throws SQLException {
		List<BeanCursoJsp> listar = new ArrayList<BeanCursoJsp>();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			
			
			beanCursoJsp.setCep(resultSet.getString("cep"));
			beanCursoJsp.setRua(resultSet.getString("rua"));
			beanCursoJsp.setBairro(resultSet.getString("bairro"));
			beanCursoJsp.setCidade(resultSet.getString("cidade"));
			beanCursoJsp.setEstado(resultSet.getString("estado"));
			beanCursoJsp.setIbge(resultSet.getString("ibge"));
			
			beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
			beanCursoJsp.setContentType(resultSet.getString("contenttype"));
			
			beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
			
			beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
			
			beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
			
			beanCursoJsp.setSexo(resultSet.getString("sexo"));
			
			beanCursoJsp.setPerfil(resultSet.getString("perfil"));
			
			
			
			listar.add(beanCursoJsp);
		}
		return listar;
	}
	

                                // MÉTODO DO DELETE:
              public void delete (String id) {
            	  try {
            		  String sql = "delete from usuario where id = '" + id + "' and login <> 'admin'";// NÃO DEIXA APAGAR O ADMIN
            		  PreparedStatement preparedStatement = connection.prepareStatement(sql);
            		  preparedStatement.execute();
            		  connection.commit();
            	  } catch (Exception e) {
            		  e.printStackTrace();
            		  try {
            			  connection.rollback();
            		  } catch (SQLException e1) {
            			  e1.printStackTrace();
            		  }
            	  }

}
                                  // MÉTODO CONSULTAR PARA EDITAR:
			public BeanCursoJsp consultar(String id) throws Exception {
				String sql = "select * from usuario where id='" + id + "' and login <> 'admin'";// NÃO DEIXA CONSULTAR O ADMIN";
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				
				if (resultSet.next()) {
					BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
					beanCursoJsp.setId(resultSet.getLong("id"));
					beanCursoJsp.setLogin(resultSet.getString("login"));
					beanCursoJsp.setSenha(resultSet.getString("senha"));
					beanCursoJsp.setNome(resultSet.getString("nome"));
					
					
					beanCursoJsp.setCep(resultSet.getString("cep"));
					beanCursoJsp.setRua(resultSet.getString("rua"));
					beanCursoJsp.setBairro(resultSet.getString("bairro"));
					beanCursoJsp.setCidade(resultSet.getString("cidade"));
					beanCursoJsp.setEstado(resultSet.getString("estado"));
					beanCursoJsp.setIbge(resultSet.getString("ibge"));
					
					beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
					beanCursoJsp.setContentType(resultSet.getString("contenttype"));
					
					beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
					beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
					
					beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
					
					beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
					
					beanCursoJsp.setSexo(resultSet.getString("sexo"));
					
					beanCursoJsp.setPerfil(resultSet.getString("perfil"));
					
					
					return beanCursoJsp;
					
				}
				
				return null;
			}
			
			
			
			
			
            // MÉTODO VALIDAR LOGIN(NÃO DEIXAR REGISTRAR LOGIN REPETIDO):
	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) as quantidade from usuario where login='" + login + "'"; //SE JÁ TIVER 1 LOGIN NÃO REGISTRA
		                                                                                       // SE FOR ZERO, REGISTRA NOVO LOGIN
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if (resultSet.next()) {
			
		return resultSet.getInt("quantidade")<=0; // SE FOR MENOR OU IGUAL A ZERO = CADASTRA LOGIN
			
		}
		
		return false;                      // SE FOR 1 OU MAIS, QUER DIZER JÁ EXISTE ESSE LOGIN, NÃO CADASTRA.
	}
	
	
	
	
	
	   // MÉTODO VALIDAR LOGIN(NÃO DEIXAR REGISTRAR LOGIN REPETIDO AO ATUALIZAR USUARIO):
		public boolean validarLoginUpdate(String login, String id) throws Exception {
			String sql = "select count(1) as quantidade from usuario where login='" + login + "' and id <>" + id; //SE JÁ TIVER 1 LOGIN NÃO ATUALIZA
			                                                                                       // SE FOR ZERO, REGISTRA NOVO LOGIN
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
			return resultSet.getInt("quantidade")<=0; // SE FOR MENOR OU IGUAL A ZERO = CADASTRA LOGIN
				
			}
			
			return false;                      // SE FOR 1 OU MAIS, QUER DIZER JÁ EXISTE ESSE LOGIN, NÃO CADASTRA.
		}
	
	
	
	
	
			
			
			

			
			                            // MÉTODO PARA ATUALIZAR OS USUARIOS:
			public void atualizar(BeanCursoJsp usuario) {
				try {
					
			StringBuilder sql = new StringBuilder();

			// O APPEND FAZ A CONCATENAÇÃO PORÉM DEIXA FAZER CONDIÇÕES DENTRO DELAS
			sql.append(" update usuario set login = ?, senha = ?, nome= ? ,");
			sql.append(" cep= ?, rua= ?, bairro= ?, cidade= ?, estado= ?, ibge= ?, ativo= ?, sexo= ?, perfil=?");

			if (usuario.isAtualizarImage()) {

				sql.append(", fotobase64 = ?,  contenttype = ? ");
			}

			if (usuario.isAtualizarPdf()) {
				sql.append(", contenttypecurriculo = ?, curriculobase64 = ? ");
			}

			if (usuario.isAtualizarImage()) {

				sql.append(", fotobase64miniatura = ? ");
			}

			sql.append(" where id = " + usuario.getId());
					
					
					PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
					preparedStatement.setString(1,  usuario.getLogin());
					preparedStatement.setString(2,  usuario.getSenha());
					preparedStatement.setString(3,  usuario.getNome());
					preparedStatement.setString(4,  usuario.getCep());
					preparedStatement.setString(5,  usuario.getRua());
					preparedStatement.setString(6,  usuario.getBairro());
					preparedStatement.setString(7,  usuario.getCidade());
					preparedStatement.setString(8,  usuario.getEstado());
					preparedStatement.setString(9,  usuario.getIbge());
					preparedStatement.setBoolean(10,  usuario.isAtivo());
					preparedStatement.setString(11,  usuario.getSexo());
					preparedStatement.setString(12,  usuario.getPerfil());
					
					
			if (usuario.isAtualizarImage()) {
				preparedStatement.setString(13, usuario.getFotoBase64());
				preparedStatement.setString(12, usuario.getContentType());
			}

			if (usuario.isAtualizarPdf()) {

				if (usuario.isAtualizarPdf() && !usuario.isAtualizarImage()) {

					preparedStatement.setString(13, usuario.getContentTypeCurriculo());
					preparedStatement.setString(14, usuario.getCurriculoBase64());
				} else {
					preparedStatement.setString(15, usuario.getContentTypeCurriculo());
					preparedStatement.setString(16, usuario.getCurriculoBase64());
				}
			}

			else {
				if (usuario.isAtualizarImage()) {
					preparedStatement.setString(15, usuario.getFotoBase64Miniatura());
				}
			}

			if (usuario.isAtualizarImage() && usuario.isAtualizarPdf()) {
				preparedStatement.setString(17, usuario.getFotoBase64Miniatura());
			}
					
			
			  
					
					preparedStatement.executeUpdate();
					connection.commit();
				}catch (Exception e) {
					e.printStackTrace();
					try {
						connection.rollback();
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
	
	
			public boolean validarSenha(String senha) throws Exception {
				String sql = "select count(1) as qtd from usuario where senha='" + senha + "'";

				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					
					return resultSet.getInt("qtd") <= 0;/*Return true*/
				}
				return false;
			}

		}

