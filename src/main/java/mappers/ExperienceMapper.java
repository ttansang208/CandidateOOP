package mappers;

import entities.Experience;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class ExperienceMapper {

    public static Experience toEntity(ResultSet resultSet) throws SQLException {

        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        Date birthday = resultSet.getDate("birthday");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");
        int expYear = resultSet.getInt("expYear");
        String skill = resultSet.getString("skill");

        Experience experience = new Experience();
        experience.setId(UUID.fromString(id));
        experience.setName(name);
        if((birthday) != null ){
            experience.setBirthDay(LocalDate.of(birthday.getYear(),birthday.getMonth(),birthday.getDay()));
        }
        experience.setPhone(phone);
        experience.setEmail(email);
        experience.setExpYear(expYear);
        experience.setSkill(skill);
        return experience;
    }
}
