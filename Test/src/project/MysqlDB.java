package project;
import java.sql.*;

public class MysqlDB {
    public static Connection connection;
    public static Statement statement;
    
    public static void showResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData meta = resultSet.getMetaData();
        String res = "";
//        StringBuffer result = new StringBuffer();
        int colCount = meta.getColumnCount();
        for(int i=1; i<=colCount; i++)
            res += meta.getColumnLabel(i) + ", ";
//            result.append(meta.getColumnLabel(i)+"\t");
        res += "\n";
//        result.append("\n");
        while(resultSet.next()) {
            for(int i=1; i<=colCount; i++)
                res += resultSet.getObject(i).toString() + ", ";
//                result.append(resultSet.getObject(i)+"\t");
            res += "\n";
//            result.append("\n");
        }
        System.out.println(res);
//        System.out.println(result);
    }
    
    public static void connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://195.178.227.53:3306/test","test","testpw");
            statement = connection.createStatement();
        } catch(ClassNotFoundException e1) {
            System.out.println("Databas-driver hittades ej: "+e1);
        }
    }
    
    public static void disconnect() throws SQLException {
        statement.close();
        connection.close();
    }
    
    public static void main(String[] args) {
        try {
            connect();
            
            ResultSet result = statement.executeQuery("SELECT * FROM Person");
            showResultSet(result);
            
            disconnect();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
}