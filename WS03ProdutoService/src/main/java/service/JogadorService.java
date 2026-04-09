package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.jogadoresDAO;
import model.Produto;
import spark.Request;
import spark.Response;

public class JogadorService {

	private jogadoresDAO jogadoresdao = new jogadoresDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_ORDERBY_NUMERO = 3;
	
	public JogadorService() {
		makeForm();
	}

	public void makeForm() {
		makeForm(FORM_INSERT, new Produto(), FORM_ORDERBY_NOME);
	}

	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Produto(), orderBy);
	}

	public void makeForm(int tipo, Produto produto, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umProduto = "";
		if(tipo != FORM_INSERT) {
			umProduto += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/jogador/list/1\">Novo Jogador</a></b></font></td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t</table>";
			umProduto += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/jogador/";
			String name, nomeJogador, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Jogador";
				nomeJogador = "Ex: Neymar, Messi...";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + produto.getId();
				name = "Atualizar Jogador (ID " + produto.getId() + ")";
				nomeJogador = produto.getNome();
				buttonLabel = "Atualizar";
			}
			umProduto += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umProduto += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""+ nomeJogador +"\"></td>";
			umProduto += "\t\t\t<td>Número: <input class=\"input--register\" type=\"number\" name=\"numero\" value=\""+ produto.getNumero() +"\"></td>";
			umProduto += "\t\t\t<td>Time: <input class=\"input--register\" type=\"text\" name=\"time\" value=\""+ produto.getTime() +"\"></td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td>Posição: <input class=\"input--register\" type=\"text\" name=\"posicao\" value=\""+ produto.getPosicao() +"\"></td>";
			umProduto += "\t\t\t<td align=\"center\" colspan=\"2\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t</table>";
			umProduto += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umProduto += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Jogador (ID " + produto.getId() + ")</b></font></td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td>&nbsp;Nome: "+ produto.getNome() +"</td>";
			umProduto += "\t\t\t<td>Número: "+ produto.getNumero() +"</td>";
			umProduto += "\t\t\t<td>Time: "+ produto.getTime() +"</td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td>&nbsp;Posição: "+ produto.getPosicao() + "</td>";
			umProduto += "\t\t\t<td colspan=\"2\">&nbsp;</td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t</table>";		
		} 
		form = form.replaceFirst("<UM-PRODUTO>", umProduto);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Jogadores</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/jogador/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/jogador/list/" + FORM_ORDERBY_NOME + "\"><b>Nome</b></a></td>\n" +
        		"\t<td><a href=\"/jogador/list/" + FORM_ORDERBY_NUMERO + "\"><b>Número</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Produto> produtos;
		if (orderBy == FORM_ORDERBY_ID) {                 	produtos = jogadoresdao.getOrderByID();
		} else if (orderBy == FORM_ORDERBY_NOME) {		    produtos = jogadoresdao.getOrderBynome();
		} else if (orderBy == FORM_ORDERBY_NUMERO) {		produtos = jogadoresdao.getOrderBynumero();
		} else {											produtos = jogadoresdao.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Produto p : produtos) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getId() + "</td>\n" +
            		  "\t<td>" + p.getNome() + "</td>\n" +
            		  "\t<td>" + p.getNumero() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/jogador/" + p.getId() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/jogador/update/" + p.getId() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteProduto('" + p.getId() + "', '" + p.getNome() + "', '" + p.getNumero() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-PRODUTO>", list);				
	}
	
	public Object insert(Request request, Response response) {
		String nome = request.queryParams("nome");
		int numero = Integer.parseInt(request.queryParams("numero"));
		String time = request.queryParams("time");
		String posicao = request.queryParams("posicao");
		
		String resp = "";
		Produto produto = new Produto(-1, nome, numero, time, posicao);
		
		if(jogadoresdao.insert(produto) == true) {
            resp = "Jogador (" + nome + ") inserido!";
            response.status(201); 
		} else {
			resp = "Jogador (" + nome + ") não inserido!";
			response.status(404); 
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Produto produto = (Produto) jogadoresdao.get(id);
		
		if (produto != null) {
			response.status(200); 
			makeForm(FORM_DETAIL, produto, FORM_ORDERBY_NOME);
        } else {
            response.status(404); 
            String resp = "Jogador " + id + " não encontrado.";
    		makeForm();
    		form = form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }
		return form;
	}
	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Produto produto = (Produto) jogadoresdao.get(id);
		
		if (produto != null) {
			response.status(200); 
			makeForm(FORM_UPDATE, produto, FORM_ORDERBY_NOME);
        } else {
            response.status(404); 
            String resp = "Jogador " + id + " não encontrado.";
    		makeForm();
    		form = form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }
		return form;
	}
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Produto produto = jogadoresdao.get(id);
        String resp = "";       

        if (produto != null) {
        	produto.setNome(request.queryParams("nome"));
        	produto.setNumero(Integer.parseInt(request.queryParams("numero")));
        	produto.setTime(request.queryParams("time"));
        	produto.setPosicao(request.queryParams("posicao"));
        	jogadoresdao.update(produto);
        	response.status(200); 
            resp = "Jogador (ID " + produto.getId() + ") atualizado!";
        } else {
            response.status(404); 
            resp = "Jogador (ID " + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Produto produto = jogadoresdao.get(id);
        String resp = "";       

        if (produto != null) {
            jogadoresdao.delete(id);
            response.status(200); 
            resp = "Jogador (" + id + ") excluído!";
        } else {
            response.status(404); 
            resp = "Jogador (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}