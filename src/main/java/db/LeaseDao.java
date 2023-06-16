package db;

import models.LeaseContract;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;

    }



    public void addLeaseContract(LeaseContract leaseContract) {
        // TODO: Implement the logic to add a lease contract

        // create connection and prepared statement
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO lease_contracts (VIN, lease_start, lease_end, monthly_payment)\n" +
                    "VALUES (?, ?, ?, ?)")

        ){
            // set the parameter
            ps.setString(1, leaseContract.getVin());
            ps.setDate(2, Date.valueOf(leaseContract.getLeaseStart()));
            ps.setDate(3, Date.valueOf(leaseContract.getLeaseEnd()));
            ps.setDouble(4, leaseContract.getMonthlyPayment());

            // execute the query
            int rows = ps.executeUpdate();
            // confirm the update
            System.out.printf("Rows updated %d\n", rows);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
