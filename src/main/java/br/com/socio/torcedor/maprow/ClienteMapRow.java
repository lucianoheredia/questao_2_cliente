package br.com.socio.torcedor.maprow;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.socio.torcedor.model.Cliente;
import br.com.socio.torcedor.model.TimeCoracao;
/**
 * 
 * @author Luciano
 *
 */
public class ClienteMapRow implements RowMapper<Cliente>{

	private transient Cliente cliente;
	
	public ClienteMapRow(Cliente cliente){
		this.cliente = cliente;
	}
	
	@Override
	public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Cliente newCliente = new Cliente();
		TimeCoracao timeCor = new TimeCoracao();
		
		newCliente.setId(rs.getLong("id_cliente"));
		newCliente.setNome(rs.getString("nome_cliente"));
		newCliente.setEmail(rs.getString("email"));
		newCliente.setDataNascimento(rs.getString("data_nasc"));
		timeCor.setId(rs.getInt("id_time_coracao"));
		newCliente.setTimeCoracao(timeCor);

		this.cliente = newCliente;
		
		return this.cliente;
	}

}
