package br.com.socio.torcedor.maprow;

import org.springframework.jdbc.core.RowMapper;

import br.com.socio.torcedor.model.Cliente;
/**
 * 
 * @author Luciano
 *
 */
public final class MapRowFactory {

	private MapRowFactory() {
		super();
	}

	public static RowMapper<Cliente> getCampanhaRowMapper(Cliente client) {
		return new ClienteMapRow(client);
	}

}
