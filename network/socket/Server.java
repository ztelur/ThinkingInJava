package com.company.network.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by randy on 15-3-29.
 */
public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket server=new ServerSocket(20006);
        Socket client=null;
        boolean f=true;
        while (f) {
            client=server.accept();
            System.out.println("与客户端连接成功");
            new Thread(new ServerRunnable(client)).start();
        }
        server.close();
    }
}
class ServerRunnable implements Runnable{
    private Socket client=null;
    public ServerRunnable(Socket client) {
        this.client=client;
    }
    @Override
    public void run() {
        try {
            //获取socket的输入流，向客户端输出
            PrintStream out = new PrintStream(client.getOutputStream());
            BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean flag=true;

            while (flag) {
                //接收客户端发过来的请求
                String str=in.readLine();
                Thread.sleep(10000);
                if (str==null||"".equals(str)) {
//                    flag=false;
                } else {
                    System.out.println("收到客户端数据");
                    if ("end".equals(str)){
                        flag=false;
                    } else {
                        out.println("server echo:"+str);
                    }
                }
            }
            out.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
