import java.io.IOException;
import java.net.*;

public class UDP_server {
    public static void main(String[] args) throws IOException {
        //用数组接收数据报包
        byte[] bs = new byte[500];
        DatagramPacket p = new DatagramPacket(bs, bs.length);
        //创建数据报套接字
        DatagramSocket socket_B = new DatagramSocket(10010);
        //接收数据报包
        socket_B.receive(p);
        System.out.println(new String(bs, 0, p.getLength(),"GBK"));
        //关闭套接字
        socket_B.close();
    }
}
