package server;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Client {
    private String id;
    private String reference;
    private String client_name;
    private String client_surname;
    private Integer number;
    private  String business_name;
    private Integer businessId;

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


    public Client(Integer id){
        this.businessId = id;

    }

    public    HashMap<Integer,HashMap<String,String>>  getbusinessClients() throws SQLException {
        String sql = "SELECT * FROM clients_ WHERE business_service = ?";
        String jdbcUrl = "jdbc:postgresql://aws-0-eu-west-1.pooler.supabase.com:6543/postgres?user=postgres.dklypwwqkhgnqqlzpjrs&password=!4PNfgC-_U6ZjL5";
        Connection conn = DriverManager.getConnection(jdbcUrl);

        HashMap<String,String> business_client =  new HashMap<>();
        HashMap<Integer,HashMap<String,String>> fetch_all = new HashMap<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,this.businessId);
            ResultSet execute  =  pstm.executeQuery();

            Integer counter = 0;
            while (execute.next() ){
                String client_number = execute.getString("client_number");
                business_client.put("client_name",execute.getString("client_name") );
                business_client.put("client_surname",execute.getString("client_surname") );
                business_client.put("client_number",client_number);
                business_client.put("client_id",  execute.getString("client_id"));
//                System.out.println("current Block " + business_client);
                fetch_all.put(counter,business_client);
                counter ++;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return fetch_all;
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

    public static void main(String[] args) throws SQLException {

        Client test_ = new Client(12);
        System.out.println(test_.getbusinessClients().get(3).get("client_name"));
    }

}
