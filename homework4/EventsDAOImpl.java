package edu.csula.storage.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.csula.storage.EventsDAO;
import edu.csula.storage.Database;
import edu.csula.models.Event;

public class EventsDAOImpl implements EventsDAO {
	private final Database context;


	protected static final String getAllQuery = "SELECT * FROM events;";
	protected static final String getByIdQuery = "SELECT * FROM events WHERE events.id = ?";
	protected static final String setQuery = "UPDATE events SET name = ? , description = ?, trigger_at = ? WHERE events.id = ?";
	protected static final String addQuery = "INSERT INTO events (name, description, trigger_at) VALUES (?, ?, ?)";
	protected static final String removeQuery = "DELETE FROM events WHERE events.id = ? ";

	public EventsDAOImpl(Database context) {
		this.context = context;
	}

	@Override
	public List<Event> getAll() {
		List<Event> elist = new ArrayList<>();
		try (Connection c = context.getConnection(); Statement stmt = c.createStatement()) {
			ResultSet rs = stmt.executeQuery(getAllQuery);
			while (rs.next()) {
				Event event = new Event();
					event.id = rs.getInt(1);
					event.name = rs.getString(2);
					event.description = rs.getString(3);
					event.triggerAt = rs.getInt(4);

				elist.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return elist;
		}
		return elist;
	}

	@Override
	public Optional<Event> getById(int id) {
	Optional<Event> eitems = Optional.empty();

		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(getByIdQuery)) {
			pstmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()){
				Event event = new Event();
					event.id = rs.getInt(1);
					event.name = rs.getString(2);
					event.description = rs.getString(3);
					event.triggerAt = rs.getInt(4);

				items = Optional.of(event);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}

		return eitems;
	}

	@Override
	public void set(int id, Event event) {
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(setQuery)){
			stmt.setString(1, event.getName());
			stmt.setString(2, event.getDescription());
			stmt.setInt(3, event.getTriggerAt());
			stmt.setInt(4, id);	
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(Event event) {
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(addQuery)) {
			stmt.setString(1, event.getName());
			stmt.setString(2, event.getDescription());
			stmt.setInt(3, event.getTriggerAt());
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
