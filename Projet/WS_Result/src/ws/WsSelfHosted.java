/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Doublon&Zeydax
 */

@WebService(serviceName = "WSHoster")
public class WsSelfHosted
{
    @WebMethod(operationName = "ConsulterResultats")
    public String ConsulterResultats(@WebParam(name = "Reference") String Reference)
    {
        return "Hello " + Reference + " !!!!";
    }
}