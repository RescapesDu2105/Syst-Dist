/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessTestRemote;

import javax.ejb.Remote;

/**
 *
 * @author Doublon
 */
@Remote
public interface SessionBeanTestRemoteRemote
{

    String SayHello(String Nom);
    
}
