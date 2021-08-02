package controller;

import daos.CertificateDAO;
import daos.ExperienceDAO;
import entities.Certificate;

public class CertificateController {
    private CertificateDAO certificateDAO;
    public CertificateController() {
        this.certificateDAO = new CertificateDAO();
    }
    public void save(Certificate certificate){
        if(certificate == null){
            System.out.println("Object is null ! please try again");
        }
        if(certificate.getId() == null || certificate.getExperience_id() == null
                || certificate.getFresher_id() == null || certificate.getIntern_id() == null){
            System.out.println("Id is not null ! Try again");
        }
    }
}
