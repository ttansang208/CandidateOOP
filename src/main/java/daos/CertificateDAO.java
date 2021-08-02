package daos;

import entities.Certificate;
import mappers.CertificateMapper;
import mappers.ExperienceMapper;
import utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CertificateDAO implements ICertificateDAO {

    @Override
    public Certificate findById(String id) {
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM certificate WHERE id = ?" );
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            // chac chan chi co 1 dong
            boolean check = resultSet.next();
            if (!check){
                return  null;
            }
            return CertificateMapper.toEntity(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Certificate entity,int type) {

        if(checkExits(entity.getName())){
            if(type == 1){
                update(entity.getName(),entity.getExperience_id().toString(),1);
            }else if(type == 2){
                update(entity.getName(),entity.getFresher_id().toString(),2);
            }else{
                update(entity.getName(),entity.getIntern_id().toString(),3);
            }
        }else{
            create(entity);
        }
    }

    @Override
    public Certificate findbyName(String name) {
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM certificate WHERE name = ?" );
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            boolean check = resultSet.next();
            if (!check){
                return null;
            }
            return CertificateMapper.toEntity(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void update(String email,String id,int type){
        String sql ="UPDATE certificate SET";
        if(type == 1){
           sql = sql + " experience_id = ? WHERE name = ?";
        }else if(type == 2){
            sql = sql + " fresher_id = ? WHERE name = ?";
        }else{
            sql = sql + " intern_id = ? WHERE name = ?";
        }
        try {
            Connection connection = ConnectionUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, email);
            statement.executeUpdate();
        }catch ( Exception e){
            e.printStackTrace();
        }

    }
    private boolean checkExits(String email){
        String sql ="SELECT * FROM certificate WHERE name = ?";
        try {
            Connection connection = ConnectionUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if(result.next()) return true;
            else return false;
        }catch ( Exception e){
            e.printStackTrace();
            return false;
        }
    }
    private void create(Certificate entity){
        String sql = "INSERT INTO `candidate`.`certificate` " +
                "(`id`," +
                "`name`," +
                "`experience_id` ," +
                "`fresher_id` ," +
                "`intern_id`)" +
                "VALUES (" +
                "?," +
                "?," +
                "?," +
                "?," +
                "?);";
        try {
            Connection connection = ConnectionUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getId().toString());
            statement.setString(2, entity.getName());
            if(entity.getExperience_id() != null){
                statement.setString(3,entity.getExperience_id().toString());
            }
            else{
                statement.setNull(3,Types.VARCHAR);
            }
            if(entity.getFresher_id() != null){
                statement.setString(4,entity.getFresher_id().toString());
            }
            else{
                statement.setNull(4,Types.VARCHAR);
            }
            if(entity.getIntern_id() != null){
                statement.setString(5,entity.getIntern_id().toString());
            }
            else{
                statement.setNull(5,Types.VARCHAR);
            }

            statement.executeUpdate();
        }catch ( Exception e){
            e.printStackTrace();
        }
    }

}
