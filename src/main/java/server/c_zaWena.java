package server;

import io.javalin.Javalin;

import java.sql.*;
import java.util.Map;

public class c_zaWena {

    public static void main(String[] args)  {
        Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> {
                    it.anyHost();
                });
            });

            config.routes.post("/sign_up",ctx ->{
                String business_name = ctx.formParam("business_name");
                String service_type = ctx.formParam("service");
                String client_name = ctx.formParam("clientName");
                String client_ID = ctx.formParam("ID");
                Integer amount =  Integer.parseInt(   ctx.formParam("amount"));

                AddBusiness new_business = new AddBusiness(business_name,service_type, client_name,amount, client_ID);
                try{
                    new_business.register_();
                    ctx.json(Map.of("status", "OK"));
                } catch (SQLException e) {
                    ctx.json(Map.of("status", "Failed" ));
                    throw new RuntimeException(e);
                }

            });
            config.routes.post("/confirm_client",ctx ->{
               String client_id = ctx.formParam("ID_Client");

               String business_name =   ctx.formParam("business");;
               Client confirm_client = new Client(business_name);
               String client_exist = confirm_client.clientIdValidate();
               if(client_id.equals(client_exist)){
                   ctx.json(Map.of("Status","OK"));
               } else {
                   ctx.json(Map.of("Status","FAILURE"));
               }

            });
            config.routes.get("/login", ctx ->{
                ctx.render("templates/login.html");
            });
        }).start(5050);
    }

}
