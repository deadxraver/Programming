package main;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import com.thoughtworks.xstream.XStream;
import annotations.MainMethod;
import communication.RequestPackage;
import communication.ResponsePackage;
import com.thoughtworks.xstream.security.AnyTypePermission;
import commandhelper.Message;
import elements.Movie;
import elements.MovieCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parsers.CollectionParser;
import parsers.FileDataParser;

public final class Program {
    public Program(int port) throws IOException {
        this.port = port;
        logger = LoggerFactory.getLogger(this.getClass());
        createChannel();
        reader = new BufferedReader(new InputStreamReader(System.in));
        sc = null;
        xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        collection = CollectionParser.parse((MovieCollection) xStream.fromXML(FileDataParser.parse(new String(new FileInputStream(System.getenv("COLLECTION")).readAllBytes(), StandardCharsets.UTF_8))));
    }
    public final int port;
    private final Logger logger;
    private ServerSocketChannel ssc;
    private SocketChannel sc;
    private final MovieCollection collection;
    private final XStream xStream;
    private final BufferedReader reader;


    @MainMethod
    public void execute() throws IOException {
            while (true) {
                if (acceptConnection()) {
                    try (Socket socket = sc.socket()) {
                        RequestPackage<?> requestPackage = getRequest(socket);
                        Message message = requestPackage.command().execute(collection, requestPackage.argument());
                        sendResponse(new ResponsePackage(message), socket);
                    } catch (IOException | ClassNotFoundException e) {
                        logger.info(e.getMessage());
                    }
                } else if (reader.ready()) {
                    String input = reader.readLine().trim();
                    if (input.equals("exit") || input.equals("save")) {
                        try (FileOutputStream fos = new FileOutputStream(System.getenv("COLLECTION"))) {
                            fos.write(xStream.toXML(collection).getBytes());
                        }
                        if (input.equals("exit")) System.exit(0);
                    }
                }
            }
        }

    private void createChannel() {
        try {
            ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(this.port));
            ssc.configureBlocking(false);
            logger.info("The server is active. Current address is {}", ssc.socket().getInetAddress().getHostAddress());
        } catch (IOException e) {
            logger.info("Error: ", new Throwable("Could not create channel"));
            System.exit(1);
        }
    }

    private boolean acceptConnection() {
        try {
            if ((sc = ssc.accept()) == null) throw new IOException();
            logger.info("Client connected");
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
    private void sendResponse(ResponsePackage response, Socket socket) throws IOException {
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(response);
            oos.flush();
            logger.info("Response sent, {}", response);
    }
    /**
     * Method used to get request from client and return it as a package
     *
     * @return         requestPackage RequestPackage element
     */
    private RequestPackage<?> getRequest(Socket socket) throws IOException, ClassNotFoundException {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestPackage<?> rp = (RequestPackage<?>) ois.readObject();
            logger.info("Request received, {}", rp);
            return rp;
    }

    private Movie getMovie() {
        try (
                ObjectInputStream ois = new ObjectInputStream(sc.socket().getInputStream())
                ) {
            Movie movie = (Movie) ois.readObject();
            logger.info("Movie received: {} ", movie);
            ois.close();
            return movie;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
