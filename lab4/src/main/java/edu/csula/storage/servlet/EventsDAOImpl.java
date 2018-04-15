package edu.csula.storage.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collection;

import javax.servlet.ServletContext;

import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

/**
 * To abstract the storage access from the business layer using ServletContext
 * (application scope). This implementation will store the underlying data under
 * the application scope and read from it accordingly.
 *
 * As ServletContext is like a global HashMap, it's important for you to add a
 * context name to separate out the different section of data (e.g. events vs
 * generators) so that you can have the application scope looks like below:
 *
 * ```json
 * {
 *   "events": [
 *     { "id": 0, "name": "event-1", "description": "..." }
 *   ],
 *   "generators": [
 *     { "id": 0, "name": "generator-1", "description": "..." }
 *   ]
 * }
 * ```
 */
public class EventsDAOImpl implements EventsDAO {
	private final ServletContext context;
	protected static final String CONTEXT_NAME = "events";

	public EventsDAOImpl(ServletContext context) {
		this.context = context;
	}

	@Override
	public List<Event> getAll() {
		List<Event> temp = (List<Event>) context.getAttribute(CONTEXT_NAME);
		if(temp == null){
			return new ArrayList<Event>();
		}
		return temp;
	}

	@Override
	public Optional<Event> getById(int id) {

		for(Event temp : this.getAll()){
			if(temp.getId() == id){
				return Optional.of(temp);
			}
		}
		return Optional.empty();
	}

	@Override
	public void set(int id, Event event) {
		// TODO: set a certain event given id to be different from context
		List<Event> temp = this.getAll();
		for(int i = 0; i < temp.size(); i++){
			if(temp.get(i).getId() == id){
				temp.set(i, event);
			}
		}
		context.setAttribute(CONTEXT_NAME, temp);

	}

	@Override
	public void add(Event event) {
		Collection<Event> temp = new ArrayList<>();
		temp.add(event);
		context.setAttribute(CONTEXT_NAME, temp);
	}

	@Override
	public void remove(int id) {
		
		List<Event> temp = this.getAll();
		for(int i = 0; i < temp.size(); i++){
			if(temp.get(i).getId() == id){
				temp.remove(i);
			}
		}
		context.setAttribute(CONTEXT_NAME, temp);
	}
}
