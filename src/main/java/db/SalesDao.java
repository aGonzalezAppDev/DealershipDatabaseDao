package db;

import models.SalesContract;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        // TODO: Implement the logic to add a sales contract
        // create connection and preparedStatement
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO sales_contracts (VIN, sale_date, price)\\n\" +\n" +
                    "                     \"VALUES (?, ?, ?)");

        ) {
            // set the parameters
            ps.setString(1, salesContract.getVin());
            ps.setDate(2, Date.valueOf(salesContract.getSaleDate()));
            ps.setDouble(3, salesContract.getPrice());

            // execute query
            int rows = ps.executeUpdate();

            // confirm update
            System.out.printf("Rows updated %d\n", rows);

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

