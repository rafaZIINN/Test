package empresa1.modelo;

import empresa.controlador.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public abstract class EnderecoDAO {

	public abstract String getTableName();
	public abstract String getFkName();
	
	
	public int create(Endereco o) {

		if (o.getPk() != 0) {
			throw new RuntimeException("O objeto já foi inserido no banco de dados");
		}

		try {

			Connection conn = BancoDados.crieConexao();

			PreparedStatement stm = conn.prepareStatement(
					"INSERT INTO "+getTableName()+"("+getFkName()+", logradouro, bairro, cidade, estado, pais, cep) VALUES (?, ?, ?, ?, ?, ?, ?);",
					PreparedStatement.RETURN_GENERATED_KEYS);

			stm.setInt(1, o.getFk());
			stm.setString(2, o.getLogradouro());
			stm.setString(3, o.getBairro());
			stm.setString(4, o.getCidade());
			stm.setString(5, o.getEstado());
			stm.setString(6, o.getPais());
			stm.setString(7, o.getCep());

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
	 * 
	 * @param pk a chave primaria do cargo a ser retornado
	 * @return o cargo relativo a pk informada
	 */
	public Endereco retreave(int pk) {
		try {

			Connection conn = BancoDados.crieConexao();
			PreparedStatement stm = conn.prepareStatement("select * from "+getTableName()+" where pk_endereco = ?");

			stm.setInt(1, pk);

			stm.executeQuery();

			ResultSet rs = stm.getResultSet();

			if (!rs.next()) {// if (rs.next()==false)
				throw new RuntimeException("chave primaria nao encontrada");
			}

			return new Endereco(pk, 
					rs.getInt("fk_fornecedor"), 
					rs.getString("logradouro"),
					rs.getString("bairro"), 
					rs.getString("cidade"), 
					rs.getString("estado"), 
					rs.getString("pais"),
					rs.getString("cep"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Retorna um objeto cargo que representa a t-upla da pk informada
	 * 
	 * @param pk a chave primaria do cargo a ser retornado
	 * @return o cargo relativo a pk informada
	 */
	public ArrayList<Endereco> retreaveAll(int fk) {
		try {

			Connection conn = BancoDados.crieConexao();
			PreparedStatement stm = conn.prepareStatement("select * from " + getTableName() + " where " + getFkName() + " = ?");

			stm.setInt(1, fk);
			
			stm.executeQuery();

			ResultSet rs = stm.getResultSet();
			
			ArrayList<Endereco> aux = new ArrayList<>();
			
			while (rs.next()) {
				aux.add(new Endereco(rs.getInt("pk_endereco"), 
						rs.getInt(getFkName()), 
						rs.getString("logradouro"),
						rs.getString("bairro"), 
						rs.getString("cidade"), 
						rs.getString("estado"), 
						rs.getString("pais"),
						rs.getString("cep")));
			}
			
			return aux;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public void update(Endereco o) {
		
		if (o.getPk()==0) {
			throw new RuntimeException("O objeto nao existe no banco de dados");
		}
		
		try {
			
			Connection conn = BancoDados.crieConexao();
			PreparedStatement stm = 
					conn.prepareStatement(
							"UPDATE ? SET ?=?, logradouro=?, bairro=?, cidade=?, estado=?, pais=?, cep=? WHERE pk_endereco = ?");

			stm.setString(1, getTableName());
			stm.setString(2, getFkName());
			stm.setInt(3, o.getFk());
			stm.setString(4, o.getLogradouro());
			stm.setString(5, o.getBairro());
			stm.setString(6, o.getCidade());
			stm.setString(7, o.getEstado());
			stm.setString(8, o.getPais());
			stm.setString(9, o.getCep());
			stm.setInt(10, o.getPk());
			
			stm.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(Endereco o) {
		
		if (o.getPk()==0) {
			throw new RuntimeException("O objeto nao existe no banco de dados");
		}
		
		try {
			
			Connection conn = BancoDados.crieConexao();
			PreparedStatement stm = 
					conn.prepareStatement(
							"DELETE FROM ? WHERE pk_endereco = ?");

			stm.setString(1, getTableName());
			stm.setInt(2, o.getPk());
			
			stm.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
