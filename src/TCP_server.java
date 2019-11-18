import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_server {
    public static void main(String[] args) throws IOException {
        //创建一个ServerSocket, 用于监听客户端Socket的连接请求
        ServerSocket server = new ServerSocket(30000);
        //客户端请求
        Socket client=null;
        //用循环不断接收来自客户端的请求
        boolean flag=true;
        while(flag) {
            //每当接收到一个客户端的请求，服务器也对应产生一个Socket
            client = server.accept();

            //将Socket对应的输出流包装成printStream
            PrintStream out = new PrintStream(client.getOutputStream());
            //获取Socket的输出流，用来向客户端发送数据
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream(),"GBK"));

            boolean sw = true;
            while (sw) {
                String str = buf.readLine();
                //接收从客户端发送过来的数据
                System.out.println("received:"+str);
                if (str == null || "".equals(str)) {
                    sw = false;
                } else {
                    if ("close".equals(str)) {
                        flag = false;
                        sw=false;
                    } else {
                        out.println(str);
                    }
                }
            }
            out.close();
            client.close();
        }
        server.close();
    }
}
