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
/*public class NotificationClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received notification: " + message);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/

public class NotificationClient {
    private static final int PORT = 9876;
    //private NotificationService notificationService = new NotificationService();
    
    /*public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getLocalHost();

        // Create notification object with data to be stored
        notification notif = new notification(1, "Hello, world!", null);
        byte[] buffer = notif.getDescription().getBytes();

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
        socket.send(packet);
        socket.close();
    }*/
    
    // The class has a main method which creates a NotificationService object, initializes a DatagramSocket object, sends a notification message to the server using a DatagramPacket,
    //adds the notification to the NotificationService object using its ajouter method, and finally closes the socket.
    public static void main(String[] args) throws Exception {
    NotificationService notificationService = new NotificationService();
    DatagramSocket socket = new DatagramSocket();
    byte[] buffer = "Hello, world!".getBytes();
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), PORT);
    socket.send(packet);
    notificationService.ajouter(new notification(1, "Hello, world!")); // Replace 1 with the actual user ID
    socket.close();
}
}
