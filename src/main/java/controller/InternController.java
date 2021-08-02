package controller;

import daos.CertificateDAO;
import daos.InternDAO;
import entities.Intern;
import exceptions.BirthdayException;
import exceptions.EmailException;
import org.apache.commons.validator.routines.EmailValidator;
import java.time.LocalDate;

public class InternController {

    private InternDAO internDAO;

    public void save(Intern intern){
        if(intern == null){
            System.out.println("Error ! ");
        }
        if(intern.getId() == null){
            System.out.println("ID is not null !");
        }
        try {
            if(intern.getBirthDay() != null && !(intern.getBirthDay().isBefore(LocalDate.now()) &&
                    intern.getBirthDay().isAfter(LocalDate.of(1900,12,31)))){
                throw new BirthdayException();
            }
            if( !EmailValidator.getInstance().isValid(intern.getEmail())){
                throw new EmailException();
            }} catch (Exception e){
            System.out.println("The system has encountered an unexpected problem, sincerely sorry !!");
            return;
        }
        Intern findIntern = internDAO.findByEmail(intern.getEmail());
        if(findIntern != null){
            System.out.println("Email is duplicate !");
        }
        internDAO.save(intern);
        CertificateDAO certificateDAO = new CertificateDAO();
    }
}
