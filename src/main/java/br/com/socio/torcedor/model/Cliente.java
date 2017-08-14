package br.com.socio.torcedor.model;

/**
 * 
 * @author Luciano
 *
 */
public class Cliente {
	
	public Cliente() {
	
	}

	private long id;

	private String nome;
	
	private String email;
	
	private String dataNascimento;
	
	private TimeCoracao timeCoracao;

	public Cliente(long id, String nome, String email, String dataNascimento, TimeCoracao timeCoracao ) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.timeCoracao = timeCoracao;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public TimeCoracao getTimeCoracao() {
		return timeCoracao;
	}

	public void setTimeCoracao(TimeCoracao timeCoracao) {
		this.timeCoracao = timeCoracao;
	}


	
}