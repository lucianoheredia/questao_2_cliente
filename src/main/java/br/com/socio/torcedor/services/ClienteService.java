package br.com.socio.torcedor.services;

import java.util.List;

import br.com.socio.torcedor.model.Campanha;
import br.com.socio.torcedor.model.Cliente;
/**
 * 
 * @author Luciano
 *
 */
public interface ClienteService {

	List<Campanha> saveCliente(Cliente cliente);
     
	List<Cliente> findAllClientes() throws Exception;

	Cliente findById(Long id);
	
	void associarClienteCampanha(Long id) throws Exception;

	    
}
