package br.com.socio.torcedor.rules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import br.com.socio.torcedor.cliente.ClienteRepository;
import br.com.socio.torcedor.kafka.TopicProducer;
import br.com.socio.torcedor.model.Cliente;

public class BaseRules {

	@Autowired
	ClienteRepository resitory;
	
	protected TopicProducer producer = null;
    protected final Gson gson = new Gson();
	
	protected Boolean validationRequest(Cliente client) {
		return client != null;
	}

	protected List<Cliente> isClienteExist(Cliente cliente) {
		return this.resitory.findClienteValidation(cliente);
	}
	
	protected Boolean isClienteExistBool(Cliente cliente) {
		return this.resitory.findClienteValidationBool(cliente);
	}

	protected String adicionarUmDiaData(String data) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dataCamp = new Date(format.parse(data).getTime());

		Calendar cal = Calendar.getInstance();
		cal.setTime(dataCamp);
		cal.add(cal.DAY_OF_MONTH, +1);

		return format.format(cal.getTime());

	}

}
