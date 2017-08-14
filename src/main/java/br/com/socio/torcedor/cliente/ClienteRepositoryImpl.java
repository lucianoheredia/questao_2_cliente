package br.com.socio.torcedor.cliente;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.socio.torcedor.infra.DataSourceConfig;
import br.com.socio.torcedor.maprow.MapRowFactory;
import br.com.socio.torcedor.model.Cliente;

/**
 * 
 * @author Luciano
 *
 */
@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

	private NamedParameterJdbcTemplate config;

	public ClienteRepositoryImpl() {
		this.config = DataSourceConfig.jdbcTemplate();
	}

	@Override
	public List<Cliente> findAllClientes() throws Exception {
		Date novo = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String data = format.format(novo.getTime());
		Cliente cliente = new Cliente();
		ConcurrentHashMap<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
		return this.config.query(SELECT_CLIENTE, paramMap, MapRowFactory.getCampanhaRowMapper(cliente));
	}

	@Override
	public List<Cliente> findClienteValidation(Cliente cliente) {
		Cliente camp = new Cliente();
		ConcurrentHashMap<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
		paramMap.put("email", cliente.getEmail());
		return this.config.query(SELECT_CLIENTE_VALIDATION, paramMap, MapRowFactory.getCampanhaRowMapper(camp));
	}

	@Override
	public Boolean findClienteValidationBool(Cliente cliente) {
		List<Cliente> clienteList = new ArrayList<>();
		Cliente camp = new Cliente();
		ConcurrentHashMap<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
		paramMap.put("email", cliente.getEmail());
		clienteList = this.config.query(SELECT_CLIENTE_VALIDATION, paramMap, MapRowFactory.getCampanhaRowMapper(camp));
		if (clienteList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void saveCliente(Cliente cliente) {
		ConcurrentHashMap<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
		paramMap.put("nome", cliente.getNome());
		paramMap.put("email", cliente.getEmail());
		paramMap.put("dataNascimento", cliente.getDataNascimento());
		paramMap.put("timeCoracao", cliente.getTimeCoracao().getId());
				
		this.config.update(INSERT_CLIENTE, paramMap);
	}

	@Override
	public Cliente findById(Long id) {
		Cliente camp = new Cliente();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		try {
			camp = (Cliente) this.config.queryForObject(SELECT_CLIENTE_ID, parameters,
					MapRowFactory.getCampanhaRowMapper(camp));
		} catch (Exception e) {
			camp = null;
			e.printStackTrace();
		}

		return camp;
	}


}
