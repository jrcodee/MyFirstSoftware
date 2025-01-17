package conexaopostgree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.util.PSQLException;

import entities.Usuario;
import factorys.FactoryException;

public class ConexaoLogin {
	
	private static Conexao conectar = null;
	
	@SuppressWarnings("finally")
	public Usuario fazerLogin(String id) {
		Usuario usuario = null;
		try {
			conectar = new Conexao();
			
			System.out.println("Usuario da Conexao: " + conectar.getConexao().getMetaData().getUserName());
			System.out.println("URL da Conexao: " + conectar.getConexao().getMetaData().getURL());
			
			usuario = buscaDados(id);
			
			} catch (Exception ex) {
				return null;
			}
			finally{
				if(conectar != null)
					conectar.fecharConexao();
					return usuario;
			}
	}
	
	private static Usuario buscaDados(String id) throws SQLException {
		
		Connection con = conectar.getConexao();
		String comandoBuscaIdESenha = "SELECT pk_id, senha, tipo_de_acesso FROM public.usuario WHERE pk_id = ?;";
		ResultSet resultado = null;
		
		try {
			
			PreparedStatement stmBuscaIdESenha = con.prepareStatement(comandoBuscaIdESenha);
			stmBuscaIdESenha.setString(1, id);
			resultado = stmBuscaIdESenha.executeQuery();
			resultado.next();
			
			
		}catch (SQLException e) {
			
			if(con != null){
				try{
					con.rollback();
					return null;
				}catch(SQLException sqlE){
					sqlE.printStackTrace();
				}
			}

		}finally{
			if(con != null){
				try {
					con.setAutoCommit(true);
					con.close();
					return new Usuario(resultado.getString(1), resultado.getString(2), resultado.getString(3));
				} catch (SQLException e) {
					e.getStackTrace();
				}
				

			}

		}
		return null;
	}
}
