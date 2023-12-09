package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PecasXadrez;
import xadrez.PosicaoXadrez;
import xadrez.Color;

import xadrez.PecasXadrez;

public class UI {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
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

	public static void imprimirTabuleiro(PecasXadrez[][] pecas) {
	    for (int i = 0; i < pecas.length; i++) {
	        System.out.print((8 - i) + " ");
	        for (int j = 0; j < pecas.length; j++) {
	            imprimirPeca(pecas[i][j], false);
	        }
	        System.out.println();
	    }
	    System.out.println("  a b c d e f g h");
	}

	public static void imprimirTabuleiro(PecasXadrez[][] pecas, boolean[][] movimentosPossiveis) {
	    for (int i = 0; i < pecas.length; i++) {
	        System.out.print((8 - i) + " ");
	        for (int j = 0; j < pecas.length; j++) {
	            imprimirPeca(pecas[i][j], movimentosPossiveis[i][j]);
	        }
	        System.out.println();
	    }
	    System.out.println("  a b c d e f g h");
	}

	private static void imprimirPeca(PecasXadrez peca, boolean background) {
	    if (background) {
	        System.out.print(ANSI_BLUE_BACKGROUND);
	    }
	    
	    if (peca == null) {
	        System.out.print("-" + ANSI_RESET);
	    } else {
	        String cor = (peca.getColor() == Color.WHITE) ? ANSI_WHITE : ANSI_YELLOW;
	        System.out.print(cor + peca + ANSI_RESET);
	    }
	    
	    System.out.print(" ");
	}
}
