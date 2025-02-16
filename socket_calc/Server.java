import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        //Creating server socket to listen on port 5000:
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is running. Waiting for a connection...");
            //Accepting client connection:
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            //Reading input from client:
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Sending output to client:
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            //Variables to store the operands:
            int x = 0, y = 0;
            //Storing the most recent operation:
            String lastOperation = "add";

            while (true) {
                //Reading input from client:
                String input = in.readLine();
                //Exiting loop is input is null:
                if (input == null) break;

                String[] parts = input.split(" ");

                //For "x y operation" requests:
                if (parts.length == 3) {
                    x = Integer.parseInt(parts[0].replace(",", ""));
                    y = Integer.parseInt(parts[1]);
                    lastOperation = parts[2];
                    //Sending the result to client:
                    out.println(calculate(x, y, lastOperation));
                } 
                //For change requests:
                else if (parts.length == 4 && parts[0].equalsIgnoreCase("change")) {
                    if (parts[1].equalsIgnoreCase("x")) {
                        x = Integer.parseInt(parts[3]);
                    } else if (parts[1].equalsIgnoreCase("y")) {
                        y = Integer.parseInt(parts[3]);
                    }
                    //Sending the updated result to client:
                    out.println(calculate(x, y, lastOperation));
                }
            }
            //Closeing socket connection:
            socket.close();
        } catch (IOException e) {
            //Printing any IO exceptions:
            e.printStackTrace();
        }
    }

    //Method to calculate the arithmetic operations:
    private static String calculate(int x, int y, String operation) {
        switch (operation.toLowerCase()) {
            case "add": return String.valueOf(x + y);
            case "subtract": return String.valueOf(x - y);
            case "multiply": return String.valueOf(x * y);
            case "divide": return y != 0 ? String.valueOf(x / y) : "Error: Division by zero";
            //Handling invalid operations:
            default: return "Invalid operation";
        }
    }
}
