import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is running and waiting for a connection...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            int x = 0, y = 0;

            while (true) {
                String input = in.readLine();
                if (input == null) break;

                String[] parts = input.split(" ");
                if (parts.length == 3) {
                    x = Integer.parseInt(parts[0].replace(",", ""));
                    y = Integer.parseInt(parts[1]);
                    String operation = parts[2];
                    out.println(calculate(x, y, operation));
                } else if (parts.length == 4 && parts[0].equalsIgnoreCase("change")) {
                    if (parts[1].equalsIgnoreCase("x")) {
                        x = Integer.parseInt(parts[3]);
                    } else if (parts[1].equalsIgnoreCase("y")) {
                        y = Integer.parseInt(parts[3]);
                    }
                    out.println(calculate(x, y, "add"));
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String calculate(int x, int y, String operation) {
        switch (operation.toLowerCase()) {
            case "add": return String.valueOf(x + y);
            case "subtract": return String.valueOf(x - y);
            case "multiply": return String.valueOf(x * y);
            case "divide": return y != 0 ? String.valueOf(x / y) : "Error: Division by zero";
            default: return "Invalid operation";
        }
    }
}
