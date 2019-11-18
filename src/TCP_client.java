import sun.nio.cs.ext.GBK;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;

public class TCP_client {
    public static void main(String[] args) throws IOException {
        Socket server = new Socket("127.0.0.1", 30000);
        //设置超时时间
        server.setSoTimeout(10000);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in,"GBK"));
        //获取键盘输入
        PrintStream out = new PrintStream(server.getOutputStream());
        //获取Socket的输出流，用来发送数据到服务端
        BufferedReader buf = new BufferedReader(new InputStreamReader(server.getInputStream(),"UTF-8"));
        //获取Socket的输入流，用来接收从服务端发送过来的数据
        boolean flag = true;
        while (flag) {
            System.out.println("输入信息：");
            String str = input.readLine();
            out.println(str);
            //发送数据到服务端
            if ("close".equals(str)) {
                flag = false;
            } else {
                try {
                    //从服务器端接收数据有个时间限制（系统自设，也可以自己设置），超过了这个时间，便会抛出该异常
                    String echo = buf.readLine();
                    System.out.println("接收到:");
                    String gbk = new String(str.getBytes("GBK"));
                    System.out.println(gbk);
                } catch (SocketTimeoutException e) {
                    System.out.println("Time out, No response");
                }
            }
        }
        input.close();
        if (server != null) {
            //如果构造函数建立起了连接，则关闭套接字，如果没有建立起连接，自然不用关闭
            server.close(); //只关闭socket，其关联的输入输出流也会被关闭
        }
    }
}

