import java.util.Scanner;

public class BatalhaNaval {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        iniciar(input);


    }

    public static void iniciar(Scanner input) {
        int numeroValido;
        do {
            mostrarMenu();
            int entrada = entradaDadosNum(input, "Escolha uma opção do Menu:");
            numeroValido = validarEntradaMenu(entrada, input, 0, 3);
            seletorMenu(input, numeroValido);
        } while (numeroValido != 0);


    }

    public static void mostrarMenu() {
        System.out.println("╔════════════════════════════════╗");
        System.out.println("       ⚓ BATALHA NAVAL ⚓        ");
        System.out.println("╠════════════════════════════════╣");
        System.out.println("║  [ 1 ]  JOGADOR VS JOGADOR     ║");
        System.out.println("║  [ 2 ]  JOGADOR VS MAQUINA     ║");
        System.out.println("║  [ 3 ]  REGRAS DO JOGO         ║");
        System.out.println("║  [ 0 ]  SAIR                   ║");
        System.out.println("╚════════════════════════════════╝");
    }

    public static void mostrarRegras() {
        System.out.printf("╔══════════════════════════════════════╗%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("║   ⚓  B A T A L H A  N A V A L  ⚓   ║%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("╠══════════════════════════════════════╣%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("║   🌊  COMO JOGAR                     ║%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("║   Posicione seus navios no           ║%n");
        System.out.printf("║   tabuleiro 10x10 e tente            ║%n");
        System.out.printf("║   afundar a frota inimiga!           ║%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("╠══════════════════════════════════════╣%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("║   🚢  FROTA                          ║%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("║   ████  Porta-Aviao   (4 casas)      ║%n");
        System.out.printf("║   ███   Cruzador      (3 casas)      ║%n");
        System.out.printf("║   ██    Destroyer     (2 casas)      ║%n");
        System.out.printf("║   █     Submarino     (1 casa) x2    ║%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("╠══════════════════════════════════════╣%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("║   🗺  LEGENDA                        ║%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("║   ~  =  Agua       X  =  Acerto      ║%n");
        System.out.printf("║   N  =  Navio      O  =  Erro        ║%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("╠══════════════════════════════════════╣%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("║   🏆  Afunde todos os navios         ║%n");
        System.out.printf("║       inimigos primeiro e VENCA!     ║%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("╚══════════════════════════════════════╝%n");
    }

    public static int entradaDadosNum(Scanner input, String texto) {
        int entrada;

        System.out.println(texto);
        entrada = input.nextInt();

        return entrada;
    }

    public static int validarEntradaMenu(int entrada, Scanner input, int parametroA, int parametroB) {


        while (entrada < parametroA || entrada > parametroB) {
            System.out.println("Digite uma opcao valida:");
            entrada = input.nextInt();
        }

        return entrada;
    }

    public static String[] solicitarNome(Scanner input) {

        String[] jogadores = new String[2];

        input.nextLine();
        for (int i = 0; i < jogadores.length; i++) {
            System.out.println("Digite o nome do jogador " + (i + 1) + ":");
            jogadores[i] = input.nextLine();
        }

        return jogadores;
    }

    public static void seletorMenu(Scanner input, int numeroValido) {

        switch (numeroValido) {

            case 0:
                System.out.println("Saindo da batalha, até breve marujo!!");
                break;
            case 1:
                String[] jogadores = solicitarNome(input);
                modoPVP(input, jogadores);
                break;
            case 2:
                System.out.println("teste 2");
                break;
            case 3:
                mostrarRegras();
                break;

        }

    }

    public static void modoPVP(Scanner input, String[] jogadores) {

        char[][] tabuleiro = criarTabuleiro();
        char[][] naviosJogador1 = criarTabuleiro();
        char[][] naviosJogador2 = criarTabuleiro();
        char[][] acertosJogador1 = criarTabuleiro();
        char[][] acertosJogador2 = criarTabuleiro();

        mostrarApresentacao(jogadores, tabuleiro);

        posicionarFrota(input, naviosJogador1, jogadores, 0);

        trocarJogador(input);

        mostrarTabuleiro(tabuleiro);

        posicionarFrota(input, naviosJogador2, jogadores, 1);


    }

    public static void mostrarApresentacao(String[] jogadores, char[][] tabuleiro) {

        System.out.printf("╔══════════════════════════════════════╗%n");
        System.out.printf("║   ⚓  BEM-VINDOS, CAPITÃES!  ⚓      %n");
        System.out.printf("╠══════════════════════════════════════╣%n");
        System.out.printf("║  🎖  %-33s║%n", jogadores[0]);
        System.out.printf("║  ⚔   VS                              ║%n");
        System.out.printf("║  🎖  %-33s║%n", jogadores[1]);
        System.out.printf("╠══════════════════════════════════════╣%n");
        System.out.printf("║     A BATALHA JÁ VAI COMEÇAR...      %n");
        System.out.printf("╠══════════════════════════════════════╣%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("║   🚢  FROTA                          %n");
        System.out.printf("║                                      ║%n");
        System.out.printf("║   ████  Porta-Aviao   (4 casas)      ║%n");
        System.out.printf("║   ███   Cruzador      (3 casas)      ║%n");
        System.out.printf("║   ██    Destroyer     (2 casas)      ║%n");
        System.out.printf("║   █     Submarino     (1 casa) x2    ║%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("╠══════════════════════════════════════╣%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("║   🗺  LEGENDA                        %n");
        System.out.printf("║                                      ║%n");
        System.out.printf("║   ~  =  Agua       X  =  Acerto      ║%n");
        System.out.printf("║   N  =  Navio      O  =  Erro        ║%n");
        System.out.printf("║                                      ║%n");
        System.out.printf("╚══════════════════════════════════════╝%n");

        mostrarTabuleiro(tabuleiro);


    }

    public static void mostrarTabuleiro(char[][] tabuleiro) {

        System.out.printf("%n");
        System.out.printf("     0  1  2  3  4  5  6  7  8  9%n");
        System.out.printf("   ┌──────────────────────────────┐%n");

        for (int i = 0; i < tabuleiro.length; i++) {
            System.out.printf(" %d │", i);
            for (int j = 0; j < tabuleiro[i].length; j++) {
                System.out.printf(" %c ", tabuleiro[i][j]);
            }
            System.out.printf("│ %n");
        }

        System.out.printf("   └──────────────────────────────┘%n");


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

    public static void posicionarNavio(Scanner input, char[][] tabuleiro, int tamanho) {

        int linha = entradaDadosNum(input, "Digite a posição da linha (0-9)");
        int coluna = entradaDadosNum(input, "Digite a posição da coluna (0-9)");
        int orientacao = validarEntradaMenu(entradaDadosNum(input, "Orientacao (1=horizontal, 2=vertical):"), input, 1, 2);
        boolean valido = posicaoValida(tabuleiro, linha, coluna, tamanho, orientacao);

        while (!valido) {
            System.out.println("Posicao invalida! Tente novamente.");
            linha = entradaDadosNum(input, "Digite a posição da linha (0-9)");
            coluna = entradaDadosNum(input, "Digite a posição da coluna (0-9)");
            orientacao = validarEntradaMenu(entradaDadosNum(input, "Orientacao (1=horizontal, 2=vertical):"), input, 1, 2);
            valido = posicaoValida(tabuleiro, linha, coluna, tamanho, orientacao);
        }

        if (orientacao == 1) {
            for (int i = coluna; i < coluna + tamanho; i++) {

                tabuleiro[linha][i] = 'N';


            }
            mostrarTabuleiro(tabuleiro);
        } else {
            for (int i = linha; i < linha + tamanho; i++) {

                tabuleiro[i][coluna] = 'N';

            }
            mostrarTabuleiro(tabuleiro);
        }


    }

    public static void posicionarFrota(Scanner input, char[][] tabuleiro, String[] jogadores, int jogador) {

        System.out.println("Capitão " + jogadores[jogador] + ", posicione sua frota!");

        posicionarNavio(input, tabuleiro, 4);
        posicionarNavio(input, tabuleiro, 3);
        posicionarNavio(input, tabuleiro, 2);
        posicionarNavio(input, tabuleiro, 1);
        posicionarNavio(input, tabuleiro, 1);
    }

    public static boolean posicaoValida(char[][] tabuleiro, int linha, int coluna, int tamanho, int orientacao) {

        for (int i = 0; i < tamanho; i++) {

            int l;
            int c;

            if (orientacao == 1) {
                l = linha;
                c = coluna + i;
            } else {
                l = linha + i;
                c = coluna;
            }

            if (l < 0 || l >= 10 || c < 0 || c >= 10) {
                return false;
            }

            if (tabuleiro[l][c] == 'N') {
                return false;
            }
        }

        return true;
    }

    public static void trocarJogador (Scanner input){

        System.out.println("\n=================================");
        System.out.println("Passe o computador para o próximo jogador.");
        System.out.println("Pressione ENTER para continuar...");
        System.out.println("=================================");

        input.nextLine();
        input.nextLine();

        for (int i = 0; i < 100; i++) {
            System.out.println();
        }

    }



}

