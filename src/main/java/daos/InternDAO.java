package daos;

import entities.Certificate;
import entities.Intern;
import mappers.InternMapper;
import utils.ConnectionUtils;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class InternDAO implements CrudDAO<Intern, String> {

    @Override
    public void save(Intern entity) {
        String insert = "INSERT INTO `candidate`.`intern`" +
                "(`id`," +
                "`name`," +
                "`phone`," +
                "`email`," +
                "`major`," +
                "`university`," +
                "`birthday`," +
                "`semester`)" +
                "VALUES (" +
                "?," +
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
            statement.setString(3, entity.getPhone());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getMajor());
            statement.setString(6, entity.getUniversity());
            statement.setDate(7, Date.valueOf(entity.getBirthDay()));
            statement.setInt(8, entity.getSemester());
            int result = statement.executeUpdate();
            if (result > 0) {
                Certificate certificate = new Certificate();
                certificate.setId(UUID.randomUUID());
                certificate.setName(entity.getEmail());
                certificate.setIntern_id(entity.getId());
                CertificateDAO certificateDAO = new CertificateDAO();
                certificateDAO.save(certificate,3);
            } else {
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Intern findById(String s) {
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM intern WHERE id = ?");
            statement.setString(1, s);
            ResultSet resultSet = statement.executeQuery();
            // chac chan chi co 1 dong
            boolean check = resultSet.next();
            if (!check) {
                return null;
            }
            return InternMapper.toEntity(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<Intern> findAll(List<String> strings) {
        List<Intern> interns = new ArrayList<>();
        Map<String, Intern> map = new HashMap<>();
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM intern ");
            statement.setString(1, String.valueOf(strings));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                if (map.get(email) == null) {
                    map.put(email, InternMapper.toEntity(resultSet));
                }
                interns.add(InternMapper.toEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return interns;
    }

    @Override
    public boolean deleteById(String s) {
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM intern WHERE id = ?");
            statement.setString(1, "name");
            int resultSet = statement.executeUpdate();
            System.out.println("Delete success !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByEmail(String email) {
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM fresher WHERE email = ?");
            statement.setString(1, email);
            int resultSet = statement.executeUpdate();
            if (resultSet > 0) {
                System.out.println("Delete Success !");
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Intern entity) {
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE intern SET" +
                            "`name`= ?, " +
                            "`phone` = ?, " +
                            "`email` = ? " +
                            "`major` = ? " +
                            "`university` = ? " +
                            "`birthday` = ? " +
                            "`semester` = ? " +
                            "WHERE id = ?");
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getPhone());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getMajor());
            statement.setString(5, entity.getUniversity());
            statement.setDate(6, Date.valueOf(entity.getBirthDay()));
            statement.setInt(7, entity.getSemester());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Intern findByEmail(String email) {
        try (Connection connection = ConnectionUtils.getConnection()) {
            Intern intern = new Intern();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM intern WHERE email = ?");
            statement.setString(1, intern.getEmail());
            ResultSet resultSet = statement.executeQuery();
            boolean check = resultSet.next();
            if (!check) {
                return intern;
            }
            return InternMapper.toEntity(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
