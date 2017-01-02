package addis.execute;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by huangfeifeng on 1/18/16.
 */
public class MainClass {

    public void testSocket() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6789);
        Socket socket = serverSocket.accept();
    }

    public static void main(String... args) throws IOException {
//        long interval = 86400;
//        long current = System.currentTimeMillis();
//        long span = 24 * 60 * 60 * 1000;
//        long offset = 8 * 60 * 60 * 1000;
//        long from = ((current - interval * 1000) / span) * span - offset;
//        long to = from + span - 1;
//        System.out.println(new Date(from));
//        System.out.println(new Date(to));
        while(true){
            System.out.println("i love u");
        }
    }
}
