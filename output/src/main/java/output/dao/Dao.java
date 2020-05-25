package output.dao;

import java.util.List;

public interface Dao<E> {

    int count();

    boolean insert(E e);

    boolean insertMany(List<E> e);

    boolean updateManyByName(List<E> e, String column);

    boolean updateManyByAbbreviation(List<E> e, String column);

    boolean updateManyById(List<E> e, String column);

    boolean delete(E e);

    boolean deleteAll();

    E selectByName(String s);

    E selectByAbbreviation(String s);

    E selectById(int i);

    List<E> selectAll();

}