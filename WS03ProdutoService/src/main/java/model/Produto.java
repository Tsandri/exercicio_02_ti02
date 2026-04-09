package model;

public class Produto {
	private int id;
	private String nome;
	private int numero;
	private String time;
	private String posicao;
	
	public Produto() {
	    id = -1;
	    nome = "";
	    numero = 0;
	    time = "";
	    posicao = "";
	}

	public Produto(int id, String nome, int numero, String time, String posicao) {
	    setId(id);
	    setNome(nome);
	    setNumero(numero);
	    setTime(time);
	    setPosicao(posicao);
	}		
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	@Override
	public String toString() {
	    return "Jogador: " + nome + " | Número: " + numero + " | Time: " + time + " | Posição: " + posicao;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Produto) obj).getId());
	}	
}