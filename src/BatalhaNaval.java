import java.util.Scanner;

public class BatalhaNaval {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        iniciar(input);


    }

    public static void iniciar(Scanner input){
        int numeroValido;
        do {
            mostrarMenu();
            int entrada = entradaDadosNum(input, "Escolha uma opção do Menu:");
             numeroValido= validarEntradaMenu(entrada,input,0,3);
            seletorMenu(input, numeroValido);
        }while (numeroValido != 0);


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

    public static int entradaDadosNum (Scanner input, String texto){
        int entrada;

        System.out.println(texto);
        entrada = input.nextInt();

        return entrada;
    }

    public static int validarEntradaMenu (int entrada, Scanner input, int parametroA, int parametroB){


        while (entrada < parametroA || entrada > parametroB){
            System.out.println("Digite uma opcao valida:");
            entrada = input.nextInt();
        }

        return entrada;
    }

    public static String[] solicitarNome(Scanner input){

        String[] jogadores = new String[2];

        input.nextLine();
        for (int i = 0; i < jogadores.length; i++) {
            System.out.println("Digite o nome do jogador " + (i + 1) + ":");
            jogadores[i] = input.nextLine();
        }

        return jogadores;
    }

    public static void seletorMenu(Scanner input,int numeroValido){

        switch (numeroValido){

            case 0:
                System.out.println("Saindo da batalha, até breve marujo!!");
                break;
            case 1:
                String[] jogadores = solicitarNome(input);
                modoPVP(input,jogadores);
                break;
            case 2:
                System.out.println("teste 2");
                break;
            case 3:
                mostrarRegras();
                break;

        }

    }

    public static void modoPVP (Scanner input, String[] jogadores){

        char[][] tabuleiro = criarTabuleiro();
        char[][] naviosJogador1 = criarTabuleiro();
        char[][] naviosJogador2 = criarTabuleiro();
        char[][] acertosJogador1 = criarTabuleiro();
        char[][] acertosJogador2 = criarTabuleiro();



        mostrarApresentacao(jogadores,tabuleiro);






    }

    public static void mostrarApresentacao(String[] jogadores, char[][] tabuleiro){

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

    public static void mostrarTabuleiro(char[][] tabuleiro){

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

    public  static char[][] criarTabuleiro(){

        char[][] tabuleiro = new char[10][10];

        for (int i = 0; i <tabuleiro.length; i++) {

            for (int j = 0; j < tabuleiro[i].length; j++) {

                tabuleiro[i][j] = '~';

            }

        }

        return tabuleiro;

    }




}

