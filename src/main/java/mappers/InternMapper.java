package mappers;

import entities.Intern;
import entities.enums.CandidateType;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class InternMapper {
    public static Intern toEntity(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        Date birthday = resultSet.getDate("birthday");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");
        String internRank = resultSet.getString("internRank");
        String major = resultSet.getString("major");
        int semester = resultSet.getInt("semester");
        String university = resultSet.getString("university");

        Intern intern = new Intern();
        intern.setId(UUID.fromString(id));
        intern.setName(name);
        if (birthday != null) {
            intern.setBirthDay(LocalDate.of(birthday.getYear(), birthday.getMonth(), birthday.getDay()));
        }
        intern.setPhone(phone);
        intern.setEmail(email);
        intern.setMajor(major);
        intern.setSemester(semester);
        //intern.setRank();
        intern.setUniversity(university);
        return intern;
    }
}
