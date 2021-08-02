package controller;

import daos.CertificateDAO;
import daos.FresherDAO;
import entities.Fresher;
import entities.Intern;
import exceptions.BirthdayException;
import exceptions.EmailException;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;

public class FresherController {

    private FresherDAO fresherDAO;

    public void save(Fresher fresher){
        if(fresher == null){
            System.out.println("Error ! ");
        }
        if(fresher.getId() == null){
            System.out.println("ID is not null !");
        }
        try {
            if(fresher.getBirthDay() != null && !(fresher.getBirthDay().isBefore(LocalDate.now()) &&
                    fresher.getBirthDay().isAfter(LocalDate.of(1900,12,31)))){
                throw new BirthdayException();
            }
            if( !EmailValidator.getInstance().isValid(fresher.getEmail())){
                throw new EmailException();
            }} catch (Exception e){
            System.out.println("The system has encountered an unexpected problem, sincerely sorry !!");
            return;
        }
        Fresher findFresher = fresherDAO.findByEmail(fresher.getEmail());
        if(findFresher != null){
            System.out.println("Email is duplicate !");
        }
        fresherDAO.save(fresher);
        CertificateDAO certificateDAO = new CertificateDAO();
    }


}
