import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_client {
    public static void main(String[] args) throws IOException {
        byte[] bs = "我是A,给B发信息".getBytes();//要发的信息内容
        //UDPA与UDPB的ip均为本机ip，故设置不同的端口号
        InetAddress desIp = InetAddress.getLocalHost();
        //数据报包，UDPB的端口为10010
        DatagramPacket p = new DatagramPacket(bs, bs.length, desIp, 10010);
        //创建数据报套接字，UDPA的端口设置为10086
        DatagramSocket socket_A = new DatagramSocket(10086);
        //UDPA给UDPB发送数据报
        socket_A.send(p);
        //关闭socket_A套接字
        socket_A.close();
    }
}
