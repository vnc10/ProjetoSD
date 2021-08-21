import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import kotlin.random.Random


class Game(val players: MutableList<Player>, val subRounds: Int, val totalPoints: Int, val nameArquive: String) {

    var actualMaster: Int = 0
    var character: String? = null
    var tip: String? = null
    var actualRound: Int = 1
    val mumRounds: Int = this.players.size
    var actualSubRound: Int = 1
    var questions: MutableList<String> = mutableListOf()


    fun playGame() {
        println("Iniciando a partida...")
        sendMessageToAll("OS SEGUINTES JOGADORES IRAM PARTICIPAR DA PARTIDA:")
        var count: Int = 0
        while (count < this.players.size) {
            sendMessageToAll(this.players[count].getNickName())
            count++
        }
        sendMessageToAll("INICIANDO A PARTIDA")
        while (this.actualRound <= this.mumRounds) {
            println("CONFIGURANDO A RODADA " + this.actualRound)
            sendMessageToAll("CONFIGURANDO A RODADA " + this.actualRound)
            configureRound()
            this.players[this.actualMaster].setIsMaster(false)
            showHighScore()
            this.actualRound++
            for (j in this.players) {
                j.setAcertou(false)
            }

        }
        winner()
        println("PARTIDA ENCERRADA.")
        sendMessageToAll("PARTIDA ENCERRADA")
        saveArquive()
    }

    fun configureRound() {
        println("SORTEANDO O MESTRE...")
        sendMessageToAll("SORTEANDO O MESTRE...")
        this.actualMaster = Random.nextInt(0, this.players.size)
        while (this.players[this.actualMaster].getWasMaster()) {
            this.actualMaster = Random.nextInt(0, this.players.size)
        }
        players[this.actualMaster].setWasMaster(true)
        this.players[this.actualMaster].setIsMaster(true)
        println("O MESTRE DESSA RODADA SERÁ O(A): " + this.players[this.actualMaster].getNickName())
        sendMessageToAll("O MESTRE DESSA RODADA SERÁ O(A): " + this.players[this.actualMaster].getNickName())
        println("O MESTRE ESTÁ ESCOLHENDO QUEM ELE É...")
        sendMessageToAll("O MESTRE ESTÁ ESCOLHENDO QUEM ELE É...")
        this.character = this.players[this.actualMaster].getCharacter()
        println("O MESTRE ESTÁ ESCREVENDO A DICA...")
        sendMessageToAll("O MESTRE ESTÁ ESCREVENDO A DICA...")
        this.tip = this.players[this.actualMaster].getTip()
        sendMessageToAll("A DICA É: " + this.tip)
        match()
    }

    fun match() {
        var aux: Pair<String, String>
        var someHit: Boolean = false
        while ((this.actualSubRound <= this.subRounds) && !someHit) {
            sendMessageToAll("COMEÇANDO " + this.actualSubRound + " RODADA DE PERGUNTAS/TENTATIVAS")
            println("COMEÇANDO " + this.actualSubRound + " RODADA DE PERGUNTAS/TENTATIVAS")
            this.players[this.actualMaster].showMessage("OS JOGADORES ESTÃO ESCREVENDO AS PERGUNTAS...")
            println("OS JOGADORES ESTÃO ESCREVENDO AS PERGUNTAS/TENTATIVAS")
            for (i in this.players) {
                if ((!i.getIsMaster()) && (!i.getAcertou())) {
                    aux = i.makeChoice()
                    if (aux.first.equals("ATTEMPT")) {
                        if (aux.second.equals(this.character)) {
                            i.setScore(this.totalPoints)
                            i.showMessage("VOCÊ ACERTOU O PERSONAGEM!!!")
                            i.showMessage("AGUARDE A RODADA TERMINAR...")
                            i.setAcertou(true)
                            someHit = true
                        }
                    } else {
                        this.questions.add(aux.second)
                    }
                }
            }
            masterResponse()
            this.actualSubRound++
        }
        this.actualSubRound = 1
    }

    fun masterResponse() {
        sendMessageToAll("O MESTRE ESTÁ RESPONDENDO AS PERGUNTAS...")
        println("O MESTRE ESTÁ RESPONDENDO AS PERGUNTAS...")
        this.players[this.actualMaster].showMessage("RESPONDA AS SEGUINTES PERGUNTAS COM 's' PARA SIM E 'n' PARA NÃO:")
        var count: Int = 1
        for (i in this.questions) {
            val response = this.players[this.actualMaster].getResponse(i)
            sendMessageToAll("PERGUNTA " + count + ":")
            sendMessageToAll(i)
            sendMessageToAll(response)
            count++
        }
        this.questions.clear()
    }

    fun showHighScore() {
        println("ATUALMENTE O PLACAR DA PARTIDA É:")
        sendMessageToAll("ATUALMENTE O PLACAR DA PARTIDA É:")
        for (i in this.players) {
            println(i.getNickName() + " -> " + i.getScore() + " POINTS")
            sendMessageToAll(i.getNickName() + " -> " + i.getScore() + " POINTS")
        }
    }

    fun saveArquive() {
        val file = File(this.nameArquive)
        val fos = FileOutputStream(file)
        val bw = BufferedWriter(OutputStreamWriter(fos))
        bw.write("PLACAR DA PARTIDA:")
        bw.newLine()
        for (i in this.players) {
            bw.write(i.getNickName() + " --> " + i.getScore() + " Points")
            bw.newLine()
        }
        bw.close()

    }

    fun sendMessageToAll(message: String) {
        for (i in this.players) {
            if (!i.getIsMaster()) {
                i.showMessage(message)
            }
        }
    }

    fun winner() {
        var winner: Player = this.players[0]
        for (i in this.players) {
            if (i.getScore() > winner.getScore()) {
                winner = i
            }
        }
        sendMessageToAll("O GANHADOR É: " + winner.getNickName())
        winner.messageWin()
    }


}