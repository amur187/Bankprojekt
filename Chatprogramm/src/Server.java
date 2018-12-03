import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
* Server eines ganz einfachen Chats
*/
public class Server {
/**
* Port, unter dem der Server auf eingehenden Verbindungen wartet
*/
	public static int ANMELDEPORT = 7777;
/**
* startet den Server und wartet auf eingehende Verbindungen vom Client
*/
	private void verbindungAnnehmen()
	{
		ServerSocket seso = null;
		Socket so = null;
		
		try {
			seso = new ServerSocket(ANMELDEPORT);
			so = seso.accept();
		} catch (IOException e) {
				e.printStackTrace();		
		} 
			
		try
		{
			InputStream ein = so.getInputStream();
			OutputStream aus = so.getOutputStream();

			while(true){
				BufferedInputStream bis = new BufferedInputStream(ein);
				System.out.println(bis.read());

			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		try {
			seso.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
/**
* startet den Server
*/
	public static void main(String[] args)
	{
		Server server = new Server();
		server.verbindungAnnehmen();
	}

}
