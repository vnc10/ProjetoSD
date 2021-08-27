import java.lang.Exception
import java.net.Socket
import java.util.*
import java.awt.RenderingHints
import java.awt.Graphics2D
import java.awt.Font
import java.awt.image.BufferedImage



fun main(){

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
    graphics.drawString("WHO I AM", 0, 27)

    for (y in 0 until height) {
        val sb = StringBuilder()
        for (x in 0 until width) {
            sb.append(if (image.getRGB(x, y) == -16777216) " " else "|")
        }
        if (sb.toString().trim { it <= ' ' }.isEmpty()) {
            continue
        }
        println(sb)
    }

    val message: Scanner = Scanner(System.`in`)
    println("Bem-Vindo a o jogo WHO I AM")
    println("Digite o ip do servidor:")
    val ip: String = message.nextLine()
    println("Digite a porta:")
    val port:Int = message.nextInt()
    try{
        val socket: Socket = Socket(ip,port)
        val client = Client(socket)
        client.start()
    }catch (e: Exception){
        println(e)
    }
}