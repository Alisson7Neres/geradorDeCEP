@Entity
public class Pessoa{

	private String cep;
	
	private String logradouro;
	
	private String complemento;
	
	private String bairro;
	
	private String localidade;
	
	private String uf;
	
	@Transient // Não fica perssistente ou não grava, só fica em memória
	private Estados estados;
	
	@ManyToOne
	private Cidades cidades;
  
  public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public Estados getEstados() {
		return estados;
	}

	public void setEstados(Estados estados) {
		this.estados = estados;
	}

	public Cidades getCidades() {
		return cidades;
	}

	public void setCidades(Cidades cidades) {
		this.cidades = cidades;
	}
  

public void pesquisaCep(AjaxBehaviorEvent event) {
		try {
			// Monta URL
			URL url = new URL("https://viacep.com.br/ws/" +pessoa.getCep()+"/json/");
			// Abri a conexão
			URLConnection connection = url.openConnection();
			// Executa do lado do servidor
			InputStream is = connection.getInputStream();
			// Leitura de fluxo de dados
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			String cep = "";
			StringBuilder jsonCep = new StringBuilder();
			while((cep = br.readLine()) != null) {
				jsonCep.append(cep);
			}
			// Iniciando um novo objeto Gson
			 Pessoa gsonAux = new Gson().fromJson(jsonCep.toString(), Pessoa.class);
			
			pessoa.setCep(gsonAux.getCep());
			pessoa.setLogradouro(gsonAux.getLogradouro());
			pessoa.setComplemento(gsonAux.getComplemento());
			pessoa.setBairro(gsonAux.getBairro());
			pessoa.setLocalidade(gsonAux.getLocalidade());
			pessoa.setUf(gsonAux.getUf());
			
		}catch(Exception e){
			e.printStackTrace();
			mostrarMenssagem("Erro ao consultar CEP");
		  }
	  }
  }
