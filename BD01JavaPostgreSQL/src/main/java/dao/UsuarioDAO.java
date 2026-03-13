package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioDAO extends DAO {
	public UsuarioDAO() { super(); conectar(); }
	public void finalize() { close(); }
	
	public boolean insert(Usuario jogador) {
	    boolean status = false;
	    try {  
	        Statement st = conexao.createStatement();
	        // Não enviamos o 'id', o Postgres cria o próximo automaticamente
	        String sql = "INSERT INTO jogadores (numero, nome, time, posicao) "
	                   + "VALUES (" + jogador.getNumero() + ", '" + jogador.getNome() + "', '"  
	                   + jogador.getTime() + "', '" + jogador.getPosicao() + "');";
	        st.executeUpdate(sql);
	        st.close();
	        status = true;
	    } catch (SQLException u) { throw new RuntimeException(u); }
	    return status;
	}

	public List<Usuario> get() {	
		List<Usuario> jogadores = new ArrayList<Usuario>();
		try {
			Statement st = conexao.createStatement();
			String sql = "SELECT * FROM jogadores ORDER BY numero";
			ResultSet rs = st.executeQuery(sql);	           
			while(rs.next()) {	            	
				Usuario j = new Usuario(rs.getInt("numero"), rs.getString("nome"), rs.getString("time"), rs.getString("posicao"));
				jogadores.add(j);
			}
			st.close();
		} catch (Exception e) { System.err.println(e.getMessage()); }
		return jogadores;
	}

	public boolean update(Usuario jogador) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE jogadores SET nome = '" + jogador.getNome() + "', time = '"  
				       + jogador.getTime() + "', posicao = '" + jogador.getPosicao() + "'"
					   + " WHERE numero = " + jogador.getNumero();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) { throw new RuntimeException(u); }
		return status;
	}
	
	public boolean delete(int numero) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM jogadores WHERE numero = " + numero;
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) { throw new RuntimeException(u); }
		return status;
	}
}