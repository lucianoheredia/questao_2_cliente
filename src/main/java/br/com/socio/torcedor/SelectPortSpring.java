package br.com.socio.torcedor;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;
/**
 * Classe criada para que a aplicação rode em outra porta.
 * Dessa forma, conseguimos rodar local as aplicações criadas.
 * @author Luciano
 *
 */
@Component
public class SelectPortSpring implements EmbeddedServletContainerCustomizer {

	    @Override
	    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
	       configurableEmbeddedServletContainer.setPort(8888);
	    }

	
	
}
