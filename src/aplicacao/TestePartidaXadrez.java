package aplicacao;

import java.util.Scanner;
import java.util.InputMismatchException;

import xadrez.PartidaXadrez;
import xadrez.PecasXadrez;
import xadrez.PosicaoXadrez;
import xadrez.ExcecaoXadrez;

public class TestePartidaXadrez {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();

        while (true) {
        	try {
        	    UI.limparTela();
        	    UI.imprimirPartida(partidaXadrez);
        	    System.out.println();
        	    System.out.print("Origem: ");
        	    PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
        	    boolean[][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
				UI.limparTela();
				UI.imprimirTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);
        	    System.out.println();
        	    System.out.print("Alvo: ");
        	    PosicaoXadrez alvo = UI.lerPosicaoXadrez(sc);        	    
        	    PecasXadrez pecaCapturada = partidaXadrez.realizarMovimentoXadrez(origem, alvo);
        	}
        	catch (ExcecaoXadrez e) {
        	    System.out.println(e.getMessage());
        	    sc.nextLine();
        	}
        	catch (InputMismatchException e) {
        	    System.out.println(e.getMessage());
        	    sc.nextLine();
        	}


        }
    }
}
