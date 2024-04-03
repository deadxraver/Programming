package main;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.NoSuchElementException;

import clientcommunication.RequestPackage;
import clientcommunication.ResponsePackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// + Модуль приёма подключений.
// + Модуль чтения запроса.
// - Модуль обработки полученных команд.
// + Модуль отправки ответов клиенту.

public class Program {
    public Program(int port) throws IOException {
        this.port = port;
        createChannel();
        sc = null;
        waitingForMovie = false;
        logger = LoggerFactory.getLogger(this.getClass());
    }
    public final int port;
    private Logger logger;
    private ServerSocketChannel ssc;
    private SocketChannel sc;
    private boolean waitingForMovie;
    public void execute() {
        try {
            createChannel();
        } catch (IOException e) {
            System.out.println("Could not create channel");
            System.exit(1);
        }
        try {
            while (true) {
                if (!acceptConnection()) continue; // todo (try reading from console)
                RequestPackage<?> requestPackage = getRequest();

            }
        } catch (NoSuchElementException e) {
            // TODO (save the collection)
        }

    }
    private void createChannel() throws IOException {
        ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(this.port));
        ssc.configureBlocking(false);
        System.out.println("The server is active. Current address is " + ssc.getLocalAddress());
    }

    private boolean acceptConnection() {
        try {
            if ((sc = ssc.accept()) == null) throw new IOException();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    /**
     * Method used to generate and send server's response
     *
     * @param  response	response to client's request
     */
    private void sendResponse(ResponsePackage<?> response) {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(sc.socket().getOutputStream())
                ) {
            oos.writeObject(response);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Method used to get request from client and return it as a package
     *
     * @return         requestPackage RequestPackage element
     */
    private RequestPackage<?> getRequest() {
        try (
                ObjectInputStream ois = new ObjectInputStream(sc.socket().getInputStream())
                ) {

            return (RequestPackage<?>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
