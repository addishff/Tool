package addis.execute;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by huangfeifeng on 1/18/16.
 */
public class MainClass {

    public void testSocket() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6789);
        Socket socket = serverSocket.accept();
    }

    public static void main(String... args) {
        String requestUrl = "abc/dfg/aaa/ert";
        String regex = "abc/.*";
        System.out.println(requestUrl.matches(regex));
    }
}
