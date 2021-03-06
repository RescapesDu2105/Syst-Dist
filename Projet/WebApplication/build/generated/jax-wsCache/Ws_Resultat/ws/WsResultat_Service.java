
package ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "Ws_Resultat", targetNamespace = "http://ws/", wsdlLocation = "http://localhost:8023/Ws_Resultat?wsdl")
public class WsResultat_Service
    extends Service
{

    private final static URL WSRESULTAT_WSDL_LOCATION;
    private final static WebServiceException WSRESULTAT_EXCEPTION;
    private final static QName WSRESULTAT_QNAME = new QName("http://ws/", "Ws_Resultat");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8023/Ws_Resultat?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WSRESULTAT_WSDL_LOCATION = url;
        WSRESULTAT_EXCEPTION = e;
    }

    public WsResultat_Service() {
        super(__getWsdlLocation(), WSRESULTAT_QNAME);
    }

    public WsResultat_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), WSRESULTAT_QNAME, features);
    }

    public WsResultat_Service(URL wsdlLocation) {
        super(wsdlLocation, WSRESULTAT_QNAME);
    }

    public WsResultat_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WSRESULTAT_QNAME, features);
    }

    public WsResultat_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WsResultat_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WsResultat
     */
    @WebEndpoint(name = "Ws_ResultatPort")
    public WsResultat getWsResultatPort() {
        return super.getPort(new QName("http://ws/", "Ws_ResultatPort"), WsResultat.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WsResultat
     */
    @WebEndpoint(name = "Ws_ResultatPort")
    public WsResultat getWsResultatPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws/", "Ws_ResultatPort"), WsResultat.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSRESULTAT_EXCEPTION!= null) {
            throw WSRESULTAT_EXCEPTION;
        }
        return WSRESULTAT_WSDL_LOCATION;
    }

}
