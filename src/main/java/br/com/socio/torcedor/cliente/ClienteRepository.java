package br.com.socio.torcedor.cliente;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.socio.torcedor.model.Cliente;
/**
 * 
 * @author Luciano
 *
 */
public interface ClienteRepository {

	public static final String SELECT_CLIENTE = "SELECT * FROM CLIENTE ";

	public static final String SELECT_CLIENTE_ID = "SELECT * FROM CLIENTE WHERE id_CLIENTE = :id";

	public static final String SELECT_CLIENTE_VALIDATION = "SELECT * FROM CLIENTE WHERE upper(EMAIL) = upper(:email) ";

	public static final String INSERT_CLIENTE = "INSERT INTO CLIENTE "
			+ "  ( NOME_CLIENTE, EMAIL, DATA_NASC, ID_TIME_CORACAO ) " + " VALUES "
			+ "  ( :nome , :email, :dataNascimento, :timeCoracao ) ";
	
	public static final String DELETE_CLIENTE_ID = "DELETE FROM CLIENTE " + "  WHERE id_CLIENTE = :id  ";

	public static final String UPDATE_CLIENTE = " UPDATE CLIENTE SET " 
			+ "  	NOME_CLIENTE = :nomeCLIENTE ," 
			+ "		DATA_INICIO_VIG = :dtInicioVig,	" 
			+ "		DATA_FIM_VIG = :dtFimVig " 
			+ "   WHERE id_CLIENTE = :id ";

	List<Cliente> findAllClientes() throws Exception;

	List<Cliente> findClienteValidation(Cliente cliente);
	
	Boolean findClienteValidationBool(Cliente cliente);

	void saveCliente(Cliente cliente);

	Cliente findById(Long id);

}
