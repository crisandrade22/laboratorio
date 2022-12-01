//package gov.sp.fatec.laboratorio.main;
//import java.sql.*;
//
//public class Teste {
//    public static void main(String[] args) {
//        String url = "jdbc:mysql://172.20.0.3:3306/laboratorio?useTimezone=true&serverTimezone=UTC";
//        String username = "root";
//        String password = "1";
//
//        System.out.println("Connecting database...");
//        Connection con = null;
//        PreparedStatement p = null;
//        ResultSet rs = null;
//
//        try (Connection connection = DriverManager.getConnection(url, username, password)) {
//            String sql = "select * from produtos";
//            p = connection.prepareStatement(sql);
//            rs = p.executeQuery();
//
//            while(rs.next()) {
//                String nome = rs.getString("nome");
//                System.out.println("Nome: " + nome);
//            }
//            System.out.println("Database connected!");
//        } catch (SQLException e) {
//            throw new IllegalStateException("Cannot connect the database!", e);
//        }
//    }
//}
