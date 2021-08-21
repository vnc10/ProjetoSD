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

