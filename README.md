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

 <h3> <b> Interfaces: </b> </h3>

***Controller:***

 *A interface Controller, é responsável por configurar a partida, inicializar o servidor e invocar o Game.

***Parâmetros:***

***namePlayers (obrigatório):***

Lista com o nome dos jogadores que estarão na partida

***players (obrigatório):***

Lista dos jogadores que estão na partida

***subRound (obrigatório):***

Número de rodadas de perguntas/tentativas

***totalPoints (obrigatório):***

Pontuação que será atribuída ao jogador que acertar o personagem

***message (obrigatório):***

Input das mensagens enviadas para configuração do servidor

***Game:***

A interface Game, é usada para começar o jogo, configurar a rodada, verificar se o personagem está correto, apresentar a pontuação, salvar os pontos no banco de dados e mostrar o vencedor.

***Parâmetros:***

***players (obrigatório):***

Lista de jogadores que vão participar da partida.

***subRounds (obrigatório):***

Número de rodadas de perguntas/tentativas

***totalPoints (obrigatório):***

Pontuação que será atribuída ao jogador que acertar o personagem

***nameArquive (obrigatório):***

Nome do arquivo que será salvo a pontuação da partida.

***Player:***

Interface responsável por controlar o estado de cada jogador dentro do jogo.

***Parâmetros:***

***socket (obrigatório):***
	
Responsável por armazenar o socket de comunicação de cada jogador.

***nickName (obrigatório):***

Responsável por armazenar o nome de cada jogador.

***score (obrigatório):***
	
Responsável por armazenar a pontuação do jogador.

***wasMaster (obrigatório):***

Responsável por armazenar o estado atual do cliente, podendo ser jogador ou mestre.

***acertou (obrigatório):***

Responsável por armazenar o estado atual do cliente da tentativa do jogador, podendo ser True quando a tentativa for acertada, ou False quando a tentativa for errada. 

***mensageIn (obrigatório):***

Responsável por receber as mensagens digitadas pelo jogador.

***messageOut (obrigatório):***

Responsável por mostrar ao jogador as mensagens enviadas pelo servidor do jogo.


