import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        //Connecting to server on port 5000:
        try (Socket socket = new Socket("localhost", 5000);
            //Reading input from server:
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             //Sending output to server:
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             //Scanner for user input:
             Scanner scanner = new Scanner(System.in)) {
            
            while (true) {
                System.out.print("Enter calculation (x, y operation) or change request: ");
                //Reading user input:
                String input = scanner.nextLine();
                //Sending input to server:
                out.println(input);
                
                //Reading response from server:
                String response = in.readLine();
                //Exiting if no response from server:
                if (response == null) break;
                //Display result from server:
                System.out.println(response);
            }
        } catch (IOException e) {
            //Printing any IO exceptions:
            e.printStackTrace();
        }
    }
}
