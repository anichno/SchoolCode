package pex4guibuilder;


/**
 * Functionality to send and receive messages to a Connect 4 Game server.
 * 
 * @author Wayne.Brown
 * 
 * @version 1.0 - Nov 3, 2011 at 7:26:11 AM
 */
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.Scanner;

//=====================================================================
// A TCP socket for sending and receiving messages to a server.
//=====================================================================

public class RemotePlayer {

  public static final int PORT = 22;
  private static final boolean TRACE = false;
  private final long TWO_MINUTES = 1000 * 120; // in milliseconds
  private final int REMOTE_MOVE_COULD_NOT_BE_RETRIEVED = -1;
  //
  private String localPlayerName;
  private String localPlayerIPAddress;
  //
  private String remotePlayerName;
  private String remotePlayerIPAddress;
  //
  private String server;
  private int port;

  /**
   * Constructor
   * 
   * @param localPlayerName The name of the local player
   * @param remotePlayerName The name of the remote player
   */
  public RemotePlayer(String localPlayerName, String remotePlayerName) {
    if (TRACE) {
      System.out.println("Creating a RemotePlayer");
    }
    this.localPlayerName = new String(localPlayerName);
    this.remotePlayerName = new String(remotePlayerName);

    server = "DFCS.usafa.ds.af.edu";
    port = PORT;

    getIPAddresses();

    if (remotePlayerIPAddress == null) {
      String message = "The player, " + remotePlayerName + " can't be contacted.";
      String title = "Connection failed";
      int results = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
  }

  /**
   * Sends a message to the Connect 4 server:
   *   "whoIsFirst PlayerName@IPaddress PlayerName@IPaddress"
   * 
   * @return the name of the player that goes first.
   */
  public String whoIsFirst() {
    TCPClient client = new TCPClient(server, port);
    String message = "whoIsFirst: " 
                     + localPlayerName + "@" + localPlayerIPAddress + " "
                     + remotePlayerName + "@" + remotePlayerIPAddress + "\n";
    if (TRACE) System.out.println("RemotePlayer sending: " + message);
    client.sendMessageToServer(message);
    String reply = client.receiveMessageFromServer();
    if (TRACE) System.out.println("Who goes first is: " + reply);
    return reply;
  }

  /**
   * Send a message to the Connect 4 server:
   *    "sendPlay PlayerName@IPaddress column"
   * This is the play made by the local player. It sits on the server
   * until the remote player retrieves it.
   * 
   * @param column the column the local user played in.
   */
  public void sendLastPlay(int column) {
    String message = "sendPlay: "
                     + localPlayerName + "@" + localPlayerIPAddress
                     + " " + column + "\n";

    if (TRACE) System.out.println("RemotePlayer sending: " + message);
    TCPClient client = new TCPClient(server, port);
    client.sendMessageToServer(message);
  }

  /**
   * Send a message to the Connect 4 server:
   *    "getPlay PlayerName@IPaddress"
   * This attempts to retrieve a play from a remote player -- actually 
   * a play that the remote player has sent to the Connect 4 server.
   * It send a retrieve message every 1/2 second for up to 2 minutes.
   * If no move is found in that time, it returns an invalid move.
   * 
   * @return column the column the local user played in or 
   *         REMOTE_MOVE_COULD_NOT_BE_RETRIEVED if no move could be 
   *         retrieved within 2 minutes.
   */
  public int getRemotePlay() {
    int column = REMOTE_MOVE_COULD_NOT_BE_RETRIEVED;

    try {
      // Continueally ask for the remote player's move, once every 1/2 second
      // Only try for 2 minutes and then give up
      long startTime = System.currentTimeMillis();
      long elapsedTime = 0;
      String message = "getPlay: " + remotePlayerName + "@" + remotePlayerIPAddress + "\n";
      do {
        Thread me = Thread.currentThread();
        synchronized (me) {
          me.wait(500); // wait
          me.notify(); // stop waiting
        }

        TCPClient client = new TCPClient(server, port);
        if (TRACE) {
          System.out.println("RemotePlayer sending: " + message);
        }
        client.sendMessageToServer(message);
        String reply = client.receiveMessageFromServer();

        if (!reply.equalsIgnoreCase("none")) {
          Scanner parse = new Scanner(reply);
          if (parse.hasNextInt()) {
            column = parse.nextInt();
          }
        }
        elapsedTime = System.currentTimeMillis() - startTime;
      } while (column == -1 && elapsedTime < TWO_MINUTES);

    } catch (Exception error) {
      System.out.println("Error getting next remote move: " + error);
    }

    return column;
  }

  /**
   * Send a message to the Connect 4 server:
   *    "endGame PlayerName@IPaddress PlayerName@IPaddress"
   * This removed the players from the Connect 4 server. If this method
   * is not called, the server will remember the players indefinitely,
   * which might cause incorrect data to be transferred for a new game
   * that uses the same player name and IP address.
   */
  public void endGame() {
    TCPClient client = new TCPClient(server, port);
    String message = "endGame: " 
                     + localPlayerName + "@" + localPlayerIPAddress + " "
                     + remotePlayerName + "@" + remotePlayerIPAddress + "\n";
    if (TRACE) System.out.println("RemotePlayer sending: " + message);
    client.sendMessageToServer(message);
  }

  /**
   * A private method to query the Connect 4 server for the IP address
   * of a player, given the player's name. Assuming that the remote player
   * starts their Connect 4 game at about the same time as the local player,
   * the Connect 4 server will return the correct player based on the 
   * player name of the remote player. 
   */
  private void getIPAddresses() {

    try {
      TCPClient client = new TCPClient(server, port);
      // Get this player's IP address
      localPlayerIPAddress = client.getIPAddress().getHostAddress();
      remotePlayerIPAddress = null;

      // Send the name of the player and this local host IP address to the server
      String startMessage = "sendAddress: " + localPlayerName + "@" + localPlayerIPAddress + "\n";
      client.sendMessageToServer(startMessage);
      //String sendMessageReply = client.receiveMessageFromServer();

      // Get the remote player's IP address
      // Only try for 2 minutes and then give up
      long startTime = System.currentTimeMillis();
      long elapsedTime = 0;
      String message = "getRemoteAddress: " + remotePlayerName + "\n";
      do {
        client = new TCPClient(server, port);
        client.sendMessageToServer(message);
        String reply = client.receiveMessageFromServer();

        if (reply.length() > 0) {
          Scanner parse = new Scanner(reply);
          parse.useDelimiter("@");
          if (parse.hasNext()) {
            String name = parse.next();
            if (parse.hasNext()) {
              remotePlayerIPAddress = parse.next();
            }
          }
        }
        elapsedTime = System.currentTimeMillis() - startTime;
      } while (remotePlayerIPAddress == null && elapsedTime < TWO_MINUTES);

    } catch (Exception error) {
      System.out.println("Error getting IP addresses: " + error);
    }

    if (TRACE) System.out.println("Initialized local player: "
                         + localPlayerName + "@" + localPlayerIPAddress);
    if (TRACE) System.out.println("Initialized remote player: "
                         + remotePlayerName + "@" + remotePlayerIPAddress);
  }
}
class TCPClient {

