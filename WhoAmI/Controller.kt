import java.lang.Exception
import java.net.ServerSocket
import java.util.*

class Controller {
    var namePlayers: MutableList<String> = mutableListOf()
    var players: MutableList<Player> = mutableListOf()
    var subRound: Int = 1
    var totalPoints: Int = 0
    val message = Scanner(System.`in`)
    fun initializeServer() {
        println("BEM-VINDO A O WHO I AM")
        println("Digite o numero da porta do servidor:")
        val port: Int = message.nextInt()
        println("Iniciando servidor...")
        try {
            val server: ServerSocket = ServerSocket(port)
            println("Servidor inicializado na porta:" + port)
            configureGame(server)
        } catch (e: Exception) {
            println(e)
        }
    }

    fun configureGame(server: ServerSocket) {
        val nameArquive: String = "gameWhoIAm-" + java.time.LocalDateTime.now().dayOfMonth + "-" +
                java.time.LocalDateTime.now().month + "-" + java.time.LocalDateTime.now().hour + ":" +
                java.time.LocalDateTime.now().minute + "h.txt"
        println("CONFIGURANDO PARTIDA...")
        println("DIGITE O NUMERO DE PARTICIPANTES PARA ESSA PARTIDA:")
        var numPlayer: Int = message.nextInt()
        println("DIGITE O NUMERO DE RODADAS DE PERGUNTAS/TENTATIVAS:")
        this.subRound = message.nextInt()
        println("DIGITE O VALOR DE CADA ACERTO:")
        this.totalPoints = message.nextInt()
        println("PARTIDA CONFIGURADA.")
        val rules = listOf<String>(
            "REGRAS DO JOGO",
            "1º CADA JOGADOR SERÁ O MESTRE POR UMA RODADA",
            "2º O MESTRE SÓ PODE RESPONDER AS PERGUNTAS COM SIM OU NÃO",
            "3º O MESTRE SERÁ TROCADO QUANDO ALGUM JOGADOR FIZER UMA TENTATIVA E ACERTAR OU QUANDO ATINGER O NUMERO MAXIMO DE RODADAS DE TENTATIVAS/PERGUNTAS",
            "4º O JOGO TERÁ NO MAXIMO" + this.subRound + " RODADAS DE TENTATIVAS OU PERGUNTAS",
            "5º CADA ACERTO TERÁ O VALOR DE " + this.totalPoints + " PONTOS",
            "6º A PARTIDA TERÁ " + numPlayer + "JOGADORES",
            "7º O RESULTADO SERÁ NO SALVO NO ARQUIVO " + nameArquive)
        println("AGUARDANDO " + numPlayer + " JOGADORES(ES) SE CONECTAR(EM)")
        while (numPlayer != 0) {
            val client = server.accept()
            val player = Player(client)
            player.start()
            player.setNickName(true)
            while (namePlayers.contains(player.getNickName())) {
                player.setNickName(false)
            }
            namePlayers.add(player.getNickName())
            for(j in rules){
                player.showMessage(j)
            }
            player.showMessage("AGUARDE A PARTIDA COMEÇAR")
            players.add(player)
            println("O jogador " + player.getNickName() + " Entrou na Partida")
            numPlayer--
            for (i in this.players) {
                if (!i.getNickName().equals(player.getNickName())) {
                    i.showMessage("O jogador " + player.getNickName() + " Entrou na Partida")
                }
            }
        }
        playGame(nameArquive)
    }

    fun playGame(nameArquive: String) {
        val jogo = Game(this.players, this.subRound, this.totalPoints, nameArquive)
        jogo.playGame()
    }
}