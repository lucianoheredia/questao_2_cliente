package br.com.socio.torcedor.utils;

public class RestSocioTorcedorErrorType {
	
	private String mensagemErroReq;
	 
    public RestSocioTorcedorErrorType(String mensagemErroReq){
        this.mensagemErroReq = mensagemErroReq;
    }
 
    public String getErrorMessage() {
        return mensagemErroReq;
    }
}
