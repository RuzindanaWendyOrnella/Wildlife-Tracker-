import org.sql2o.Connection;

import java.util.List;

public class EndangeredAnimal {
    private String name;
    private String health;
    private String age;
    private int id;

    public EndangeredAnimal(String name, String health,String age) {
        this.name = name;
        this.health = health;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public String getHealth() {
        return health;
    }
    public String getAge() {
        return age;
    }
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO endangeredanimal (name,health,age) VALUES (:name,:health,:age)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
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
