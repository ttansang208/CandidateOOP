package mappers;

import entities.Candidate;
import entities.Certificate;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class CertificateMapper {
    public  static Certificate toEntity(ResultSet resultSet) throws SQLException {

        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        String experience_id = resultSet.getString("experience_id");
        String fresher_id = resultSet.getString("fresher_id");
        String intern_id = resultSet.getString("intern_id");

        Certificate certificate = new Certificate();
        certificate.setId(UUID.fromString(id));
        certificate.setName(name);
        certificate.setExperience_id(UUID.fromString(experience_id));
        certificate.setFresher_id(UUID.fromString(fresher_id));
        certificate.setIntern_id(UUID.fromString(intern_id));
        return certificate;
    }
}
