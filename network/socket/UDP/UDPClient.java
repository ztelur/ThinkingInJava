package com.company.network.socket.UDP;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.*;

/**
 * Created by randy on 15-3-30.
 */
public class UDPClient {
    private static final int TIME_OUT=5000;
    private static int client_port=9000;
    private static int server_port=3000;
    private static final int SEND_NUM=5;
    private int send_num=0;
    private static  String send_message="hello I am UDPClient";
    public static void main(String[] args) {
        try {
            byte[] buf=new byte[1024];
            DatagramSocket socket = new DatagramSocket(client_port); //监听9000的端口
            InetAddress inetAddress=InetAddress.getLocalHost();  //获得本地地址
            //这是用来发送数据的。
            DatagramPacket send_packet=new DatagramPacket(send_message.getBytes(),
                                                                                send_message.length(),inetAddress,server_port);
            //这是用来接收数据的
            DatagramPacket receive_packet=new DatagramPacket(buf,1024);//只需要使用buf缓冲和设置最大的长度

            /**
             * 准备工作完成，开始发送和接收数据
             */
            socket.setSoTimeout(TIME_OUT);
            int tries=0;
            boolean received=false;
            while (!received&&tries<5) {   //没有收到，并且尝试在5次之下
                try {
                    socket.send(send_packet);//可见在UDP中，socket只是用于网络通道的建立，目标地址，端口等都在packet中

                    socket.receive(receive_packet);
                    if (!receive_packet.getAddress().equals(inetAddress)) {

                    }
                    received=true;
                }catch (InterruptedIOException e) {  //先捕获子类的啊。
                    tries++;
                    System.out.println("Time out "+tries);
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (received) {
                        //输出数据
                        //receive_packet的lenght要重新设置。
                } else {
                        //日志记录
                }
                socket.close(); //这是必须的。
            }



        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
