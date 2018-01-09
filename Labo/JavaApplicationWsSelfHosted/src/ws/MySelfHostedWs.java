/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.WebMethod;

/**
 *
 * @author Doublon
 */

@WebService(serviceName = "MySelfHostedWs")
public class MySelfHostedWs
{
    @WebMethod(operationName = "SayHello")
    public String SayHello(@WebParam(name = "name") String txt)
    {
        return "Hello " + txt + " !!!!!!";
    }
}
