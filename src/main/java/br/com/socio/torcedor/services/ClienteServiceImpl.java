package br.com.socio.torcedor.services;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.socio.torcedor.cliente.ClienteRepository;
import br.com.socio.torcedor.model.Campanha;
import br.com.socio.torcedor.model.Cliente;
import br.com.socio.torcedor.rules.ClienteRules;
import br.com.socio.torcedor.ws.ClienteCampanhaWSInterface;

/**
 * 
 * @author Luciano
 *
 */
@Service("campanhaService")
public class ClienteServiceImpl implements ClienteService {

	@Inject
	private transient ClienteRepository repository;

	@Inject
	private transient ClienteRules rules;

	@Autowired
	ClienteCampanhaWSInterface campanhaWS;

	
	public List<Cliente> findAllClientes() throws Exception {
		return this.repository.findAllClientes();
	}

	
	public Cliente findById(Long id) {
		return this.repository.findById(id);
	}

	
	@Override
	public List<Campanha> saveCliente(Cliente cliente) {

		boolean saveBool = false;
		try {
			saveBool = this.rules.clienteValidation(cliente);
		} catch (ParseException e) {
			saveBool = false;
			e.printStackTrace();
		}

		/*
		 * Dado um E-mail que já existe, informar que o cadastro já foi
		 * efetuado, porém, caso o cliente não tenha nenhuma campanha associada,
		 * o serviço deverá enviar as novas campanhas como resposta;
		 * Se "saveBool" igual a TRUE faz a requisição para buscar campanhas;
		 */
		if (saveBool) {
			return this.campanhaWS.consumirServicoCampanha();
		} else {
			this.repository.saveCliente(cliente);
			return this.campanhaWS.consumirServicoCampanha();
		}

	}

	@Override
	public void associarClienteCampanha(Long id){
		this.campanhaWS.associarClienteCampanha(id);
	}

}
