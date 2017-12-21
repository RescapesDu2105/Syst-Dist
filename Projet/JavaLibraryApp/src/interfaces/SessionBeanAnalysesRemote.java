/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Medecin;
import java.security.Principal;
import javax.ejb.Remote;

/**
 *
 * @author Philippe
 */
@Remote
public interface SessionBeanAnalysesRemote
{
    public Principal Authentification();
    //public Medecin Authentification();
    public Medecin getMedecinByLogin(String login);
}
