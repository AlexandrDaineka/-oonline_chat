package Daineka.Client;

import Daineka.Server.SettingsTXT;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class ClientProcess implements Runnable {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 3; i++) {
            startClient();
        }
    }

    @Override
    public void run() {
        try {
            startClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startClient() throws IOException {
        SettingsTXT parserSettingTXT = new SettingsTXT(new File("settings.txt"));

        try (Socket socket = new Socket((String) parserSettingTXT.getData("host"),
                (int) parserSettingTXT.getData("port"));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        ) {
            threadStart(in);
            messageOut(out);
        }
    }

    private static void messageOut(PrintWriter out) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("введите Ваш никнейм:");
        String clientName = getValueString();
        while (true) {
            System.out.println("Введите сообщение");
            String msg = getValueString();
            if (msg.equals("/exit")) {
                out.println(clientName + ": " + msg);
                break;
            } else {
                out.println(clientName + ": " + msg);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getValueString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void threadStart(BufferedReader in) {
        {
            new Thread(() -> {
                try {
                    String mess;
                    while ((mess = in.readLine()) != null) {
                        System.out.println(mess);
                    }
                } catch (IOException e) {
                    System.out.println("Вы вышли из чата");
                }
            }).start();
        }
    }
}