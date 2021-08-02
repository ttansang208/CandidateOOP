package daos;

import entities.Certificate;
import entities.Fresher;
import entities.Intern;
import entities.enums.GraduationRank;
import mappers.FresherMapper;
import mappers.InternMapper;
import org.apache.commons.lang3.StringUtils;
import utils.ConnectionUtils;

import java.nio.channels.ScatteringByteChannel;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class FresherDAO implements CrudDAO<Fresher, String>, IFresherDAO {

    @Override
    public void save(Fresher entity) {
        String insert = "INSERT INTO `candidate`.`fresher`" +
                "(`id`," +
                "`name`," +
                "`birthday`," +
                "`phone`," +
                "`email`," +
                "`graduation`," +
                "`graduationrank`," +
                "`university`)" +
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
            statement.setDate(3, Date.valueOf(entity.getBirthDay()));
            statement.setString(4, entity.getPhone());
            statement.setString(5, entity.getEmail());
            statement.setDate(6, Date.valueOf(entity.getGraduation()));
            //check xem rank null or not ?
            statement.setString(7, entity.getRank() != null ? entity.getRank().toString() : StringUtils.EMPTY);
            statement.setString(8, entity.getUniversity());
            //statement.executeUpdate();
            int result = statement.executeUpdate();
            if (result > 0) {
                Certificate certificate = new Certificate();
                certificate.setId(UUID.randomUUID());
                certificate.setName(entity.getEmail());
                certificate.setFresher_id(entity.getId());
                CertificateDAO certificateDAO = new CertificateDAO();
                certificateDAO.save(certificate,2);
            } else {
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Fresher findById(String id) {
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM fresher WHERE id = ?");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            boolean check = resultSet.next();
            if (!check) {
                return null;
            }
            return FresherMapper.toEntity(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Fresher> findAll(List<String> strings) {
        List<Fresher> freshers = new ArrayList<>();
        Map<String, Fresher> map = new HashMap<>();
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM fresher");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                if (map.get(email) == null) {
                    map.put(email, FresherMapper.toEntity(resultSet));
                }
                freshers.add(FresherMapper.toEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return freshers;
    }

    @Override
    public boolean deleteById(String s) {
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM fresher WHERE id = ?");
            ResultSet resultSet = statement.executeQuery();
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
            } else {
                System.out.println("Error ! Cannot delete Fresher.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Fresher entity) {
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE fresher SET" +
                            "`graduation`= ?, " +
                            "`university` = ?, " +
                            "`name`= ?, " +
                            "`phone` = ?, " +
                            "`email` = ?, " +
                            "`graduationRank` = ? ," +
                            "`birthday` = ? " +
                            "WHERE id = ?");
            statement.setDate(1, Date.valueOf(entity.getGraduation()));
            statement.setString(2, entity.getUniversity());
            statement.setString(3, entity.getName());
            statement.setString(4, entity.getPhone());
            statement.setString(5, entity.getEmail());
            statement.setString(6, entity.getRank() != null
                    ? entity.getRank().toString() : StringUtils.EMPTY);
            statement.setDate(7, Date.valueOf(entity.getBirthDay()));
            statement.setString(8, entity.getId().toString());
            int update = statement.executeUpdate();
            if (update > 0) {
                System.out.println("Update success");
            } else {
                System.out.println("Error !");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Fresher findByEmail(String email) {
        try (Connection connection = ConnectionUtils.getConnection()) {
            Fresher fresher = new Fresher();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM fresher WHERE email = ?");
            statement.setString(1, fresher.getEmail());
            ResultSet resultSet = statement.executeQuery();
            boolean check = resultSet.next();
            if (!check) {
                return fresher;
            }
            return FresherMapper.toEntity(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Fresher> findByRank(GraduationRank rank) {
        try (Connection connection = ConnectionUtils.getConnection()) {
            List<Fresher> fresher = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM fresher WHERE graduationrank = ?");
            statement.setString(1, rank.toString());
            ResultSet resultSet = statement.executeQuery();
            // chac chan chi co 1 dong
            boolean check = resultSet.next();
            if (!check) {
                return fresher;
            }
            return (List<Fresher>) FresherMapper.toEntity(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
