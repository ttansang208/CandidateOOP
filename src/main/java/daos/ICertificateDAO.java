package daos;

public interface ICertificateDAO <T, Certificate > {
    entities.Certificate findById(String id );
    void save (entities.Certificate entity,int type);
    entities.Certificate findbyName(String name);

}
