package Repository;




import java.sql.SQLException;
import java.util.ArrayList;

public interface Dao<T> {

    String get(int id) throws SQLException;

    ArrayList<T> getAll() throws SQLException;

    void update(int id) throws SQLException;

    void delete(int id) throws SQLException;
}