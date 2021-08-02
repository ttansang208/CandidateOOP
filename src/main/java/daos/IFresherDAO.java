package daos;

import entities.Fresher;
import entities.enums.GraduationRank;

import java.util.List;

public interface IFresherDAO<T> {

    List<Fresher> findByRank(GraduationRank rank);

}
