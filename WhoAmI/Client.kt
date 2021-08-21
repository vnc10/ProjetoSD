import java.io.PrintStream
import java.net.*
import java.util.*

class Client(val socket: Socket) {
    val messageIn = Scanner(socket.getInputStream())
    val messageOut = PrintStream(socket.getOutputStream())
    val message = Scanner(System.`in`)
    var state: Boolean = false


    fun start(){

        println("Aguardando mensagem do servidor...")
        state = messageIn.hasNextLine()
        while(state){
            val aux = messageIn.nextLine()
            println(messageIn.nextLine())
            if(!aux.equals("MESSAGE")){
                cleanBuffer()
                messageOut.println(message.nextLine().uppercase(Locale.getDefault()))
            }
            state = messageIn.hasNextLine()
        }
    }


    fun cleanBuffer(){
        while(System.`in`.available() > 0){
            System.`in`.read()
        }
    }

}