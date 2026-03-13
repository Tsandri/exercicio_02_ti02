package model;

public class Usuario {
	private int id;
	private int numero;
	private String nome;
	private String time;
	private String posicao;
	
	public Usuario() {
		this.numero = -1;
		this.nome = "";
		this.time = "";
		this.posicao = "";
	}
	
	public Usuario(int numero, String nome, String time, String posicao) {
		this.numero = numero;
		this.nome = nome;
		this.time = time;
		this.posicao = posicao;
	}

	public int getNumero() { return numero; }
	public void setNumero(int numero) { this.numero = numero; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public String getTime() { return time; }
	public void setTime(String time) { this.time = time; }
	public String getPosicao() { return posicao; }
	public void setPosicao(String posicao) { this.posicao = posicao; }

	@Override
	public String toString() {
		return "Jogador [numero=" + numero + ", nome=" + nome + ", time=" + time + ", posicao=" + posicao + "]";
	}	
}