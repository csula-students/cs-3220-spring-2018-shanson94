package edu.csula.storage.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.Database;
import edu.csula.models.Generator;

public class GeneratorsDAOImpl implements GeneratorsDAO {
	private final Database context;

	
	protected static final String getAllQuery = "SELECT * FROM generators;";
	protected static final String getByIdQuery = "SELECT * FROM generators WHERE generators.id = ?";
	protected static final String setQuery = "UPDATE generators SET name = ?, description = ?, rate = ?, base_cost = ?, unlock_at = ? WHERE generators.id = ?";
	protected static final String addQuery = "INSERT INTO generators(name, description, rate, base_cost,unlock_at) VALUE (?, ?, ?, ?, ?)";
	protected static final String removeQuery = "DELETE FROM generators WHERE generators.id = ?";

	public GeneratorsDAOImpl(Database context) {
		this.context = context;
	}

	@Override
	public List<Generator> getAll() {
		List<Generator> glist = new ArrayList<>();
		try (Connection c = context.getConnection(); Statement stmt = c.createStatement()){
			ResultSet rs = stmt.executeQuery(getAllQuery);
			while (rs.next()){
				Generator generator = new Generator();
						generator.id = rs.getInt(1);
						generator.name = rs.getString(2);
						generator.description = rs.getString(3);
						generator.rate = rs.getInt(4);
						generator.baseCost = rs.getInt(5);
						generator.unlockAt = rs.getInt(6);

				glist.add(generator);
			}
		}catch(SQLException e){
			e.printStackTrace();
			return glist;
		}

		return glist;

	}

	@Override
	public Optional<Generator> getById(int id) {
		Optional<Generator> glist = Optional.empty();

		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(getByIdQuery)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()){
				Generator generator = new Generator();
						generator.id = rs.getInt(1);
						generator.name = rs.getString(2);
						generator.description = rs.getString(3);
						generator.rate = rs.getInt(4);
						generator.baseCost = rs.getInt(5);
						generator.unlockAt = rs.getInt(6);

				glist = Optional.of(generator);
			}
		}catch(SQLException e){
			e.printStackTrace();
			return glist;
		}

		return glist;
	}

	@Override
	public void set(int id, Generator generator) {
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(setQuery)){
			stmt.setString(1, generator.getName());
			stmt.setString(2, generator.getDescription());
			stmt.setInt(3, generator.getRate());
			stmt.setInt(4, generator.getBaseCost());
			stmt.setInt(5, generator.getUnlockAt());
			stmt.setInt(6, id);	
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(Generator generator) {
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(addQuery)){
			stmt.setString(1, generator.getName());
			stmt.setString(2, generator.getDescription());
			stmt.setInt(3, generator.getRate());
			stmt.setInt(4, generator.getBaseCost());
			stmt.setInt(5, generator.getUnlockAt());
			stmt.setInt(6, id);	
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(int id) {
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(removeQuery)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
