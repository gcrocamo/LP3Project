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

	public static void imprimirTabuleiro(PecasXadrez[][] pecas) {
	    for (int i = 0; i < pecas.length; i++) {
	        System.out.print((8 - i) + " ");
	        for (int j = 0; j < pecas.length; j++) {
	            imprimirPeca(pecas[i][j]);
	        }
	        System.out.println();
	    }
	    System.out.println("  a b c d e f g h");
	}

	private static void imprimirPeca(PecasXadrez peca) {
	    if (peca == null) {
	        System.out.print("-");
	    } else {
	        if (peca.getColor() == Color.WHITE) {
	            System.out.print(ANSI_WHITE + peca + ANSI_RESET);
	        } else {
	            System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
	        }
	    }
	    System.out.print(" ");
	}
}
