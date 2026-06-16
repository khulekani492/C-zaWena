package server;

import io.javalin.Javalin;

public class c_zaWena {
    void main() {
        Javalin.create(config -> {

            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> {
                    it.anyHost();
                });
            });

            config.routes.get("/",ctx ->{
                ctx.result("Hello: " );
//                ctx.render("templates/index.html");
            });
            config.routes.get("/login", ctx ->{
                ctx.render("templates/login.html");
            });
        }).start(5050);
    }

}
