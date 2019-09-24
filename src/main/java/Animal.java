import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;

public class Animal {
  public  String name;
    private  int id;


    public Animal(String name) {
        this.name = name;

    }



    public String getName() {

        return name;
    }

    public  int getId() {
        return id;
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animal (name) VALUES (:name);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Animal> all() {

        String ww = "select * from animal;";
        try(Connection con = DB.sql2o.open()) {

            return con.createQuery(ww).executeAndFetch(Animal.class);
        }
    }



}