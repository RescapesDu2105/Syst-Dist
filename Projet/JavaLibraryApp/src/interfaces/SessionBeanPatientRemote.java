/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Patient;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Philippe
 */
@Remote
public interface SessionBeanPatientRemote
{
    ArrayList<Patient> getPatients();
    ArrayList<Patient> ChercherPatient(String nom, String prenom);
    void AjouterPatient(int idPatient, String nom, String prenom, String login);
    void ModifierPatient(int idPatient);
    Patient getPatient(int idPatient);
}
