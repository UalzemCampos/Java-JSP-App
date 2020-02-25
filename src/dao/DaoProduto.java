package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCategoria;
import beans.BeanProdutos;
import connection.SingleConnection;

public class DaoProduto {
	
	private Connection connection;
	
	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanProdutos produto) {
		
		try {
		String sql = "insert into produto(nome,quantidade,valor,valor_total, categoria_id) values (?, ?, ?, ?, ?)";
		PreparedStatement insert = connection.prepareStatement(sql);
		insert.setString(1,  produto.getNome());
		insert.setDouble(2, produto.getQuantidade());
		insert.setDouble(3, produto.getValor());
		insert.setDouble(4, produto.getValor_total());
		insert.setLong(5, produto.getCategoria_id());
		
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
	
	
	              // AQUI O METODO QUE CRIA 1 LISTA PRA MOSTRAR OS PRODUTOS ADICIONADOS:
	
	public List<BeanProdutos> listar() throws Exception{
		List<BeanProdutos> listar = new ArrayList<BeanProdutos>();
		
		String sql = "select * from produto";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			BeanProdutos beanProdutos = new BeanProdutos();
			beanProdutos.setId(resultSet.getLong("id"));
			beanProdutos.setNome(resultSet.getString("nome"));
			beanProdutos.setQuantidade(resultSet.getDouble("quantidade"));
			beanProdutos.setValor(resultSet.getDouble("valor"));
			beanProdutos.setValor_total(resultSet.getDouble("valor_total"));
			beanProdutos.setCategoria_id(resultSet.getLong("categoria_id"));
			
			
			listar.add(beanProdutos);
		}
		
		return listar;
	
	}
	
	
	// AQUI O METODO QUE CRIA 1 LISTA PRA MOSTRAR AS CATEGORIAS ADICIONADAS:
	
	
	public List<BeanCategoria> listaCategorias() throws Exception{
		List<BeanCategoria> retorno = new ArrayList<BeanCategoria>();
		
		String sql = "select * from categoria";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			BeanCategoria categoria = new BeanCategoria();
			categoria.setId(resultSet.getLong("id"));
			categoria.setNome(resultSet.getString("nome"));
			retorno.add(categoria);
		}
		return retorno;
		
		
	}
	
	
	
	

                                // MÉTODO DO DELETE:
              public void delete (String id) {
            	  try {
            		  String sql = "delete from produto where id = '" + id + "'";
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
			public BeanProdutos consultar(String id) throws Exception {
				String sql = "select * from produto where id='" + id + "'";
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				
				if (resultSet.next()) {
					BeanProdutos beanProdutos = new BeanProdutos();
					beanProdutos.setId(resultSet.getLong("id"));
					beanProdutos.setNome(resultSet.getString("nome"));
					beanProdutos.setQuantidade(resultSet.getDouble("quantidade"));
					beanProdutos.setValor(resultSet.getDouble("valor"));
					beanProdutos.setValor_total(resultSet.getDouble("valor_total"));
					beanProdutos.setCategoria_id(resultSet.getLong("categoria_id"));
					
					
					return beanProdutos;
					
				}
				
				return null;
			}
			
			
			
			
			
            // MÉTODO VALIDAR PRODUTO(NÃO DEIXAR REGISTRAR PRODUTO REPETIDO):
	public boolean validarProduto(String nome) throws Exception {
		String sql = "select count(1) as quantidade from produto where nome='" + nome + "'"; //SE JÁ TIVER 1 PRODUTO NÃO REGISTRA
		                                                                                       // SE FOR ZERO, REGISTRA NOVO PRODUTO
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if (resultSet.next()) {
			
		return resultSet.getInt("quantidade")<=0; // SE FOR MENOR OU IGUAL A ZERO = CADASTRA PRODUTO
			
		}
		
		return false;                      // SE FOR 1 OU MAIS, QUER DIZER JÁ EXISTE ESSE PRODUTO, NÃO CADASTRA.
	}
	
	
	
	
	
	   // MÉTODO VALIDAR LOGIN(NÃO DEIXAR REGISTRAR LOGIN REPETIDO AO ATUALIZAR USUARIO):
		public boolean validarProdutoUpdate(String nome, String id) throws Exception {
			String sql = "select count(1) as quantidade from produto where nome='" + nome + "' and id <>" + id; //SE JÁ TIVER 1 PRODUTO NÃO ATUALIZA
			                                                                                       // SE FOR ZERO, REGISTRA NOVO PRODUTO
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
			return resultSet.getInt("quantidade")<=0; // SE FOR MENOR OU IGUAL A ZERO = CADASTRA LOGIN
				
			}
			
			return false;                      // SE FOR 1 OU MAIS, QUER DIZER JÁ EXISTE ESSE LOGIN, NÃO CADASTRA.
		}
	
	
	
	
	
			
			
			

			
			                            // MÉTODO PARA ATUALIZAR OS USUARIOS:
			public void atualizar(BeanProdutos produto) {
				try {
					String sql = "update produto set nome = ?, quantidade = ?, valor= ?, valor_total=?, categoria_id=?"
							+ "where id = " + produto.getId();
					
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1,  produto.getNome());
					preparedStatement.setDouble(2,  produto.getQuantidade());
					preparedStatement.setDouble(3,  produto.getValor());
					preparedStatement.setDouble(4,  produto.getValor_total());
					preparedStatement.setLong(5, produto.getCategoria_id());
					
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
	
	
			

		}

