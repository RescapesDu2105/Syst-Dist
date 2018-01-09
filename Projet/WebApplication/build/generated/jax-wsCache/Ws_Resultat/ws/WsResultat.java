
package ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Ws_Resultat", targetNamespace = "http://ws/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WsResultat {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<ws.Analyses>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getResultats", targetNamespace = "http://ws/", className = "ws.GetResultats")
    @ResponseWrapper(localName = "getResultatsResponse", targetNamespace = "http://ws/", className = "ws.GetResultatsResponse")
    @Action(input = "http://ws/Ws_Resultat/getResultatsRequest", output = "http://ws/Ws_Resultat/getResultatsResponse")
    public List<Analyses> getResultats(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

}
