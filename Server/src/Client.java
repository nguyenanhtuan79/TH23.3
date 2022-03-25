import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String []args) throws IOException
    {
        // khoi tao 3 bien kieu String
        String a,b,pheptoan,ketqua,traloi;
        //tao socket de ket noi toi server
        Socket ClientSocket = new Socket("Localhost", 1234);
        //thong bao da ket noi thanh cong
        System.out.println("Connected to server");
        //tao luong nhap du lieu tu ban phim
        DataInputStream inFromUser = new DataInputStream(System.in);
        //tao luong nhan du lieu tu server
        DataInputStream inFromServer = new DataInputStream(ClientSocket.getInputStream());
        //tao luong gui du lieu len server
        DataOutputStream outToServer = new DataOutputStream(ClientSocket.getOutputStream());

        do {
            // nhap lieu tu ban phim
            try {
                System.out.println(inFromServer.readLine());
                System.out.println("nhap a :");
                a = inFromUser.readLine();
                outToServer.writeBytes(a + '\n');
                System.out.println(inFromServer.readLine());
                System.out.println("nhap b :");
                b = inFromUser.readLine();
                outToServer.writeBytes(b + '\n');
                System.out.println(inFromServer.readLine());
                System.out.println("nhap phep toan:");
                pheptoan = inFromUser.readLine();
                outToServer.writeBytes(pheptoan + '\n');
            } catch (UnknownHostException e) {
                System.err.println("Server Not Found");
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Cannot make a connection");
                System.exit(1);
            }
            //nhan ve tu server
            ketqua = inFromServer.readLine();
            //in ra man hinh
            System.out.println("Ket qua a va b là: " + ketqua);
            //lam tiep hay khong
            System.out.println(inFromServer.readLine());
            traloi = inFromUser.readLine();
            outToServer.writeBytes(traloi + '\n');
        }while(traloi.equalsIgnoreCase("Co"));

        //dong luong gui du lieu len server
        outToServer.close();
        //dong luong nhan du lieu tu server
        inFromServer.close();
        //dong socket client
        ClientSocket.close();
    }

}































