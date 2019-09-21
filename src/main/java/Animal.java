import org.sql2o.Connection;

import java.util.List;

public class Animal {
    private static String name;
    private int id;

    public Animal(String name) {
        this.name = name;

    }

    public static String getName() {
        return name;
    }


    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animal (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Animal> all() {
        String sql = "SELECT * FROM  animal ";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }
}