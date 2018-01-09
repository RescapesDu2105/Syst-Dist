/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationwsselfhosted;

/**
 *
 * @author Doublon
 */
public class JavaApplicationWsSelfHosted
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ws.WSTest_Service service  = new ws.WSTest_Service();
        ws.WSTest port = service.getWSTestPort();
        System.out.println("Appel : "+ port.hello("Tusset"));
    }
    
}
