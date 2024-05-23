/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
/**
 *
 * @author ADMIN
 */
public class ImageServer {
      public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server started, waiting for connection...");
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client connected");
                    InputStream inputStream = clientSocket.getInputStream();
                    BufferedImage image = ImageIO.read(inputStream);
                    if (image != null) {
                        File outputfile = new File("received_image.jpg");
                        ImageIO.write(image, "jpg", outputfile);
                        System.out.println("Image received and saved as 'received_image.jpg'");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
