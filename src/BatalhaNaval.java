import java.util.Scanner;

public class BatalhaNaval {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        char[][] tabuleiro = criarTabuleiro();

        int opcao = menuInicial(input);

        seletorMenuInicial(opcao, input);




    }

    public static int entradaUsuario(Scanner input) {
        return input.nextInt();
    }

    public static String entradaUsuarioTexto(Scanner input){
        return input.nextLine();
    }

    public static int menuInicial(Scanner input) {

        System.out.println("╔══════════════════════════╗");
        System.out.println("║      BATALHA NAVAL       ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║   [ 0 ]  SAIR            ║");
        System.out.println("║   [ 1 ]  JOGAR           ║");
        System.out.println("╚══════════════════════════╝");
        System.out.print("Escolha uma opcao: ");

        return entradaUsuario(input);

    }

    public static void seletorMenuInicial(int opcao, Scanner input) {

        switch (opcao) {
            case 0:
                System.out.println("Saindo do Jogo!");
                break;
            case 1:
                int opcaoModoJogo;
                opcaoModoJogo = menuDoJogo(input);
                seletorMenuDoJogo(opcaoModoJogo, input);
                break;
        }

    }

    public static int menuDoJogo(Scanner input) {

        int opcao;

        System.out.println("╔══════════════════════════╗");
        System.out.println("║        MODO DE JOGO      ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║[ 1 ]  JOGADOR VS JOGADOR ║");
        System.out.println("║[ 2 ]  JOGADOR VS MAQUINA ║");
        System.out.println("╚══════════════════════════╝");
        System.out.print("Escolha uma opcao: ");

        opcao = entradaUsuario(input);

        return opcao;

    }

    public static int seletorMenuDoJogo(int opcao, Scanner input) {

        switch (opcao) {
            case 1:

               String[] jogadores = modoJogoPvP(input);
               inicioDoJogoPvp(jogadores, input);

                break;
            case 2:

                break;
        }

        return opcao;

    }

    public static String[] modoJogoPvP (Scanner input){

        String[] jogadores = new String[2];

        System.out.println("╔══════════════════════════╗");
        System.out.println("║        MODO DE JOGO      ║");
        System.out.println("║     JOGADOR VS JOGADOR   ║");
        System.out.println("╚══════════════════════════╝");

        System.out.println("╔══════════════════════════╗");
        System.out.println("║        JOGADOR UM        ║");
        System.out.println("╚══════════════════════════╝");
        System.out.println("Digite o nome do jogador 1: ");
        input.nextLine();
        jogadores[0] = entradaUsuarioTexto(input);


        System.out.println("╔══════════════════════════╗");
        System.out.println("║        JOGADOR DOIS      ║");
        System.out.println("╚══════════════════════════╝");
        System.out.println("Digite o nome do jogador 2: ");
        jogadores[1] = entradaUsuarioTexto(input);

        return jogadores;

    }

    public static void inicioDoJogoPvp (String[] jogadores,Scanner input){

        char[][] naviosJ1 = criarTabuleiro();
        char[][] tirosJ1 = criarTabuleiro();
        char[][] naviosJ2 = criarTabuleiro();
        char[][] tirosJ2 = criarTabuleiro();

    }

    public static void posicionarNavio (char[][] navios, Scanner input, String[] jogadores, int tamanhoNavio){



    }


    public static void jogador1 (Scanner input){

        String nome;

        System.out.println("╔══════════════════════════╗");
        System.out.println("║        JOGADOR UM        ║");
        System.out.println("╚══════════════════════════╝");

        System.out.println("Digite seu nome: ");
        nome = entradaUsuarioTexto(input);


    }



    public static char[][] criarTabuleiro() {

        char[][] tabuleiro = new char[10][10];


        for (int i = 0; i < tabuleiro.length; i++) {


            for (int j = 0; j < tabuleiro[i].length; j++) {

                tabuleiro[i][j] = '~';
            }


        }


        return tabuleiro;
    }

    public static void mostrarTabuleiro(char[][] tabuleiro) {

        System.out.printf("  "); // espaço para alinhar com o número da linha
        for (int j = 0; j < tabuleiro[0].length; j++) {
            System.out.printf("%d ", j);
        }
        System.out.println();


        for (int i = 0; i < tabuleiro.length; i++) {
            System.out.printf("%d ", i);
            for (int j = 0; j < tabuleiro[i].length; j++) {
                System.out.printf("%c ", tabuleiro[i][j]);
            }
            System.out.println();
        }

    }

}