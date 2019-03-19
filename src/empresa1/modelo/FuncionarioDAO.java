package empresa1.modelo;

import empresa.controlador.Cargo;
import empresa.controlador.Cpf;
import empresa.controlador.Endereco;
import empresa.controlador.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FuncionarioDAO {

    /**
     * insere o funcionario 'o' no banco de dados
     *
     * @param o objeto a ser inserido
     * @return a chave primaria gerada
     */
    public static int create(Funcionario o) {

        if (o.getPk() != 0) {
            throw new RuntimeException("O objeto já foi inserido no banco de dados");
        }

        try {

            Connection conn = BancoDados.crieConexao();
            PreparedStatement stm
                    = conn.prepareStatement(
                            "INSERT INTO funcionarios(fk_cargo, nome, cpf) VALUES (?, ?, ?)",
                            PreparedStatement.RETURN_GENERATED_KEYS);

            if (o.getCargo().getPk() == 0) {
                CargoDAO.create(o.getCargo());
            }

            stm.setInt(1, o.getCargo().getPk());
            stm.setString(2, o.getNome());
            stm.setString(3, o.getCpf().toString());

            stm.execute();

            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            o.setPk(rs.getInt(1));// 1 significa 1a coluna	

            FuncionarioEnderecoDAO fed = new FuncionarioEnderecoDAO();

            for (Endereco aux : o.getEnderecos()) {
                aux.setFk(o.getPk());//configura a chave estrangeira de funcionarios_enderecos
                fed.create(aux);
            }

            return o.getPk();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Retorna um objeto funcionario que representa a t-upla da pk informada
     *
     * @param pk a chave primaria do cargo a ser retornado
     * @return o cargo relativo a pk informada
     */
    public static Funcionario retreave(int pk) {
        try {

            Connection conn = BancoDados.crieConexao();
            PreparedStatement stm
                    = conn.prepareStatement(
                            "select * from funcionarios where pk_funcionario = ?");

            stm.setInt(1, pk);

            stm.executeQuery();

            ResultSet rs = stm.getResultSet();

            if (!rs.next()) {//if (rs.next()==false)
                throw new RuntimeException("chave primaria nao encontrada");
            }

            FuncionarioEnderecoDAO fed = new FuncionarioEnderecoDAO();

            return new Funcionario(pk,
                    rs.getString("nome"),
                    new Cpf(rs.getString("cpf")),
                    CargoDAO.retreave(rs.getInt("fk_cargo")),
                    fed.retreaveAll(pk));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Funcionario> retreaveAll() {
        try {

            Connection conn = BancoDados.crieConexao();

            Statement stm
                    = conn.createStatement();

            stm.executeQuery("select * from funcionarios order by nome");

            ResultSet rs = stm.getResultSet();

            ArrayList<Funcionario> aux = new ArrayList<>();

            FuncionarioEnderecoDAO fed = new FuncionarioEnderecoDAO();
            
            while (rs.next()) {

                aux.add(new Funcionario(rs.getInt("pk_funcionario"),
                        rs.getString("nome"),
                        new Cpf(rs.getString("cpf")),
                        CargoDAO.retreave(rs.getInt("fk_cargo")),
                        fed.retreaveAll(rs.getInt("pk_funcionario"))));
            }

            return aux;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}
