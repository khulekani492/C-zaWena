package server;

import java.sql.*;

public class AddBusiness {
    private String Business_name;
    private String service_type;
    private String client_name;
    private Integer amount;


    public AddBusiness(String businessName, String service_type, String clientName, Integer amount){
        this.Business_name = businessName;
        this.service_type = service_type;
        this.client_name = clientName;
        this.amount = amount;

    }

    public  String register_() throws SQLException {
        //jdbc connection
        String jdbcUrl = "jdbc:postgresql://aws-0-eu-west-1.pooler.supabase.com:6543/postgres?user=postgres.dklypwwqkhgnqqlzpjrs&password=!4PNfgC-_U6ZjL5";
        String sql = """
                 INSERT INTO m_s_s_clients(business_name,service_type,client_name,client_amount) VALUES (?,?,?,?)\s
                \s""";
        Connection connection = DriverManager.getConnection(jdbcUrl);

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, this.Business_name);
            pstm.setString(2, this.service_type);
            pstm.setString(3, this.client_name);
            pstm.setInt(4,this.amount);

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     return "Success";
    }


     public static void main(String[] args) throws SQLException {

        AddBusiness test_business = new AddBusiness("MKhulex Rental","property","thule",700);



        try {
            test_business.register_();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }


}
