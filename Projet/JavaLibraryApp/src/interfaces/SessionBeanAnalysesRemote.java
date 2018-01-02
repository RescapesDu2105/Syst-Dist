/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Analyses;
import entities.Demande;
import entities.Medecin;
import entities.Patient;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.ejb.Remote;

/**
 *
 * @author Philippe
 */
@Remote
public interface SessionBeanAnalysesRemote
{
    public Principal AuthentificationMedecin();
    public Principal AuthentificationLaborantin();
    //public Medecin Authentification();
    public Medecin getMedecinByLogin(String login);
    public ArrayList<Analyses> getAnalyses();
    public ArrayList<Analyses> getAnalysesByDemande(Demande Demande);
    public int PrescrireDemande(Medecin medecin, Patient patient, boolean urgent, ArrayList<String> analyses);
    public void TraiterDemande(Demande demande, ArrayList<Analyses> analyses, Date dateDebut, Date dateFin);    
    public HashMap<Demande, ArrayList<Analyses>> getAllResultatsAnalysesByPatient(Patient patient, boolean urgent);
}
