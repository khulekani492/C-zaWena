package server;

import io.javalin.Javalin;

import java.sql.*;
import java.util.Map;

public class c_zaWena {

    void main() {
        Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> {
                    it.anyHost();
                });
            });

            config.routes.get("/sign_up",ctx ->{
                String business_name = ctx.formParam("name");
                String service_type = ctx.formParam("service");
                String client_name = ctx.formParam("clientName");
                Integer amount =  Integer.parseInt(   ctx.formParam("amount"));

                AddBusiness new_business = new AddBusiness(business_name,service_type, client_name);
                try{
                    new_business.register_();
                    ctx.json(Map.of("status", "OK"));
                } catch (SQLException e) {
                    ctx.json(Map.of("status", "Failed" ));
                    throw new RuntimeException(e);
                }

//                ctx.render("templates/index.html");
            });
            config.routes.get("/login", ctx ->{
                ctx.render("templates/login.html");
            });
        }).start(5050);
    }

}
