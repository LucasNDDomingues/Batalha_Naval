⚓ Batalha Naval

Jogo de Batalha Naval (Battleship) desenvolvido em Java, executado via console/terminal. O projeto permite partidas entre dois jogadores (PvP) ou contra a máquina (PvM), com diferentes níveis de dificuldade de IA, além de um modo de demonstração automática.

🎮 Funcionalidades
Jogador vs Jogador (PvP) — dois jogadores se revezam no mesmo computador, posicionando suas frotas e disparando tiros um contra o outro.
Jogador vs Máquina (PvM) — enfrente a IA em três níveis de dificuldade:
Fácil: tiros totalmente aleatórios.
Médio: após um acerto, a IA tenta as posições adjacentes (cima, baixo, esquerda, direita).
Difícil: após dois acertos em sequência, a IA identifica a direção do navio e continua atirando na linha/coluna correta até afundá-lo.
Regras do jogo — tela explicativa com a legenda do tabuleiro e a composição da frota.
Modo Demonstração com IA — simulação automática de uma partida completa (IA vs IA), usada para validar todas as funcionalidades do projeto sem necessidade de interação manual.
🚢 Frota
Navio	Tamanho	Quantidade
Porta-Aviões	4 casas	1
Cruzador	3 casas	1
Destroyer	2 casas	1
Submarino	1 casa	2
🗺️ Legenda do Tabuleiro
Símbolo	Significado
~	Água
N	Navio
X	Acerto
O	Erro

O tabuleiro tem dimensões 10x10 (linhas e colunas de 0 a 9).

🕹️ Como Jogar
Ao iniciar o programa, escolha uma opção no menu principal.
No modo PvP ou PvM, cada jogador posiciona sua frota informando linha, coluna e orientação (horizontal ou vertical) para cada navio.
Nos turnos de ataque, informe a linha e a coluna onde deseja atirar.
Vence quem afundar toda a frota do adversário primeiro.

No modo PvP, o jogo solicita que os jogadores "passem o computador" entre os turnos, limpando a tela para preservar o sigilo do posicionamento da frota.

🛠️ Tecnologias
Java (JDK 8+)
Uso das classes Scanner (entrada de dados) e Random (posicionamento e decisões da IA)
▶️ Como Executar
Pré-requisitos
JDK instalado (versão 8 ou superior)
Compilar e rodar
bash
javac BatalhaNaval.java
java BatalhaNaval
📁 Estrutura do Projeto
.
├── BatalhaNaval.java   # Código-fonte principal do jogo
└── README.md           # Este arquivo
📋 Menu Principal
╔════════════════════════════════╗
       ⚓ BATALHA NAVAL ⚓        
╠════════════════════════════════╣
║  [ 1 ]  JOGADOR VS JOGADOR     ║
║  [ 2 ]  JOGADOR VS MAQUINA     ║
║  [ 3 ]  REGRAS DO JOGO         ║
║  [ 4 ]  DEMONSTRACAO COM IA    ║
║  [ 0 ]  SAIR                   ║
╚════════════════════════════════╝
🤖 Sobre o Modo Demonstração

O modo de demonstração (opção 4 do menu) foi criado para fins de apresentação e validação do projeto. Nele, uma IA simula uma partida completa (posicionamento de frotas e disparos usando a lógica de dificuldade difícil) de forma automática, exibindo um relatório ao final confirmando que todas as funcionalidades principais do sistema estão operando corretamente.

📄 Licença

Este projeto está disponível livremente para fins de estudo e aprendizado. Sinta-se à vontade para utilizá-lo, modificá-lo e contribuir.

Desenvolvido em Java como projeto de estudo sobre lógica de programação, matrizes e estruturas de repetição. 🚢
