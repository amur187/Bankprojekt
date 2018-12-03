import oracle.jvm.hotspot.jfr.StreamWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
* Client f�r einen ganz einfachen Chat
*/
public class Client {
	
/**
* baut die Verbindung zum Server an der angegebenen Adresse auf
* @param ip IP-Adresse des Servers
*/
	public void verbindungAufbauen(String ip)
	{
		InetAddress ipAdresse;
		Socket so = null;
		try {
			ipAdresse = InetAddress.getByName(ip);
			so = new Socket(ipAdresse, Server.ANMELDEPORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		try {
			OutputStream aus = so.getOutputStream();
			InputStream ein = so.getInputStream();



			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			so.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

/**
* startet den Client
*/
	public static void main(String[] args) {
		Client client = new Client();
		client.verbindungAufbauen("localhost");
	}

}
