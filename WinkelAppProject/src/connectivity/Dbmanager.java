package connectivity;

import java.sql.*;



public class Dbmanager {
    public static final String JDBC_EXCEPTION = "JDBC Exception: ";
    public static final String SQL_EXCEPTION = "SQL Exception: ";

    public Connection connection;

    /**
     * Open database connection
     */
    public void openConnection() {
                    
            //online database
            String url = "jdbc:mysql://192.169.52.228:3306/vernondg_winkelApp2012";
            String user = "vernondg_goedev", pass = "geheim123";
            
            //locale database
            String localUser = "root";
            String localPass = "";
            String localUrl = "jdbc:mysql://localhost/vernondg_winkelApp2012";
            
        try {
            Class.forName("com.mysql.jdbc.Driver");

            /** Open connection met online database */
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            System.err.println(JDBC_EXCEPTION + e);
        } catch (java.sql.SQLException e) {
            //als er geen internetverbinding is, probeer dan nog een keer via local database
            if (e.getMessage().contains("Communications link failure")){
                System.out.println("Can't connect, falling back on localhost.");
                try {
                    connection = DriverManager.getConnection(localUrl, localUser, localPass);
                } catch (SQLException ex) {
                    System.err.println(SQL_EXCEPTION + e);
                }
            }
            else{
                System.err.println(SQL_EXCEPTION + e);
            }
        }
        }
    

    /**
     * Close database connection
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Executes a query without result.
     * @param query, the SQl query
     */
    public void executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
    }

    /**
     * Executes a query with result.
     * @param query, the SQL query
     */
    public ResultSet doQuery(String query) {
        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
        return result;
    }
    
    /**
     * Executes a query with result.
     * @param query, the SQL query
     */
    public ResultSet insertQuery(String query) {
        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            result = statement.getGeneratedKeys();
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
        return result;
    }

}
