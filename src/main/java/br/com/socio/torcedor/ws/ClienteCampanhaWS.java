package br.com.socio.torcedor.ws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.com.socio.torcedor.dto.CampanhaDTO;
import br.com.socio.torcedor.kafka.TopicProducer;
import br.com.socio.torcedor.model.Campanha;

/**
 * Serviço criado para receber requisiçoes do projeto socio-torcedor
 * @author Luciano
 *
 */

@Service
public class ClienteCampanhaWS implements ClienteCampanhaWSInterface {

	protected TopicProducer producer = null;
	public ClienteCampanhaWS() {

	}

	public List<Campanha> consumirServicoCampanha() {

		List<Campanha> camList = new ArrayList<>();
		ResponseEntity<String> result = null;
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headersJson());

		try {
			result = restTemplate.exchange(URI_SERVICO, HttpMethod.GET, entity, String.class);
			camList = CampanhaDTO.transJsonClass(result.getBody());
		} catch (Exception e) {
			e.getMessage();
		}

		return camList;
	}

	public HttpHeaders headersJson() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		return headers;
	}

	@Override
	public void associarClienteCampanha(Long id) throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		final Map<String, Object> param = new HashMap<>();
		param.put("id", id);

		final HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		header.set("id", String.valueOf(id));
		final HttpEntity<?> entity = new HttpEntity<Object>(param, header);

		try {
			
			Gson gson = new Gson();
			this.producer = new TopicProducer("ENVIO_FILA_CAMP");
			Campanha camp = new Campanha();
			camp.setId(id);
			
			this.producer.send(gson.toJson(camp));
			
			restTemplate.put(URI_SERVICO_ASSOCIACAO, entity, param);
		} catch (Exception e) {
			e.getMessage();
			
		}
		

	}
}