  private final boolean TRACE = false;
  private Socket clientSocket;
  private InetAddress clientAddress;

  /**
   * Constructor
   * 
   * @param serverName (Can be name or IP address)
   * @param portNumber (of server)
   */
  public TCPClient(String serverName, int portNumber) {
    try {
      if (TRACE) System.out.println("Creating a socket to '" + serverName + "' on port " + RemotePlayer.PORT);
      clientSocket = new Socket(serverName, RemotePlayer.PORT);
    } catch (Exception error) {
      System.out.println("Error creating the client socket: error = " + error);
      clientSocket = null;
    }
    clientAddress = clientSocket.getLocalAddress();
    if (TRACE) System.out.println("Local ip is : " + clientAddress.getHostAddress());
  }

  /**
   * Get the local IP address of the client (game)
   * 
   * @return IP address
   */
  public InetAddress getIPAddress() {
    return clientAddress;
  }

  /** 
   * Send a message to the server.
   * 
   * @param message The message to send.
   */
  public void sendMessageToServer(String message) {
    if (clientSocket != null) {
      try {
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        if (TRACE) System.out.println("Sending: '" + message + "'");
        outToServer.writeBytes(message);

      } catch (Exception error) {
        System.out.println("Error creating the client socket: error = " + error);
      }
    }
  }

  /**
   * Receive a message from the server.
   * 
   * @return the message received
   */
  public String receiveMessageFromServer() {
    String receivedMessage = null;
    if (clientSocket != null) {
      try {
        BufferedReader inFromServer = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

        if (TRACE) System.out.println("Trying to receive a message from the server.");
        if (TRACE) System.out.println("clientSocket: " + clientSocket);
        receivedMessage = inFromServer.readLine();

        if (TRACE) System.out.println("Received: '" + receivedMessage + "'");
      } catch (Exception error) {
        System.out.println("Error reading data from the server: error = " + error);
      }
    }
    return receivedMessage;
  }
}
