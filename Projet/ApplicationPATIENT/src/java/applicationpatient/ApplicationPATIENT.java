/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationpatient;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import javax.xml.ws.Endpoint;
import ws.WsSelfHosted;

/**
 *
 * @author Philippe
 */
public class ApplicationPATIENT
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String address = "http://localhost:8023/WebServiceDemo";
        Endpoint endpoint = Endpoint.publish(address, new WsSelfHosted());
        System.out.println("WS listening at : " + address + "\nUse " + address + "?wsdl to see the wsdl");
        
        try
        {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            HttpContext context = server.createContext("/stop");
            server.setExecutor(null);
            server.start();
            
            context.setHandler((HttpExchange msg) -> 
            {
                endpoint.stop();
                String response = "!!! WS stopped !!!";
                msg.sendResponseHeaders(200, response.length());
                OutputStream os = msg.getResponseBody();
                os.close();
                server.stop(0);
                System.out.println("WS stopped");
            });
        }
        catch(Exception e)
        {
            System.out.println("Error : " + e);
        }
    }
    
}
