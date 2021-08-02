package mappers;

import entities.Fresher;
import entities.enums.CandidateType;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class FresherMapper {

    public static Fresher toEntity(ResultSet resultSet) throws SQLException {

        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        Date birthday = resultSet.getDate("birthday");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");
        Date gradutation = resultSet.getDate("graduation");
        String rank = resultSet.getString("graduationrank");
        String university = resultSet.getString("university");

        Fresher fresher = new Fresher();
        fresher.setId(UUID.fromString(id));
        fresher.setName(name);
        if (birthday != null) {
            fresher.setBirthDay(LocalDate.of(birthday.getYear(), birthday.getMonth(), birthday.getDay()));
        }
        fresher.setPhone(phone);
        fresher.setEmail(email);
        if (gradutation != null) {
            fresher.setGraduation(LocalDate.of(gradutation.getYear(), gradutation.getMonth(), gradutation.getDay()));
        }
        //fresher.setRank();
        fresher.setUniversity(university);
        return fresher;
    }
}
