import org.sql2o.Connection;

import java.util.List;

public class EndangeredAnimal {
    private String name;
    private String health;
    private int age;
    private int id;

    public EndangeredAnimal(String name, String health, int age) {
        this.name = name;
        this.health = health;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO endangeredanimal (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }

    }
    public static List<EndangeredAnimal> all() {
        String sql = "SELECT * FROM endangeredanimal";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(EndangeredAnimal.class);
        }
    }
}
