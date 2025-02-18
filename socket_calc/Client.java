// NAME: SIRI HEGDE
// HOMEWORK-2 COMP512

// Question-2 statement:
// Write small client - server codes which use the socket for communication. When run, the client should be prompted to enter a pair of (x, y) 
// integers and a calculation operant (add, subtract, multiply, divide) and then send them to the server. The server then calculates the result 
// in the form of:
// x operant y
// Then send the result back to the client. Then, the client will display the calculated result. After one calculation round has been completed, 
// the client can:
// 1. Send another calculation request, which is similar to the one described above
// 2. Or send one change request. 
// For the change request, the client sends a message in the form of the following:
// change x to x_value
// or
// change y to y_value
// then the server calculates the new value and sends the new result to the client for display.

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
