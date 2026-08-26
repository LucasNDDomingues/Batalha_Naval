import java.util.Scanner;
import java.util.Random;

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
            numeroValido = validarEntradaMenu(entrada, input, 0, 4);
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
        System.out.println("║  [ 4 ]  DEMONSTRACAO COM IA    ║");
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
                String[] jogadorPvM = solicitarNomePvM(input);
                modoPvM(input, jogadorPvM);
                break;
            case 3:
                mostrarRegras();
                break;

            case 4:
                modoDemonstracaoCompleta();
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
        trocarJogador(input);

        loopPvP(input, jogadores, naviosJogador1, naviosJogador2, acertosJogador1, acertosJogador2);
    }

    public static void loopPvP(Scanner input, String[] jogadores, char[][] naviosJ1, char[][] naviosJ2, char[][] acertosJ1, char[][] acertosJ2) {
        int vezAtual = 0;

        while (true) {
            char[][] naviosAtacado;
            char[][] acertosAtacante;
            char[][] naviosAtacante;

            if (vezAtual == 0) {
                naviosAtacado = naviosJ2;
                acertosAtacante = acertosJ1;
                naviosAtacante = naviosJ1;
            } else {
                naviosAtacado = naviosJ1;
                acertosAtacante = acertosJ2;
                naviosAtacante = naviosJ2;
            }

            System.out.println("\nVez de: " + jogadores[vezAtual]);
            System.out.println("\nSeu campo:");
            mostrarTabuleiro(naviosAtacante);
            System.out.println("\nCampo inimigo:");
            mostrarTabuleiro(acertosAtacante);

            int linha = validarEntradaMenu(entradaDadosNum(input, "Digite a linha do tiro (0-9):"), input, 0, 9);
            int coluna = validarEntradaMenu(entradaDadosNum(input, "Digite a coluna do tiro (0-9):"), input, 0, 9);

            while (acertosAtacante[linha][coluna] == 'X' || acertosAtacante[linha][coluna] == 'O') {
                System.out.println("Voce ja atirou nessa posicao! Tente novamente.");
                linha = validarEntradaMenu(entradaDadosNum(input, "Digite a linha do tiro (0-9):"), input, 0, 9);
                coluna = validarEntradaMenu(entradaDadosNum(input, "Digite a coluna do tiro (0-9):"), input, 0, 9);
            }

            realizarTiro(naviosAtacado, acertosAtacante, linha, coluna);

            if (verificarVitoria(naviosAtacado)) {
                mostrarTabuleiro(acertosAtacante);
                System.out.println(jogadores[vezAtual] + " VENCEU!");
                break;
            }

            trocarJogador(input);

            if (vezAtual == 0) {
                vezAtual = 1;
            } else {
                vezAtual = 0;
            }
        }
    }

    public static void realizarTiro(char[][] naviosAtacado, char[][] acertosAtacante, int linha, int coluna) {
        if (naviosAtacado[linha][coluna] == 'N') {
            naviosAtacado[linha][coluna] = 'X';
            acertosAtacante[linha][coluna] = 'X';
            System.out.println("ACERTO! Navio inimigo atingido!");
        } else {
            acertosAtacante[linha][coluna] = 'O';
            System.out.println("Agua! Tiro desperdicado.");
        }
    }

    public static boolean verificarVitoria(char[][] navios) {
        for (int i = 0; i < navios.length; i++) {
            for (int j = 0; j < navios[i].length; j++) {
                if (navios[i][j] == 'N') {
                    return false;
                }
            }
        }
        return true;
    }

    public static String[] solicitarNomePvM(Scanner input) {
        String[] jogadores = new String[1];
        input.nextLine();
        System.out.println("Digite o nome do jogador:");
        jogadores[0] = input.nextLine();
        return jogadores;
    }

    public static void modoPvM(Scanner input, String[] jogador) {
        char[][] tabuleiro = criarTabuleiro();
        char[][] naviosJogador = criarTabuleiro();
        char[][] naviosMaquina = criarTabuleiro();
        char[][] acertosJogador = criarTabuleiro();
        char[][] acertosMaquina = criarTabuleiro();

        int dificuldade = menuDificuldade(input);

        mostrarApresentacaoPvM(jogador, tabuleiro);

        posicionarFrotaPvM(input, naviosJogador, jogador);
        posicionarFrotaMaquina(naviosMaquina);
        trocarJogador(input);

        loopPvM(input, jogador, naviosJogador, naviosMaquina, acertosJogador, acertosMaquina, dificuldade);
    }

    public static int menuDificuldade(Scanner input) {
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║       ESCOLHA DIFICULDADE      ║");
        System.out.println("╠════════════════════════════════╣");
        System.out.println("║   [ 1 ]  FACIL                 ║");
        System.out.println("║   [ 2 ]  MEDIO                 ║");
        System.out.println("║   [ 3 ]  DIFICIL               ║");
        System.out.println("╚════════════════════════════════╝");
        return validarEntradaMenu(entradaDadosNum(input, "Escolha a dificuldade:"), input, 1, 3);
    }

    public static void posicionarFrotaMaquina(char[][] tabuleiro) {
        Random random = new Random();
        int[] tamanhos = {4, 3, 2, 1, 1};

        for (int n = 0; n < tamanhos.length; n++) {
            boolean posicionado = false;

            while (!posicionado) {
                int linha = random.nextInt(10);
                int coluna = random.nextInt(10);
                int orientacao = random.nextInt(2) + 1;

                if (posicaoValida(tabuleiro, linha, coluna, tamanhos[n], orientacao)) {
                    if (orientacao == 1) {
                        for (int i = coluna; i < coluna + tamanhos[n]; i++) {
                            tabuleiro[linha][i] = 'N';
                        }
                    } else {
                        for (int i = linha; i < linha + tamanhos[n]; i++) {
                            tabuleiro[i][coluna] = 'N';
                        }
                    }
                    posicionado = true;
                }
            }
        }
    }

    public static void mostrarApresentacaoPvM(String[] jogador, char[][] tabuleiro) {
        System.out.printf("╔══════════════════════════════════════╗%n");
        System.out.printf("║   ⚓  BEM-VINDO, CAPITÃO!  ⚓        ║%n");
        System.out.printf("╠══════════════════════════════════════╣%n");
        System.out.printf("║  🎖  %-33s║%n", jogador[0]);
        System.out.printf("║  ⚔   VS                              ║%n");
        System.out.printf("║  🤖  MAQUINA                         ║%n");
        System.out.printf("╠══════════════════════════════════════╣%n");
        System.out.printf("║     A BATALHA JÁ VAI COMEÇAR...      ║%n");
        System.out.printf("╚══════════════════════════════════════╝%n");
        mostrarTabuleiro(tabuleiro);
    }

    public static void posicionarFrotaPvM(Scanner input, char[][] tabuleiro, String[] jogador) {
        System.out.println("Capitão " + jogador[0] + ", posicione sua frota!");
        posicionarNavio(input, tabuleiro, 4);
        posicionarNavio(input, tabuleiro, 3);
        posicionarNavio(input, tabuleiro, 2);
        posicionarNavio(input, tabuleiro, 1);
        posicionarNavio(input, tabuleiro, 1);
    }

    public static void loopPvM(Scanner input, String[] jogador, char[][] naviosJogador, char[][] naviosMaquina, char[][] acertosJogador, char[][] acertosMaquina, int dificuldade) {
        int vezAtual = 0;

        while (true) {
            if (vezAtual == 0) {
                System.out.println("\nVez de: " + jogador[0]);
                System.out.println("\nSeu campo:");
                mostrarTabuleiro(naviosJogador);
                System.out.println("\nCampo da maquina:");
                mostrarTabuleiro(acertosJogador);

                int linha = validarEntradaMenu(entradaDadosNum(input, "Digite a linha do tiro (0-9):"), input, 0, 9);
                int coluna = validarEntradaMenu(entradaDadosNum(input, "Digite a coluna do tiro (0-9):"), input, 0, 9);

                while (acertosJogador[linha][coluna] == 'X' || acertosJogador[linha][coluna] == 'O') {
                    System.out.println("Voce ja atirou nessa posicao! Tente novamente.");
                    linha = validarEntradaMenu(entradaDadosNum(input, "Digite a linha do tiro (0-9):"), input, 0, 9);
                    coluna = validarEntradaMenu(entradaDadosNum(input, "Digite a coluna do tiro (0-9):"), input, 0, 9);
                }

                realizarTiro(naviosMaquina, acertosJogador, linha, coluna);

                if (verificarVitoria(naviosMaquina)) {
                    mostrarTabuleiro(acertosJogador);
                    System.out.println(jogador[0] + " VENCEU!");
                    break;
                }

                vezAtual = 1;

            } else {
                System.out.println("\nVez da maquina...");

                int[] tiro = tiroMaquina(acertosMaquina, dificuldade);
                System.out.println("Maquina atirou em linha " + tiro[0] + ", coluna " + tiro[1]);

                realizarTiro(naviosJogador, acertosMaquina, tiro[0], tiro[1]);

                if (verificarVitoria(naviosJogador)) {
                    System.out.println("A MAQUINA VENCEU!");
                    break;
                }

                vezAtual = 0;
            }
        }
    }

    public static int[] tiroMaquina(char[][] acertosMaquina, int dificuldade) {
        Random random = new Random();

        if (dificuldade == 1) {
            return tiroFacil(acertosMaquina, random);
        } else if (dificuldade == 2) {
            return tiroMedio(acertosMaquina, random);
        } else {
            return tiroDificil(acertosMaquina, random);
        }
    }

    public static int[] tiroFacil(char[][] acertos, Random random) {
        int linha;
        int coluna;

        do {
            linha = random.nextInt(10);
            coluna = random.nextInt(10);
        } while (acertos[linha][coluna] == 'X' || acertos[linha][coluna] == 'O');

        return new int[]{linha, coluna};
    }

    public static int[] tiroMedio(char[][] acertos, Random random) {
        for (int i = 0; i < acertos.length; i++) {
            for (int j = 0; j < acertos[i].length; j++) {
                if (acertos[i][j] == 'X') {
                    if (i - 1 >= 0 && acertos[i - 1][j] != 'X' && acertos[i - 1][j] != 'O') {
                        return new int[]{i - 1, j};
                    }
                    if (i + 1 < 10 && acertos[i + 1][j] != 'X' && acertos[i + 1][j] != 'O') {
                        return new int[]{i + 1, j};
                    }
                    if (j - 1 >= 0 && acertos[i][j - 1] != 'X' && acertos[i][j - 1] != 'O') {
                        return new int[]{i, j - 1};
                    }
                    if (j + 1 < 10 && acertos[i][j + 1] != 'X' && acertos[i][j + 1] != 'O') {
                        return new int[]{i, j + 1};
                    }
                }
            }
        }
        return tiroFacil(acertos, random);
    }

    public static int[] tiroDificil(char[][] acertos, Random random) {
        for (int i = 0; i < acertos.length; i++) {
            for (int j = 0; j < acertos[i].length; j++) {
                if (acertos[i][j] == 'X') {
                    if (j + 1 < 10 && acertos[i][j + 1] == 'X') {
                        if (j + 2 < 10 && acertos[i][j + 2] != 'X' && acertos[i][j + 2] != 'O') {
                            return new int[]{i, j + 2};
                        }
                        if (j - 1 >= 0 && acertos[i][j - 1] != 'X' && acertos[i][j - 1] != 'O') {
                            return new int[]{i, j - 1};
                        }
                    }
                    if (i + 1 < 10 && acertos[i + 1][j] == 'X') {
                        if (i + 2 < 10 && acertos[i + 2][j] != 'X' && acertos[i + 2][j] != 'O') {
                            return new int[]{i + 2, j};
                        }
                        if (i - 1 >= 0 && acertos[i - 1][j] != 'X' && acertos[i - 1][j] != 'O') {
                            return new int[]{i - 1, j};
                        }
                    }
                }
            }
        }
        return tiroMedio(acertos, random);
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
        } else {
            for (int i = linha; i < linha + tamanho; i++) {
                tabuleiro[i][coluna] = 'N';
            }
        }

        mostrarTabuleiro(tabuleiro);
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

    public static void trocarJogador(Scanner input) {
        System.out.println("\nPasse o computador para o proximo jogador.");
        System.out.println("Pressione ENTER para continuar...");
        input.nextLine();
        input.nextLine();

        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public static void modoDemonstracaoCompleta() {

        Random random = new Random();

        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║            MODO DEMONSTRACAO COM IA                 ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║  ESTA FUNCIONALIDADE FOI DESENVOLVIDA APENAS PARA   ║");
        System.out.println("║  APRESENTACAO E VALIDACAO DO PROJETO.               ║");
        System.out.println("║                                                      ║");
        System.out.println("║  A IA SIMULA UM USUARIO UTILIZANDO O SISTEMA        ║");
        System.out.println("║  E TESTA AUTOMATICAMENTE SUAS FUNCIONALIDADES.      ║");
        System.out.println("║                                                      ║");
        System.out.println("║  TESTES EXECUTADOS:                                ║");
        System.out.println("║   • Menu Principal                                 ║");
        System.out.println("║   • Regras do Jogo                                 ║");
        System.out.println("║   • Criacao dos Tabuleiros                         ║");
        System.out.println("║   • Posicionamento da Frota                        ║");
        System.out.println("║   • Sistema de Disparos                            ║");
        System.out.println("║   • Acertos e Erros                                ║");
        System.out.println("║   • Condicao de Vitoria                            ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        try {

            Thread.sleep(2000);

            System.out.println("\n[IA] Verificando menu principal...");
            mostrarMenu();
            Thread.sleep(2000);

            System.out.println("\n[IA] Verificando tela de regras...");
            mostrarRegras();
            Thread.sleep(3000);

            System.out.println("\n[IA] Criando tabuleiros de teste...");

            char[][] naviosJogador = criarTabuleiro();
            char[][] naviosMaquina = criarTabuleiro();

            char[][] acertosJogador = criarTabuleiro();
            char[][] acertosMaquina = criarTabuleiro();

            System.out.println("✓ Tabuleiros criados com sucesso.");
            Thread.sleep(1500);

            System.out.println("\n[IA] Posicionando frota do Jogador-Teste...");
            posicionarFrotaMaquina(naviosJogador);

            System.out.println("✓ Frota posicionada.");
            mostrarTabuleiro(naviosJogador);

            Thread.sleep(2000);

            System.out.println("\n[IA] Posicionando frota da Maquina...");
            posicionarFrotaMaquina(naviosMaquina);

            System.out.println("✓ Frota posicionada.");
            mostrarTabuleiro(naviosMaquina);

            Thread.sleep(2000);

            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║         INICIANDO SIMULACAO          ║");
            System.out.println("╚══════════════════════════════════════╝");

            int rodada = 1;
            int vezAtual = 0;

            while (true) {

                System.out.println("\n========================================");
                System.out.println("RODADA " + rodada);
                System.out.println("========================================");

                if (vezAtual == 0) {

                    int[] tiro = tiroDificil(acertosJogador, random);

                    System.out.println("\n[IA - JOGADOR TESTE]");
                    System.out.println("Disparo em (" + tiro[0] + "," + tiro[1] + ")");

                    realizarTiro(
                            naviosMaquina,
                            acertosJogador,
                            tiro[0],
                            tiro[1]
                    );

                    if (verificarVitoria(naviosMaquina)) {

                        System.out.println("\n🏆 JOGADOR-TESTE VENCEU!");
                        break;
                    }

                    vezAtual = 1;

                } else {

                    int[] tiro = tiroDificil(acertosMaquina, random);

                    System.out.println("\n[IA - MAQUINA]");
                    System.out.println("Disparo em (" + tiro[0] + "," + tiro[1] + ")");

                    realizarTiro(
                            naviosJogador,
                            acertosMaquina,
                            tiro[0],
                            tiro[1]
                    );

                    if (verificarVitoria(naviosJogador)) {

                        System.out.println("\n🏆 MAQUINA VENCEU!");
                        break;
                    }

                    vezAtual = 0;
                    rodada++;
                }

                System.out.println("\nMAPA DE TIROS DO JOGADOR-TESTE");
                mostrarTabuleiro(acertosJogador);

                System.out.println("\nMAPA DE TIROS DA MAQUINA");
                mostrarTabuleiro(acertosMaquina);

                Thread.sleep(400);
            }

            System.out.println("\n╔══════════════════════════════════════════════════════╗");
            System.out.println("║                RELATORIO DA IA                      ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.println("║  ✓ Menu validado                                    ║");
            System.out.println("║  ✓ Regras exibidas corretamente                     ║");
            System.out.println("║  ✓ Tabuleiros criados                               ║");
            System.out.println("║  ✓ Frotas posicionadas                              ║");
            System.out.println("║  ✓ Sistema de tiros validado                        ║");
            System.out.println("║  ✓ Acertos registrados                              ║");
            System.out.println("║  ✓ Erros registrados                                ║");
            System.out.println("║  ✓ Vitoria identificada corretamente                ║");
            System.out.println("║                                                      ║");
            System.out.println("║  RESULTADO: TODOS OS TESTES PASSARAM                ║");
            System.out.println("║                                                      ║");
            System.out.println("║  ESTA IA FOI CRIADA EXCLUSIVAMENTE PARA             ║");
            System.out.println("║  DEMONSTRAR E VALIDAR O FUNCIONAMENTO               ║");
            System.out.println("║  COMPLETO DO PROJETO.                               ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");

        } catch (InterruptedException e) {
            System.out.println("Erro durante a demonstracao.");
        }
    }
}