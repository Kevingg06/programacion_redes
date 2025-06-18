package ar.edu.et32.Chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Connection {

    public Server(enumType type) throws IOException {
        super(type);
    }

    public void serverOn() {

        InputStreamReader disServer = null;
        BufferedReader br = null;
        try {
            ps.println("Esperando conexion con el cliente...");
            sockClient = sockServer.accept();

            ps.printf("%s - %s",
                    sockClient.getInetAddress(),
                    sockClient.getInetAddress().getHostAddress()
            );

            dosClient = new DataOutputStream(sockClient.getOutputStream());
            disServer = new InputStreamReader(sockClient.getInputStream());
            br = new BufferedReader(disServer);

            ps.println("Cliente conectado con exito.");
            Thread.sleep(200);
            ps.println("Esperando mensaje del cliente...");

            while ((msg = br.readLine()) != null ){
                ps.printf("Mensaje: %s\n", msg);
                dosClient.writeUTF("ok");
                dosClient.flush();
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                sockClient.close();
                if (br != null)
                    br.close();
                if (disServer != null)
                    disServer.close();

                dosClient.close();
                sockServer.close();
            } catch (IOException e){
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
