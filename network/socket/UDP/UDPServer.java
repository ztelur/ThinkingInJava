package com.company.network.socket.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by randy on 15-3-30.
 */
public class UDPServer {
    public static void main(String[] args) {
        String send_message = "hello client it's server";
        byte[] buf = new byte[1024];
        try {
            DatagramSocket socket = new DatagramSocket(3000);
            DatagramPacket receive_packet = new DatagramPacket(buf, 1024);
            System.out.println("server is wait for client to send data");
            boolean f = true;
            while (f) {
                try {
                    socket.receive(receive_packet);
                    System.out.println("received data from the client");
                    printPacket(receive_packet);
                    //准备返回数据
                    DatagramPacket send_packet = new DatagramPacket(send_message.getBytes(), send_message.length(),
                            receive_packet.getAddress(), receive_packet.getPort());
                    socket.send(send_packet);

                    //由于receive接收了数据，所以要重新设置为1024
                    receive_packet.setLength(1024);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private static void printPacket(DatagramPacket packet) {
        String str=new String(packet.getData(),0,packet.getLength())+"from"+
                                        packet.getAddress()+":"+packet.getPort();
        System.out.println(str);
    }
}
