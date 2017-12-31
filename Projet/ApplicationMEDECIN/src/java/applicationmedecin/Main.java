/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationmedecin;

import entities.Medecin;
import interfaces.SessionBeanAnalysesRemote;
import interfaces.SessionBeanPatientRemote;
import java.security.Principal;
import javax.ejb.EJB;

/**
 *
 * @author Philippe
 */
public class Main
{
    @EJB
    private static SessionBeanAnalysesRemote EJBAnalyses;
    @EJB
    private static SessionBeanPatientRemote EJBPatients;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {           
            Principal principal = EJBAnalyses.AuthentificationMedecin();
            Medecin medecin = EJBAnalyses.getMedecinByLogin(principal.getName());
            

            if(medecin == null)
            {
                //Erreur
                //JOptionPane.showMessageDialog(this, "Le nom d'utilisateur et/ou le mot de passe est incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
                System.err.println("Erreur de login 1");
                //System.exit(1);
            }
            else
            {
                new EntrerPatientForm(EJBAnalyses, EJBPatients, medecin).setVisible(true);
            }
        }
        catch (Exception ex) 
        {
            System.err.println("Erreur de login 2");
        }
    }
    
}
