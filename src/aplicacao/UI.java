package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.PartidaXadrez;
import xadrez.PecasXadrez;
import xadrez.PosicaoXadrez;
import xadrez.Color;


public class UI {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	public static PosicaoXadrez lerPosicaoXadrez(Scanner sc) {
	    try {
	        String s = sc.nextLine();
	        char coluna = s.charAt(0);
	        int linha = Integer.parseInt(s.substring(1));
	        return new PosicaoXadrez(coluna, linha);
	    } catch (RuntimeException e) {
	        throw new InputMismatchException("Erro ao ler PosicaoXadrez. Valores válidos são de a1 a h8.");
	    }
	}
	
	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}	

	public static void imprimirPartida(PartidaXadrez partidaXadrez, List<PecasXadrez> capturada) {
		imprimirTabuleiro(partidaXadrez.getPecas());
		System.out.println();
		System.out.println("Rodada " + partidaXadrez.getTurno());
		if (!partidaXadrez.getXequeMate()) {
			System.out.println("Turno do jogador " + partidaXadrez.getJogadorAtual());
			if (partidaXadrez.getXeque()) {
				System.out.println("XEQUE!");
			}
		}
		else {
			System.out.println("XEQUE-MATE!");
			System.out.println("Ganhador " + partidaXadrez.getJogadorAtual() + "!!!");
		}
		
		imprimirPecasCapturadas(capturada);
	}
	
	public static void imprimirTabuleiro(PecasXadrez[][] pecas) {
	    for (int i = 0; i < pecas.length; i++) {
	        System.out.print(ANSI_BLUE + (8 - i) + ANSI_RESET + " ");
	        for (int j = 0; j < pecas.length; j++) {
	            imprimirPeca(pecas[i][j], false);
	        }
	        System.out.println();
	    }
	    System.out.println(ANSI_BLUE + "* a b c d e f g h" + ANSI_RESET);
	}

	public static void imprimirTabuleiro(PecasXadrez[][] pecas, boolean[][] movimentosPossiveis) {
	    for (int i = 0; i < pecas.length; i++) {
	        System.out.print(ANSI_BLUE + (8 - i) + ANSI_RESET + " ");
	        for (int j = 0; j < pecas.length; j++) {
	            imprimirPeca(pecas[i][j], movimentosPossiveis[i][j]);
	        }
	        System.out.println();
	    }
	    System.out.println(ANSI_BLUE + "* a b c d e f g h" + ANSI_RESET);
	}

	private static void imprimirPeca(PecasXadrez peca, boolean background) {
	    if (background) {
	        System.out.print(ANSI_BLUE_BACKGROUND);
	    }
	    
	    if (peca == null) {
	        System.out.print("-" + ANSI_RESET);
	    } else {
	        String cor = (peca.getColor() == Color.WHITE) ? ANSI_WHITE : ANSI_GREEN;
	        System.out.print(cor + peca + ANSI_RESET);
	    }
	    
	    System.out.print(" ");
	}
	private static void imprimirPecasCapturadas(List<PecasXadrez> capturada) {
		List<PecasXadrez> white = capturada.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<PecasXadrez> black = capturada.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		System.out.print("Pecas do jogador WHITE capturadas:");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(ANSI_RESET);
		System.out.print("Pecas do jogador BLACK capturadas:");
		System.out.print(ANSI_GREEN);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
	}
}
