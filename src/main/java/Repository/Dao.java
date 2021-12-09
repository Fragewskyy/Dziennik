package Repository;



public interface Dao<T> {

    String get( int id);

    String  getAll();

    void save(T t);

    void update(int id);

    void delete(int id);
}