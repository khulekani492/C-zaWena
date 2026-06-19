package server;

import javax.xml.transform.Result;
import java.sql.*;

public class Client {
    private String id;
    private String reference;
    private String client_name;
    private String client_surname;
    private Integer number;
    private  String business_name;

    public Client(String id,String reference, String client_name, String client_surname, Integer number){
        this.id = id;
        this.reference = reference;
        this.client_name = client_name;
        this.client_surname = client_surname;
        this.number = number;
    }

    public Client(String business_name){
        this.business_name = business_name;

    }

    public  String clientIdValidate() throws SQLException {
     String sql = "SELECT client_id FROM m_s_s_clients WHERE business_name = ?";
     String jdbcUrl = "jdbc:postgresql://aws-0-eu-west-1.pooler.supabase.com:6543/postgres?user=postgres.dklypwwqkhgnqqlzpjrs&password=!4PNfgC-_U6ZjL5";
     Connection conn = DriverManager.getConnection(jdbcUrl);

     try {
         PreparedStatement pstm = conn.prepareStatement(sql);
         pstm.setString(1,this.business_name);
         ResultSet execute  =  pstm.executeQuery();

         if(execute.next() ){
             String id = execute.getString("client_id");
             return id;
         }



     } catch (SQLException e) {
         throw new RuntimeException(e);
     }

     return null;
    }


}
