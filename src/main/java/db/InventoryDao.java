package db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        // TODO: Implement the logic to add a vehicle to the inventory
        // create connection and preparedStatement
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO inventory (dealership_id, VIN) VALUES (?, ?)");

        ) {
            // set the parameters
            ps.setInt(1, dealershipId);
            ps.setString(2,vin);

            // execute query
            int rows = ps.executeUpdate();

            // confirm update
            System.out.printf("Rows updated %d\n", rows);

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeVehicleFromInventory(String vin) {
        // TODO: Implement the logic to remove a vehicle from the inventory
        // create connection & prepared Statement
        try( Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM inventory WHERE VIN = ?");
        ) {
            // set parameters
            ps.setString(1, vin);
            // execute prepared Statement
            int rows = ps.executeUpdate();
            // Display the number of rows that were updated
            System.out.printf("Rows deleted %d\n", rows);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
