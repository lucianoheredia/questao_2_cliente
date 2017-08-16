package br.com.socio.torcedor.rules;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.socio.torcedor.kafka.TopicProducer;
import br.com.socio.torcedor.model.Cliente;

/**
 * Regras de negócios
 * @author Luciano
 *
 */

@Service
@Qualifier("CampanhaRules")
public class ClienteRules extends BaseRules {

	public ClienteRules() throws Exception {
		this.producer = new TopicProducer("ENVIO_FILA_CAMP");
	}

	public Boolean clienteValidation(Cliente cliente) throws ParseException {

		final Boolean req = this.validationRequest(cliente);
		if (req != null) {
			final Boolean clienteExist = this.isClienteExistBool(cliente);
			if(clienteExist){
				return true;
			}

		
	}
		return false;
	}
}
