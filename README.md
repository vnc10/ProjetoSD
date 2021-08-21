# ProjetoSD
Esse repositório é referente ao projeto final da matéria de Sistemas Distribuidos, no qual, será realizado a implementação do 'WhoAmI'.
O jogo é realizado da seguinte forma: o jogador escolhe uma sala para entrar, dentro da sala existem vários jogadores, no qual um vai ser o mestre, que será escolhido aleatoriamente, o mestre é quem escolhe seu personagem, e os jogadores tentam acertar. Os jogadores podem fazer uma tentativa de escolha do personagem ou fazer uma perguntar para o mestre, no qual responde se a dica com 'sim' ou 'não', com a finalidade para ajudar a descobrir quem é o personagem, o jogador que acertar primeiro vence.

Para se executar o projeto é necessario ter instalado o compilador kotlin, com o compilador já instalado, deve-se entrar na pasta 'WhoIAm' e executar os comandos:

**kotlinc MainServer.kt Controller.kt Game.kt Player.kt -include-runtime -d server.jar**

**kotlinc MainClient.kt Client.kt -include-runtime -d client.jar**

Após executado os comandos, para executar o servidor use o comando:

sudo java -jar server.jar 

E para o cliente:

sudo java -jar client.jar 

![alt text](https://github.com/vnc10/ProjetoSD/blob/main/arqSQ.png)

 <h3> <b> Interface do Servidor: </b> </h3>

<b> ConfigureGame(Socket) </b>

Configuração do servidor, onde ele recebe por parâmetro o endereço que será iniciado o servidor via socket.
Interface do Servidor:

**PlayGame(String)**

Inicia o jogo e recebe o nome do arquivo no qual será salvo a pontuação do jogo


**Game(MutableList<>, int, int, String)**

Controla a classe que contém os métodos utilizados pelo servidor na realização do jogo.

**playGame()**

Inicia o jogo e controla a partida.

**configureRound()**

Sorteio do mestre, mestre escolhe seu personagem e escreve a dica.

**match()**

Realiza o Controle de cada rodada.

**masterResponde()**

O mestre responde às perguntas dos jogadores.

**showHighScore()**

Apresenta a pontuação da partida.

**saveArquive()**

Salve o arquivo contendo a pontuação da partida.

**sendMessageToAll(String)**

Função utilizada para envio de mensagem para todos os jogadores.

**winner()**

Define o vencedor e mostra na tela.


<h3> <b> Interface do Cliente: </h3> </b>

**Client(Socket)**

Controla a classe que contém os métodos utilizados pelo cliente.

**start()**

Controla a interação do cliente com o jogo.

**cleanBuffer()**

Limpa o buffer de entrada do cliente.

**PlayGame(String)**

Inicia o jogo e recebe o nome do arquivo no qual será salvo a pontuação do jogo

