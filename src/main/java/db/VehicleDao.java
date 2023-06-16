package db;

import models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        // TODO: Implement the logic to add a vehicle
        // create connection and preparedStatement
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO vehicles (VIN, make, model, year, SOLD, color, " +
                    "vehicleType, odometer, price)\\n\" +\n" +
                    "                     \"VALUES (?,?,?,?,?,?,?,?,?");

        ) {
            // set the parameters
            ps.setString(1, vehicle.getVin());
            ps.setString(2, vehicle.getMake());
            ps.setString(3, vehicle.getModel());
            ps.setInt(4, vehicle.getYear());
            ps.setBoolean(5, false);
            ps.setString(6, vehicle.getColor());
            ps.setString(7, vehicle.getVehicleType());
            ps.setInt(8, vehicle.getOdometer());
            ps.setDouble(9, vehicle.getPrice());


            // execute query
            int rows = ps.executeUpdate();

            // confirm update
            System.out.printf("Rows updated %d\n", rows);

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeVehicle(String VIN) {
        // TODO: Implement the logic to remove a vehicle
        // create connection & prepared Statement
        try( Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM vehicles WHERE VIN = ?");
        ) {
            // set parameters
            ps.setString(1, VIN);
            // execute prepared Statement
            int rows = ps.executeUpdate();
            // Display the number of rows that were updated
            System.out.printf("Rows deleted %d\n", rows);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        // TODO: Implement the logic to search vehicles by price range
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM vehicles WHERE price BETWEEN ? AND ?");
        ){
            // set parameters
            ps.setDouble(1, minPrice);
            ps.setDouble(2, maxPrice);

            // use method provided to reduce repeated code and execute query
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Vehicle vehicle = createVehicleFromResultSet(rs);
                    vehicles.add(vehicle);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        // return arraylist
        return vehicles;
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        // TODO: Implement the logic to search vehicles by make and model
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM vehicles WHERE make = ? AND model =?");
        ){
            // set parameters
            ps.setString(1, make);
            ps.setString(2, model);

            // use method provided to reduce repeated code and execute query
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Vehicle vehicle = createVehicleFromResultSet(rs);
                    vehicles.add(vehicle);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        // TODO: Implement the logic to search vehicles by year range
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM vehicles WHERE year BETWEEN ? AND ?");
        ){
            // set parameters
            ps.setInt(1, minYear);
            ps.setInt(2, maxYear);

            // use method provided to reduce repeated code and execute query
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Vehicle vehicle = createVehicleFromResultSet(rs);
                    vehicles.add(vehicle);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByColor(String color) {
        // TODO: Implement the logic to search vehicles by color
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM vehicles WHERE color = ?");
        ){
            // set parameters
            ps.setString(1, color);

            // use method provided to reduce repeated code and execute query
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Vehicle vehicle = createVehicleFromResultSet(rs);
                    vehicles.add(vehicle);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        // TODO: Implement the logic to search vehicles by mileage range
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?");
        ){
            // set parameters
            ps.setInt(1, minMileage);
            ps.setInt(2, maxMileage);

            // use method provided to reduce repeated code and execute query
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Vehicle vehicle = createVehicleFromResultSet(rs);
                    vehicles.add(vehicle);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByType(String type) {
        // TODO: Implement the logic to search vehicles by type
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM vehicles WHERE vehicleType = ?");
        ){
            // set parameters
            ps.setString(1, type);

            // use method provided to reduce repeated code and execute query
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Vehicle vehicle = createVehicleFromResultSet(rs);
                    vehicles.add(vehicle);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
