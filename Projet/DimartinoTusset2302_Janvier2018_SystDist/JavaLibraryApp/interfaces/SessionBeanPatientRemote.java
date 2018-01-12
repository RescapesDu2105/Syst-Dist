/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Patient;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Philippe
 */
@Remote
public interface SessionBeanPatientRemote
{
    List getPatients();
    ArrayList<Patient> ChercherPatient(String nom, String prenom);
    int AjouterPatient(String nom, String prenom, String login);
    int AjouterPatient(Patient p);
    void ModifierPatient(Patient p);
    Patient getPatient(int idPatient);
}
