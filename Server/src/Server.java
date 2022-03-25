import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String []args) throws IOException
    {
        String so1,so2,pheptoan,kqstr,traloi;
        double ketqua = 0;
        // tao server socket
        ServerSocket server = new ServerSocket(1234);
        System.out.println("Server is now already");
        //tao 1 socket do ket noi tu client toi server
        Socket connectionSocket = server.accept();
        //tao luong nhan du lieu tu client
        DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
        // tao luong gui du lieu toi client
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

        do {
            outToClient.writeBytes("Moi nhap a: " + '\n');
            // truyen du lieu tu client vao 2 bien so1 va so2
            so1 = inFromClient.readLine();
            outToClient.writeBytes("Moi nhap b: " + '\n');
            so2 = inFromClient.readLine();
            outToClient.writeBytes("Moi phep toan: " + '\n');
            pheptoan = inFromClient.readLine();

            //ep so1 va so2 tu kieu String sang kieu Integer
            int a = Integer.parseInt(so1);
            int b = Integer.parseInt(so2);
            switch (pheptoan) {
                case "+":
                    ketqua = a + b;
                    break;
                case "-":
                    ketqua = a - b;
                    break;
                case "*":
                    ketqua = a * b;
                    break;
                case "/":
                    if (b == 0) {
                        kqstr = "ERROR b=0";
                        break;
                    }
                    ketqua = a * 1.0f / b;
                    break;
            }
            //ep ketqua 2 so sang kieu String
            kqstr = String.valueOf(ketqua);
            //gui kqstr ve client
            outToClient.writeBytes(kqstr + '\n');

            outToClient.writeBytes("Lam tiep hay khong:" + '\n');
            traloi = inFromClient.readLine();
        }while(traloi.equalsIgnoreCase("Co"));

        //dong luong nhan du lieu tu client
        inFromClient.close();
        //dong luong gui du lieu ve client
        outToClient.close();
        //dong server socket
        server.close();
    }

}





























