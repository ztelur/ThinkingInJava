package com.company.network.socket;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by randy on 15-3-29.
 */
public class Client {
    public static void main(String[] args) throws IOException{
        //客户机和本机在2006端口建立TCP连接
        Socket client=new Socket("127.0.0.1",20006);
        client.setSoTimeout(10000);
        //从键盘获得输入
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        //获得socket的输出流，用来发送服务到服务端
        PrintStream out=new PrintStream(client.getOutputStream());
        //获取scoket的输入流
        BufferedReader buf=new BufferedReader(new InputStreamReader(client.getInputStream()));
        boolean flag=true;
        while (flag) {
            System.out.println("请输入数据");
            String str=input.readLine();
            //发送数据到服务器端
            out.println(str);
            if ("end".equals(str)) {
                flag=false;
            } else {
                //从服务器接收数据有个时间限制
                try {
                    String echo = buf.readLine();
                    System.out.println(echo);
                } catch (SocketTimeoutException e) {
                    System.out.println("Time out, NO response");
                }
            }
        }
        input.close();
        if (client!=null) {
            //如果TCP成功建立了，那么关闭它，如果没有就不用关闭
            client.close();
        }
    }
}
