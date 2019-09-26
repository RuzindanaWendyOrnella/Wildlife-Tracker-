import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
/*import spark.template.handlebars.HandlebarsTemplateEngine;*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
        static int getHerokuAssignedPort() {
            ProcessBuilder processBuilder = new ProcessBuilder();
            if (processBuilder.environment().get("PORT") != null) {
                return Integer.parseInt(processBuilder.environment().get("PORT"));
            }
            return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
        }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        post("/", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String name =request.queryParams("name");
            String ranger =request.queryParams("ranger");
            String health=request.queryParams("health");
            String age=request.queryParams("age");
            String location=request.queryParams("location");
           Animal animal = new Animal(name);
            model.put("animal", animal);
            animal.save();
           EndangeredAnimal endager=new EndangeredAnimal(name,ranger,health,age,location);
            model.put("endager",endager);
            endager.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        get("/list", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal> monster = Animal.all();
            List<EndangeredAnimal> danger =EndangeredAnimal.all();
            model.put("monster", monster);
            model.put("danger", danger);
            return new ModelAndView(model, "answer.hbs");
        }, new HandlebarsTemplateEngine());
        get("/here", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<EndangeredAnimal> danger =EndangeredAnimal.all();
            model.put("danger", danger);
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());
    }

}
