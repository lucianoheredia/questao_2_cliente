package br.com.socio.torcedor.ws;

import java.util.List;

import br.com.socio.torcedor.model.Campanha;

public interface ClienteCampanhaWSInterface {

	final static String URI_SERVICO = "http://localhost:8080/api/Camp";
	
	final static String URI_SERVICO_ASSOCIACAO ="http://localhost:8080/api/Camp/Associar/{id}";
	
	List<Campanha> consumirServicoCampanha();
	
	void associarClienteCampanha(Long id) throws Exception;
}
