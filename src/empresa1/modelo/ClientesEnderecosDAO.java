package empresa1.modelo;

public class ClientesEnderecosDAO extends EnderecoDAO{

	public static final String TABLE_NAME = "clientes_enderecos";
	public static final String FK_NAME = "fk_cliente";

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_NAME;
	}
	@Override
	public String getFkName() {
		// TODO Auto-generated method stub
		return FK_NAME;
	}
	
	
}
