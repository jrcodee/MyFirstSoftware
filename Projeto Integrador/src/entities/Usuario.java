package entities;

public class Usuario {
	
	private String id;
	private String senha;
	private String tipoAcesso;
	
	public Usuario(String id, String senha, String tipoAcesso) {
		setId(id);
		setSenha(senha);
		setTipoAcesso(tipoAcesso);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(String tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}
	
	
}
