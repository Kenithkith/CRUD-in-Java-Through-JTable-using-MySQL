package Engine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class TestDBWorker {

    private final String URL = "jdbc:mysql://localhost:3306/crud_test";
    private final String userName = "root";
    private final String password = "root";

    public Boolean addData(String testName, String testAge, String testEmail) {
       
        String sql = "INSERT INTO crud_test_table(testName, testAge, testEmail) VALUES('" + testName + "','" + testAge + "','" + testEmail + "')";
        try(Connection connection = DriverManager.getConnection(URL, userName, password); Statement statement = connection.prepareStatement(sql)) {
            statement.execute(sql);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public DefaultTableModel getData() {
        
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("testID");
        defaultTableModel.addColumn("testName");
        defaultTableModel.addColumn("testAge");
        defaultTableModel.addColumn("testEmail");
       
        String sql = "SELECT * FROM crud_test_table";
        try(Connection connection = DriverManager.getConnection(URL, userName, password);Statement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
               
                String testID = resultSet.getString("testID");
                String testName = resultSet.getString("testName");
                String testAge = resultSet.getString("testAge");
                String testEmail = resultSet.getString("testEmail");
                defaultTableModel.addRow(new String[]{testID, testName, testAge, testEmail});
            }
            return defaultTableModel;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


  
    public Boolean updateData(String testID, String testName, String testAge, String testEmail) {
       
        String sql = "UPDATE crud_test_table SET testName ='" + testName+  "',testAge ='" + testAge + "',testEmail ='" + testEmail + "' WHERE testID ='" + testID + "'";
        try(Connection connection = DriverManager.getConnection(URL, userName, password); Statement statement = connection.prepareStatement(sql)) {
            statement.execute(sql);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public Boolean deleteData(String testID){

        String sql = "DELETE FROM crud_test_table WHERE testID ='" + testID + "'";


        try(Connection connection = DriverManager.getConnection(URL, userName, password); Statement statement = connection.prepareStatement(sql)){
            statement.execute(sql);
            return true;

        }catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }


}

