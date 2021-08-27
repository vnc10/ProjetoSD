package com.whoami.player
import com.whoami.entity.Score
import java.awt.Font
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.PrintStream
import java.net.*
import java.util.*

class Player (val socket: Socket) : Thread() {
    private var nickName: String = "AnomPlayer"
    private var score: Int = 0
    private var wasMaster: Boolean = false
    private var isMaster: Boolean = false
    private var acertou: Boolean = false
    private val messageIn = Scanner(socket.getInputStream())
    private val messageOut = PrintStream(socket.getOutputStream())
    val askNickName: String = "DIGITE SEU NICKNAME:"
    val askNickNameAgain: String = "NOME JÁ EM USO, POR FAVOR DIGITE OUTRO NOME:"
    val askChoice: String = "SUA VEZ --> Digite 'tentativa' para fazer uma tentativa ou Digite 'pergunta' para fazer uma pergunta"
    val askChoiceAgain: String = "PALAVRA INVALIDA, POR FAVOR ESCOLHA ENTRE 'pergunta' ou 'tentativa"
    val askQuestion: String = "DIGITE SUA PERGUNTA"
    val invalidAnwser: String = "RESPOSTA INVALIDA, ESCOLHA 's' OU 'n'"
    val askAttemp: String = "DIGITE SUA TENTATIVA"
    val askCharacter: String = "QUEM É VOCÊ?"
    val askTip: String = "QUAL É A DICA?"
    var aux: String? = null


    fun getNickName(): String {
        return this.nickName
    }

    fun setNickName(state: Boolean){
        this.messageOut.println("QUESTION")
        if(state) {
            this.messageOut.println(this.askNickName)
        }
        else{
            this.messageOut.println(this.askNickNameAgain)
        }
        this.nickName = messageIn.nextLine().uppercase(Locale.getDefault())
    }

    fun getScore(): Int{
        return this.score
    }

    fun setScore(points: Int){
        this.score += points
    }

    fun getWasMaster(): Boolean{
        return this.wasMaster
    }

    fun setWasMaster(state: Boolean){
        this.wasMaster = state
    }

    fun getIsMaster(): Boolean{
        return this.isMaster
    }

    fun setIsMaster(state: Boolean){
        this.messageOut.println("MESSAGE")
        this.messageOut.println("VOCÊ SERÁ O MESTRE DESSA RODADA")
        this.isMaster = state
    }

    fun getCharacter(): String{
        this.messageOut.println("QUESTION")
        this.messageOut.println(this.askCharacter)
        return messageIn.nextLine().uppercase(Locale.getDefault())
    }

    fun getTip(): String{
        this.messageOut.println("QUESTION")
        this.messageOut.println(this.askTip)
        return messageIn.nextLine().uppercase(Locale.getDefault())
    }

    fun getResponse(message: String): String{
        this.messageOut.println("QUESTION")
        this.messageOut.println(message)
        var msg = messageIn.nextLine().uppercase(Locale.getDefault())
        while((!msg.equals("S")) && (!msg.equals("N"))){
            this.messageOut.println("MESSAGE")
            this.messageOut.println(this.invalidAnwser)
            this.messageOut.println("QUESTION")
            this.messageOut.println(message)
            msg = messageIn.nextLine().uppercase(Locale.getDefault())
        }
        if(msg.equals("S")){
            return "SIM"
        }
        return "NÃO"
    }

    fun showMessage(message: String){
        this.messageOut.println("MESSAGE")
        this.messageOut.println(message)
    }


    fun getAcertou(): Boolean{
        return this.acertou
    }

    fun setAcertou(state: Boolean){
        this.acertou = state
    }

    fun makeChoice(): Pair<String, String>{
        var choice: Pair<String,String>
        this.messageOut.println("QUESTION")
        this.messageOut.println(this.askChoice)
        var msg = messageIn.nextLine().uppercase(Locale.getDefault())
        while((!msg.equals("PERGUNTA")) && (!msg.equals("TENTATIVA"))){
            this.messageOut.println("MESSAGE")
            this.messageOut.println(this.askChoiceAgain)
            this.messageOut.println("QUESTION")
            this.messageOut.println(this.askChoice)
            msg = messageIn.nextLine().uppercase(Locale.getDefault())
        }
        if(msg.equals("PERGUNTA")){
            this.messageOut.println("QUESTION")
            this.messageOut.println(askQuestion)
            val aux = messageIn.nextLine()
            choice = Pair("PERGUNTA", aux)
        }
        else{
            this.messageOut.println("QUESTION")
            this.messageOut.println(askAttemp)
            val aux = messageIn.nextLine()
            choice = Pair("ATTEMPT", aux)
        }
        return choice
    }

    fun messageWin() {
        val width = 100
        val height = 30

        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val g = image.graphics
        g.font = Font("SansSerif", Font.BOLD, 14)

        val graphics = g as Graphics2D
        graphics.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        )
        graphics.drawString("WINNER", 0, 27)

        for (y in 0 until height) {
            val sb = StringBuilder()
            for (x in 0 until width) {
                sb.append(if (image.getRGB(x, y) == -16777216) " " else "|")
            }
            if (sb.toString().trim { it <= ' ' }.isEmpty()) {
                continue
            }

            this.messageOut.println("MESSAGE")
            this.messageOut.println(sb)
        }
    }
}

