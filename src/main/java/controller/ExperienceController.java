package controller;

import daos.CertificateDAO;
import daos.ExperienceDAO;
import entities.Experience;
import exceptions.BirthdayException;
import exceptions.EmailException;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;
import java.util.List;

public class ExperienceController {

    private ExperienceDAO experienceDAO;

    public ExperienceController() {
        this.experienceDAO = new ExperienceDAO();
    }

    public void save(Experience experience){
        if(experience == null){
            System.out.println("Error ! ");
        }
        if(experience.getId() == null){
            System.out.println("ID is not null ! Ply try again. ");
        }
        try {
            if(experience.getBirthDay() != null && !(experience.getBirthDay().isBefore(LocalDate.now()) &&
                    experience.getBirthDay().isAfter(LocalDate.of(1900,12,31)))){
                throw new BirthdayException();
            }
            if( !EmailValidator.getInstance().isValid(experience.getEmail())){
                throw new EmailException();
            }} catch (Exception e){
            System.out.println("The system has encountered an unexpected problem, sincerely sorry !!");
            return;
        }
        Experience findExperience = experienceDAO.findByEmail(experience.getEmail());
        if(findExperience != null){
            System.out.println("Email is duplicate !");
        }
        experienceDAO.save(experience);
        CertificateDAO certificateDAO = new CertificateDAO();
    }

    public List<Experience> findAll(String id){
        experienceDAO = (Experience) experienceDAO.findAll("id");
        return (List<Experience>) experiences;
    }

}
