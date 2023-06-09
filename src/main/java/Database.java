import feature.prefs.Prefs;

import java.sql.*;

public class Database {
    private static final Database INSTANCE = new Database();

    private Connection connection;
    private Database() {
        try {
            String connectionUrl = new Prefs().getString(Prefs.DB_JDBC_CONNECTION_URL);
            connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Database getInstance()
    {
        return INSTANCE;
    }

    public int executeUpdate(String sql){
        try(Statement st = connection.createStatement()){
            return st.executeUpdate(sql);
        }catch(Exception ex){
            ex.printStackTrace();

            return -1;
        }
    }
    public ResultSet executeQuery(String sql){
        try(Statement st = connection.createStatement()){
            return st.executeQuery(sql);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public Connection getConnection(){
        return connection;
    }

    public void close(){
        try{
            connection.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
