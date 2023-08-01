package Appliers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Middleware.AbsCrud;
import conn.JdbcHandler;

public class Crud extends AbsCrud {

    public void display() {
        try (Connection connection = JdbcHandler.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM CarDetail");
             ResultSet resultSet = ps.executeQuery()) {
            System.out.println("ID\tCar Name\tCar Type\tPrice\t\tAvailability");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String carName = resultSet.getString("carname");
                String carType = resultSet.getString("cartype");
                String price = resultSet.getString("price");
                String availability = resultSet.getString("availability");
                System.out.println(id + "\t" + carName + "\t\t" + carType + "\t\t" + price + "\t\t" + availability);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getCarName(int carId) {
        String carName = null;
        try (Connection connection = JdbcHandler.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT carname FROM CarDetail WHERE id = ?")) {
            ps.setInt(1, carId);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    carName = resultSet.getString("carname");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carName;
    }

    public void markCarAsNA(int carId) {
        String carName = getCarName(carId);
        if (carName != null) {
            String availability = getCarAvailability(carName); 
            if ("available".equalsIgnoreCase(availability)) { 
                try (Connection connection = JdbcHandler.getConnection();
                     PreparedStatement ps = connection.prepareStatement("UPDATE CarDetail SET availability = 'n.a' WHERE id = ?")) {
                    ps.setInt(1, carId);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Car with ID " + carId + " is not available for order. RESTART");
            }
        } else {
            System.out.println("Car with ID " + carId + " not found.RESTART");
        }
    }

    private String getCarAvailability(String carName) {
        String availability = null;
        try (Connection connection = JdbcHandler.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT availability FROM CarDetail WHERE carname = ?")) {
            ps.setString(1, carName);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    availability = resultSet.getString("availability");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availability;
    }

    public void storeOrder(String customerName, String carName, int hours) {
        try (Connection connection = JdbcHandler.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO Orders (name, carname, hours) VALUES (?, ?, ?)")) {
            ps.setString(1, customerName);
            ps.setString(2, carName);
            ps.setInt(3, hours);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void markCarAsNAAndStoreOrder(int carId, String customerName, String carName, int hours) {
        markCarAsNA(carId);
        storeOrder(customerName, carName, hours);
    }

	@Override
	public void delete(int carId) {
		try (Connection connection = JdbcHandler.getConnection()) {
            String deleteQuery = "DELETE FROM CarDetail WHERE id = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                deleteStatement.setInt(1, carId);
                int rowsAffected = deleteStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Car with ID " + carId + " has been deleted from the CarDetail table.");
                } else {
                    System.out.println("Car with ID " + carId + " not found in the CarDetail table.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void Update(int carId,String columnName,String newValue) {
		try (Connection connection = JdbcHandler.getConnection()) {
            String updateQuery = "UPDATE CarDetail SET " + columnName + " = ? WHERE id = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setString(1, newValue);
                updateStatement.setInt(2, carId);
                int rowsAffected = updateStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Column " + columnName + " for Car with ID " + carId + " has been updated to: " + newValue);
                } else {
                    System.out.println("Car with ID " + carId + " not found in the CarDetail table.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void add(String ab, String bc, String cd, String de) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcHandler.getConnection();
            String insertQuery = "INSERT INTO CarDetail (carname, cartype, price, availability) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);

            // Setting values for the prepared statement
            preparedStatement.setString(1, ab);
            preparedStatement.setString(2, bc);
            preparedStatement.setString(3, cd);
            preparedStatement.setString(4, de);

            // Executing the query to insert data
            int rowsAffected = preparedStatement.executeUpdate();

            // Checking the result
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing JDBC objects in the reverse order of their creation
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
	}
}
