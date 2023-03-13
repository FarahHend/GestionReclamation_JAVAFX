/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.notification;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Hend
 */
/*public class NotificationServer {
    private static final int PORT = 9876;

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        byte[] buffer = "Hello, world!".getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), PORT);
        socket.send(packet);
        socket.close();
    }
}*/

// implements a NotificationServer class that listens for UDP packets on port 9876 
//and stores the data received in a notification object that is then added to a database through a NotificationService object.
public class NotificationServer {
    private static final int PORT = 9876;

    //The server creates a DatagramSocket object bound to the specified port and listens for incoming DatagramPacket objects. When a packet is received, the data is extracted and stored in a notification object. 
    //The user ID is assumed to be 1 for all notifications in this example, and the current date and time are not included in the notification object.
    public static void main(String[] args) throws Exception {
        NotificationService service = new NotificationService();
        DatagramSocket socket = new DatagramSocket(PORT);
        byte[] buffer = new byte[1024];

        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received: " + received);

            // Create notification object and store it in the database
            notification notif = new notification(1, received, null); //Replace 1 with the actual user ID
            service.ajouter(notif);
        }
    }
}

