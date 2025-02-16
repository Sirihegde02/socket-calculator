# Socket-Based Calculator (Client-Server Application)

This project implements a simple **client-server calculator** using Java sockets. The client sends calculations to the server, and the server processes them and returns the result. The client can also modify values using **change requests**, with the server applying the most recent operation.

## **How to Run the Program**

### **Prerequisites**
- Ensure you have **Java JDK** installed on your system.

### **Compiling the Code**
Navigate to the project directory where `Server.java` and `Client.java` are located, and compile them:
```sh
javac Server.java Client.java
```

### **Run the Server**
Start the server first:
```sh
java Server
```
You should see:
```
Server is running. Waiting for a connection...
```

### *Run the Client**
Open a **new terminal window** and run the client:
```sh
java Client
```

### **Example Commands**
Once the client is running, enter a calculation request in the format:
```
5, 6 add
```
**Expected output:**
```
11
```

#### **Change Requests**
To change the value of `x` or `y` **while keeping the last operation**:
```
change x to 8
```
**Server applies the last operation (`add` in this case) with the new value.**

```
change y to 2
```
**Example with division:**
```
3, 3 divide
1
change x to 6
2
```

### **Stopping the Program**
To stop the server, press `Ctrl + C` in the terminal where the server is running.
To stop the client, close the terminal or press `Ctrl + C`.

---

## **Troubleshooting**
### **1. Client Not Connecting?**
- Make sure the **server is running first**.
- Ensure the server and client are using the same **port (5000)**.

### **2. Compilation Errors?**
- Ensure both `Server.java` and `Client.java` are in the same directory.
- Run `javac Server.java Client.java` again to recompile.

### **3. Incorrect Calculations?**
- Ensure you're using valid operations (`add`, `subtract`, `multiply`, `divide`).
- `divide` will return an error if dividing by zero.

---
**Author:** Siri Hegde  
**Language:** Java  
**Concepts Used:** Socket Programming, Multi-threading, Arithmetic Operations
