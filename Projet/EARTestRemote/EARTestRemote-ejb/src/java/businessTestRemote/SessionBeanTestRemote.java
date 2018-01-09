/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessTestRemote;

import javax.ejb.Stateless;

/**
 *
 * @author Doublon
 */
@Stateless
public class SessionBeanTestRemote implements SessionBeanTestRemoteRemote
{

    @Override
    public String SayHello(String Nom)
    {
        return "Hello " + Nom;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
