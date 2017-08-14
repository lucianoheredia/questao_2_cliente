package br.com.socio.torcedor.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.socio.torcedor.model.Campanha;
import br.com.socio.torcedor.model.Cliente;
import br.com.socio.torcedor.services.ClienteService;
import br.com.socio.torcedor.utils.RestSocioTorcedorErrorType;

/**
 * 
 * @author Luciano
 *
 */
@RestController
@RequestMapping("/api")
public class RestSocioTorcedorClienteController {

	/**
	 * Log
	 */
	public static final Logger logger = LoggerFactory.getLogger(RestSocioTorcedorClienteController.class);

	/**
	 * Injeção do Spring
	 */
	@Autowired
	ClienteService clientService;

	/**
	 * Busca todos os Clientes
	 * 
	 */

	@RequestMapping(value = "/Cliente/", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listAllClientes() throws Exception {
		List<Cliente> clientes = clientService.findAllClientes();
		if (clientes.isEmpty()) {

			return new ResponseEntity(new RestSocioTorcedorErrorType("Não existem Clientes cadastradas!"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}

	/**
	 * Busca Cliente por Id
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/Cliente/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCliente(@PathVariable("id") Long id) {
		logger.info("Cliente id {}", id);
		Cliente cliente = clientService.findById(id);
		if (cliente == null) {
			logger.error("Cliente com o id não existe .", id);
			return new ResponseEntity(new RestSocioTorcedorErrorType("Cliente com o id - " + id + " não existe !"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	/**
	 * Insere uma novo cliente
	 * 
	 * @param Cliente
	 * @return
	 */
	@RequestMapping(value = "/Cliente/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<List<Campanha>>  createCliente(@RequestBody Cliente cliente) {
		List<Campanha> campanhas = clientService.saveCliente(cliente);
		return new ResponseEntity<List<Campanha>>(campanhas, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/Cliente/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCampanha(@PathVariable("id") Long id, UriComponentsBuilder builder) {
		clientService.associarClienteCampanha(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/api/Cliente/{id}").buildAndExpand(id).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.OK);
	}
	
	

	
}
