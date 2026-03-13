package app;

import java.util.List;
import java.util.Scanner;
import dao.UsuarioDAO;
import model.Usuario;

public class Aplicacao {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UsuarioDAO dao = new UsuarioDAO();
		int opcao = 0;

		do {
			System.out.println("\n==== MENU JOGADORES ====");
			System.out.println("1 - Listar");
			System.out.println("2 - Inserir");
			System.out.println("3 - Excluir");
			System.out.println("4 - Atualizar");
			System.out.println("5 - Sair");
			opcao = sc.nextInt(); sc.nextLine();

			switch (opcao) {
			case 1:
				List<Usuario> lista = dao.get();
				for (Usuario j : lista) System.out.println(j.toString());
				break;
			case 2:
				System.out.print("Número: "); int n = sc.nextInt(); sc.nextLine();
				System.out.print("Nome: "); String nome = sc.nextLine();
				System.out.print("Time: "); String time = sc.nextLine();
				System.out.print("Posição: "); String pos = sc.nextLine();
				if (dao.insert(new Usuario(n, nome, time, pos))) System.out.println("Inserido!");
				break;
			case 3:
				System.out.print("Número para excluir: ");
				if (dao.delete(sc.nextInt())) System.out.println("Excluído!");
				break;
			case 4:
				System.out.print("Número para atualizar: "); int nu = sc.nextInt(); sc.nextLine();
				System.out.print("Novo Nome: "); String nn = sc.nextLine();
				System.out.print("Novo Time: "); String nt = sc.nextLine();
				System.out.print("Nova Posição: "); String np = sc.nextLine();
				if (dao.update(new Usuario(nu, nn, nt, np))) System.out.println("Atualizado!");
				break;
			}
		} while (opcao != 5);
		sc.close();
	}
}