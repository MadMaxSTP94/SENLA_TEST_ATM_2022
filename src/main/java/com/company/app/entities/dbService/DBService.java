package main.java.com.company.app.entities.dbService;

public interface DBService {
    Object find(Object obj);
    void insert(Object obj);
    void remove(Object obj);
    void save();

}
