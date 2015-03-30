package com.company.network.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by randy on 15-3-29.
 */
public class Client2 {
    public static void main(String[] args) throws IOException{
        Socket socket=new Socket("127.0.0.1",20006);
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        PrintStream printStream=new PrintStream(socket.getOutputStream());
        BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        boolean flag=true;
        while (flag) {
            String str=input.readLine();
            printStream.println(str);
            if (str==null||"".equals(str)) {

            } else {
                try {
                    String echo = in.readLine();
                    System.out.println("client2:" + echo);
                } catch (SocketTimeoutException e) {
                    e.printStackTrace();
                }
            }

        }
        input.close();
        socket.close();
    }

}
