package empresa1.modelo;

import empresa.controlador.Cargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



/**
 * Implementa o CRUD para o objeto controlador.Cargo
 * CREATE/RETREAVE/UPDATE/DELETE
 * @author L
 *
 */
public abstract class CargoDAO {
	
	/**
	 * insere o cargo 'o' no banco de dados
	 * @param o objeto a ser inserido
	 * @return a chave primaria gerada
	 */
	public static int create(Cargo o) {
		
		if (o.getPk()!=0) {
			throw new RuntimeException("O objeto já foi inserido no banco de dados");
		}
		
		try {
			
			Connection conn = BancoDados.crieConexao();
			PreparedStatement stm = 
					conn.prepareStatement(
							"insert into cargos (nome, descricao) values (?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS);

			stm.setString(1, o.getNome());
			stm.setString(2, o.getDescricao());
			
			stm.execute();
			
			ResultSet rs = stm.getGeneratedKeys();
			rs.next();
			o.setPk(rs.getInt(1));// 1 significa 1a coluna			
			return o.getPk();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Retorna um objeto cargo que representa a t-upla da pk informada
	 * @param pk a chave primaria do cargo a ser retornado
	 * @return o cargo relativo a pk informada
	 */
	public static Cargo retreave(int pk) {
		try {
			
			Connection conn = BancoDados.crieConexao();
			PreparedStatement stm = 
					conn.prepareStatement(
							"select * from cargos where pk_cargo = ?");

			stm.setInt(1, pk);
			
			stm.executeQuery();
			
			ResultSet rs = stm.getResultSet();
			
			if (!rs.next()) {//if (rs.next()==false)
				throw new RuntimeException("chave primaria nao encontrada");
			}
			
			
			return new Cargo(rs.getInt("pk_cargo"), 
					         rs.getString("nome"), 
					         rs.getString("descricao"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ArrayList<Cargo> retreaveAll(){
		try {
			
			Connection conn = BancoDados.crieConexao();
			
            Statement stm = 
					conn.createStatement();
							
			stm.executeQuery("select * from cargos order by nome");
			
			ResultSet rs = stm.getResultSet();
			
			ArrayList<Cargo> aux = new ArrayList<>();
			
			while (rs.next()) {
				aux.add(new Cargo(rs.getInt("pk_cargo"), 
					         rs.getString("nome"), 
					         rs.getString("descricao")));
			}
			
			return aux;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void update(Cargo o) {
		
		if (o.getPk()==0) {
			throw new RuntimeException("O objeto nao existe no banco de dados");
		}
		
		try {
			
			Connection conn = BancoDados.crieConexao();
			PreparedStatement stm = 
					conn.prepareStatement(
							"UPDATE cargos SET nome=?, descricao=? WHERE pk_cargo=?");

			stm.setString(1, o.getNome());
			stm.setString(2, o.getDescricao());
			stm.setInt(3, o.getPk());
			
			stm.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void  delete(Cargo o) {
		if (o.getPk()==0) {
			throw new RuntimeException("O objeto nao existe no banco de dados");
		}
		
		try {
			
			Connection conn = BancoDados.crieConexao();
			PreparedStatement stm = 
					conn.prepareStatement(
							"DELETE FROM cargos WHERE pk_cargo=?");

			stm.setInt(1, o.getPk());
			
			stm.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
