package dao;

import model.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class jogadoresDAO extends DAO {

	public jogadoresDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}

	public boolean insert(Produto jogador) {
		boolean status = false;
		try {
			String sql = "INSERT INTO jogadores (nome, numero, time, posicao) VALUES (?, ?, ?, ?)";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setString(1, jogador.getNome());
			st.setInt(2, jogador.getNumero());
			st.setString(3, jogador.getTime());
			st.setString(4, jogador.getPosicao());
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	public Produto get(int id) {
		Produto jogador = null;		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM jogadores WHERE id=" + id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 jogador = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getInt("numero"), rs.getString("time"), rs.getString("posicao"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return jogador;
	}

	public List<Produto> get() {
		return get("");
	}

	public List<Produto> getOrderByID() {
		return get("id");		
	}
	
	public List<Produto> getOrderBynome() {
		return get("nome");		
	}
	
	public List<Produto> getOrderBynumero() {
		return get("numero");		
	}

	private List<Produto> get(String orderBy) {
		List<Produto> jogadoresList = new ArrayList<Produto>();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM jogadores" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Produto p = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getInt("numero"), rs.getString("time"), rs.getString("posicao"));
	            jogadoresList.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return jogadoresList;
	}

	public boolean update(Produto jogador) {
		boolean status = false;
		try {  
			String sql = "UPDATE jogadores SET nome = ?, numero = ?, time = ?, posicao = ? WHERE id = ?";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setString(1, jogador.getNome());
			st.setInt(2, jogador.getNumero());
			st.setString(3, jogador.getTime());
			st.setString(4, jogador.getPosicao());
			st.setInt(5, jogador.getId());
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM jogadores WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}