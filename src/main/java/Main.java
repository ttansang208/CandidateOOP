import controller.ExperienceController;
import controller.FresherController;
import controller.InternController;
import daos.CertificateDAO;
import daos.ExperienceDAO;
import daos.FresherDAO;
import daos.InternDAO;
import entities.*;
import entities.enums.CandidateType;
import entities.enums.GraduationRank;
import mappers.FresherMapper;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{

        List<Candidate> candidates = new ArrayList<>();
        FresherDAO fresherDAO = new FresherDAO();
        ExperienceDAO experienceDAO = new ExperienceDAO();
        InternDAO internDAO = new InternDAO();
        CertificateDAO certificateDAO = new CertificateDAO();
        ExperienceController experienceController = new ExperienceController();
        FresherController fresherController = new FresherController();
        InternController internController = new InternController();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Chọn các chức năng tương ứng :");
            System.out.println("1 : Thêm ứng viên mới ");
            System.out.println("2 : Xóa ");
            System.out.println("3 : Sửa ");
            System.out.println("4 : Show thông tin ứng viên : ");
            System.out.println("5 : Show certificate : ");
            System.out.println("6: Exit");
            int choose = scanner.nextInt();
            scanner.nextLine();
            switch (choose){
                case 1 :
                    System.out.println(" Full Name : ");
                    String name = scanner.nextLine();
                    System.out.println("Email : ");
                    String email = scanner.nextLine();
                    System.out.println("Phone : ");
                    String phone = scanner.nextLine();
                    System.out.println("Birthday : ");
                    String birthday = scanner.nextLine();
                    LocalDate ld = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    System.out.println("Nhap so luong certificate : ");
                    int number = scanner.nextInt();
                    for (int i = 0; i < number; i ++){
                        System.out.println("Chọn loại ứng viên muốn thêm : ");
                        System.out.println("1 : Experience");
                        System.out.println("2 : Fresher");
                        System.out.println("3 : Intern");
                        int type = scanner.nextInt();
                        scanner.nextLine();
                        switch (type){
                            case 1:
                                Experience experience = new Experience();
                                UUID id = UUID.randomUUID();
                                experience.setId(id);
                                experience.setName(name);
                                experience.setEmail(email);
                                experience.setPhone(phone);
                                experience.setBirthDay(ld);
                                System.out.println("Skill : ");
                                experience.setSkill(scanner.nextLine());
                                System.out.println("ExpYear : ");
                                experience.setExpYear(scanner.nextInt());
                                scanner.nextLine();
                                experienceController.save(experience);
                                break;
                            case 2:
                                Fresher fresher = new Fresher();
                                UUID id1 = UUID.randomUUID();
                                fresher.setId(id1);
                                fresher.setName(name);
                                fresher.setEmail(email);
                                fresher.setPhone(phone);
                                fresher.setBirthDay(ld);
                                System.out.println("University : ");
                                fresher.setUniversity(scanner.nextLine());
                                System.out.println("Graduation ");
                                String grad = scanner.nextLine();
                                LocalDate gradTime = LocalDate.parse(grad, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                fresher.setGraduation(gradTime);
                                System.out.println("Choose GraduationRank : ");
                                System.out.println("1 : Excellent ");
                                System.out.println("2 : Good ");
                                System.out.println("3 : Medium ");
                                int rank = scanner.nextInt();
                                switch (rank){
                                    case 1 : fresher.setRank(GraduationRank.EXCELLENT);
                                    case 2 : fresher.setRank(GraduationRank.GOOD);
                                    case 3 : fresher.setRank(GraduationRank.MEDIUM);
                                }
                                fresherController.save(fresher);
                                break;
                            case 3:
                                Intern intern = new Intern();
                                UUID id2 = UUID.randomUUID();
                                intern.setId(id2);
                                intern.setName(name);
                                intern.setEmail(email);
                                intern.setPhone(phone);
                                intern.setBirthDay(ld);
                                System.out.println("Major : ");
                                intern.setMajor(scanner.nextLine());
                                System.out.println("Semester : ");
                                intern.setSemester(scanner.nextInt());
                                scanner.nextLine();
                                System.out.println("University : ");
                                intern.setUniversity(scanner.nextLine());
                                internController.save(intern);
                                break;
                        }
                    }
                    break;

                case 2 :
                    System.out.println("Choose Type of candidate to delete : ");
                    System.out.println("1 : Experience");
                    System.out.println("2 : Fresher");
                    System.out.println("3 : Intern");
                    int Typechoose = scanner.nextInt();
                    scanner.nextLine();
                    if( Typechoose == 1 ){
                        System.out.println("Enter Email :");
                        experienceDAO.deleteByEmail(scanner.nextLine());
                    } else if (Typechoose == 2){
                        System.out.println("Enter Email :");
                        fresherDAO.deleteByEmail(scanner.nextLine());
                    } else {
                        System.out.println("Enter Email :");
                        internDAO.deleteByEmail(scanner.nextLine());
                    }
                    break;

                case 3 :
                    scanner.nextLine();
                    Intern intern = new Intern();
                    Experience experience = new Experience();
                    System.out.println("Nhập id ứng viên muốn sửa : ");
                    String id = scanner.nextLine();
                    Fresher fresher = fresherDAO.findById(id);
                    if (fresher == null){
                        System.out.println("Id is null ! ");
                        return;
                    }
                    FresherDAO fresherDAO1 = new FresherDAO();
                    System.out.println("Nhap graduation moi");
                    String grad = scanner.nextLine();
                    LocalDate gradTime = LocalDate.parse(grad, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    fresher.setGraduation(gradTime);
                    scanner.nextLine();
                    System.out.println("Nhap university moi ");
                    fresher.setUniversity(scanner.nextLine());
                    System.out.println("Nhap name moi : ");
                    fresher.setName(scanner.nextLine());
                    System.out.println("Nhap phone moi : ");
                    fresher.setPhone(scanner.nextLine());
                    System.out.println("Nhap Email moi : ");
                    fresher.setEmail(scanner.nextLine());
                    System.out.println("Nhap GraduationRank moi : ");
                    System.out.println("1 : Excellent ");
                    System.out.println("2 : Good ");
                    System.out.println("3 : Medium ");
                    int rank = scanner.nextInt();
                    switch (rank){
                        case 1 : fresher.setRank(GraduationRank.EXCELLENT);
                        case 2 : fresher.setRank(GraduationRank.GOOD);
                        case 3 : fresher.setRank(GraduationRank.MEDIUM);
                    }
                    scanner.nextLine();
                    System.out.println("Nhap birthday moi : ");
                    birthday = scanner.nextLine();
                    LocalDate Bld = LocalDate.parse( birthday,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    fresher.setBirthDay(Bld);
                    fresherDAO1.update(fresher);
                    break;
                case 4 :
                    System.out.println("Choose kind of candidate to show : ");
                    System.out.println("1 : intern");
                    System.out.println("2 : fresher");
                    System.out.println("3 : experience");
                    int showInfor =scanner.nextInt();
                    if(showInfor ==1 ){
                        List<String> intern1 = new ArrayList<>();
                        internDAO.findAll(intern1);
                    } else if(showInfor == 2){
                        Fresher fresher1 = new Fresher();
                        fresher1.showMe();
                    }else {
                        Experience experience1 = new Experience();
                        experience1.showMe();
                    }
                    break;
                case 5 : {
                    System.out.println("Nhap name ung vien : ");
                    Certificate certificate = certificateDAO.findbyName(scanner.nextLine());
                    System.out.println("Candidate information" + certificate);
                    break;
                }

                case 6: {
                    System.exit(0);
                }

            }


        }
    }

}

