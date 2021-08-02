package daos;

import entities.Certificate;
import entities.Experience;
import entities.Intern;
import mappers.ExperienceMapper;
import mappers.InternMapper;
import utils.ConnectionUtils;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

public class ExperienceDAO implements CrudDAO<Experience, String> {

    @Override
    public void save(Experience entity) {
        String insert = "INSERT INTO `candidate`.`experience`" +
                "(`id`," +
                "`name`," +
                "`expYear`," +
                "`skill`," +
                "`phone`," +
                "`email`," +
                "`birthday`)" +
                "VALUES (" +
                "?," +
                "?," +
                "?," +
                "?," +
                "?," +
                "?," +
                "?);";

        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1, entity.getId().toString());
            statement.setString(2, entity.getName());
            statement.setInt(3, entity.getExpYear());
            statement.setString(4, entity.getSkill());
            statement.setString(5, entity.getPhone());
            statement.setString(6, entity.getEmail());
            statement.setDate(7, Date.valueOf(entity.getBirthDay()));
            int result = statement.executeUpdate();
            if (result > 1){
                Certificate certificate = new Certificate();
                certificate.setId(UUID.randomUUID());
                certificate.setName(entity.getEmail());
                certificate.setExperience_id(entity.getId());
                CertificateDAO certificateDAO = new CertificateDAO();
                certificateDAO.save(certificate,1);
            } else {
                return ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Experience findById(String s) {
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM experience WHERE id = ?" );
            statement.setString(1, s);
            ResultSet resultSet = statement.executeQuery();
            // chac chan chi co 1 dong
            boolean check = resultSet.next();
            if (!check){
                return  null;
            }
            return ExperienceMapper.toEntity(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Experience> findAll(List<String> strings){
        List<Experience> experiences = new ArrayList<>();
        Map<String, Experience> map = new HashMap<>();
        try (Connection connection = ConnectionUtils.getConnection()) {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM experience");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            if( map.get(email) == null ){
                map.put(email, ExperienceMapper.toEntity(resultSet));
            }
            experiences.add(ExperienceMapper.toEntity(resultSet));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return experiences;
    }

    @Override
    public boolean deleteById(String s) {
        try (Connection connection = ConnectionUtils.getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM experience WHERE id = ?");
            ResultSet resultSet = statement.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByEmail(String email) {
        try (Connection connection = ConnectionUtils.getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM experience WHERE email = ?");
            statement.setString(1, email);
            int resultSet = statement.executeUpdate();
            System.out.println("Delete Success !");
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Experience entity) {
        try( Connection connection  = ConnectionUtils.getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE experience SET" +
                            "(`id`," +
                            "`name`," +
                            "`expYear`," +
                            "`skill`," +
                            "`phone`," +
                            "`email`," +
                            "`birthday`)" +
                            "WHERE id = ?");
            statement.setString(1, entity.getId().toString());
            statement.setString(2, entity.getName());
            statement.setInt(3, entity.getExpYear());
            statement.setString(4, entity.getSkill());
            statement.setString(5, entity.getPhone());
            statement.setString(6, entity.getEmail());
            statement.setDate(7, Date.valueOf(entity.getBirthDay()));
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Experience findByEmail(String email) {
        try (Connection connection = ConnectionUtils.getConnection()) {
            Experience experience = new Experience();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM experience WHERE email = ?" );
            statement.setString(1, experience.getEmail());
            ResultSet resultSet = statement.executeQuery();
            boolean check = resultSet.next();
            if (!check){
                return null ;
            }
            return ExperienceMapper.toEntity(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
