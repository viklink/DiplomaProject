package impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TourDao;
import models.TourData;

public class DefaultTourDao implements TourDao {
	private static final String JDBC_MYSQL_HOST = "jdbc:mysql://localhost:3306/";
	private static final String MYSQL_JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String DB_NAME = "travel  ";
	private static final String PARAMS = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "jogdogJ25";

	public static final String SELECT_TOUR_BY_ID = "SELECT * FROM tours WHERE id = ?";
	public static final String SELECT_TOUR_BY_NAME = "SELECT * FROM tours WHERE name = ?";
	public static final String SELECT_TOUR_BY_PRICE = "SELECT * FROM tours WHERE price < ?";
	public static final String INSERT_TOUR = "INSERT INTO tours (id, name) VALUES (?, ?)";
	public static final String UPDATE_TOUR_RECORD = "UPDATE tours SET name = ?, price = ? WHERE id = ?";
	public static final String DELETE_TOUR_RECORD = "DELETE FROM tours WHERE id = ?";
	
	private static DefaultTourDao instance;
	private DefaultTourDao() {
	}
	public static synchronized DefaultTourDao getTourDaoInstance() {
		if(instance == null) {
			instance = new DefaultTourDao();
		}
		return instance;
	}
	@Override
	public TourData getTourById(int id) {
		TourData tour = new TourData();
		try (Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(SELECT_TOUR_BY_ID)) {
				statement.setInt(1, id);
				try (ResultSet rs = statement.executeQuery()) {
					while (rs.next()) {
						tour.setId(rs.getInt("id"));
						tour.setName(rs.getString("name"));
						tour.setPrice(rs.getDouble("price"));
						}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tour;
	}

	@Override
	public TourData getTourByName(String name) {
		TourData tour = new TourData();
		try (Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(SELECT_TOUR_BY_NAME)) {
				statement.setString(1, name);
				try (ResultSet rs = statement.executeQuery()) {
					while (rs.next()) {
						tour.setId(rs.getInt("id"));
						tour.setName(rs.getString("name"));
						tour.setPrice(rs.getDouble("price"));
						}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tour;
	}
	
	@Override
	public List<TourData> getTourByPrice(double price) {
		List<TourData> tours = new ArrayList<>();
		double maxPrice = 5000;
		try (Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(SELECT_TOUR_BY_PRICE)) {
				statement.setDouble(1, maxPrice);
				try (ResultSet rs = statement.executeQuery()) {
					while (rs.next()) {
						TourData tourData = new TourData();
						tourData.setId(rs.getInt("id"));
						tourData.setName(rs.getString("name"));
						tourData.setPrice(rs.getDouble("price"));
						tours.add(tourData);
						}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tours;
	}

	@Override
	public boolean saveTour(TourData tourData) {
		int rows = 0;
		try (Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(INSERT_TOUR)) {
				
				statement.setInt(1, 6);
				statement.setString(2, "Tropic paradise");
				statement.setDouble(3, 5000);
				
				rows = statement.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(rows != 0) {
			return true;
		} else {
		return false;
		}
	}
	
	@Override
	public void updateTourRecord(int id) {
		try (Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(UPDATE_TOUR_RECORD)) {
			statement.setString(1, "Fjords of Norway");
			statement.setDouble(2, 3500);
			statement.setInt(3, 5);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Record is updated");
	}
	
	@Override
	public void deleteTourRecord(int id) {
		try (Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(DELETE_TOUR_RECORD)) {
			statement.setInt(1, 7);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Record is deleted");
	}
	
	private Connection getConnection() {
		try {
			Class.forName(MYSQL_JDBC_DRIVER_NAME);
			return DriverManager.getConnection(JDBC_MYSQL_HOST + DB_NAME + PARAMS, USERNAME, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
