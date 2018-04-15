package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {
	public void init(){
		List<Event> events = new ArrayList<>();
		events.add(new Event(events.size(), "Pickaxe", "You bought a Pickaxe.", 5));
		events.add(new Event(events.size(), "Goblin", "They came to mine ore.", 50));
		events.add(new Event(events.size(), "Machine Miner", "Goblins made you this.", 500));
		getServletContext().setAttribute("events", events);
			}
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		EventsDAOImpl dao = new EventsDAOImpl(getServletContext());
		// TODO: render the events page HTML
		String html = "";
				html+= "<!DOCTYPE html>";
				html+=	"<html lang=\"en\">";
				html+=	"<head>";
				html+=	"<meta charset=\"UTF-8\">";
				html+=	"<title>CS 3220 Clicker Game</title>";
				html+=	"<link rel=\"stylesheet\" href=\"styles.css\">";
				html+=	"</head>";
				html+=	"<body>";
				html+=	"<h1>Clicker Game!</h1>";
				html+=	"<nav>";
				html+=	"<a href=\"admin-info.html\">Info</a>";
				html+= 	"<a href=\"admin-generators.html\">Generators</a>";
				html+=  "<a href=\"admin-events.html\">Elements</a>";
				html+= 	"</nav>";
				html+=	"<div class=\"container\">;";
				html+=	"<div class=\"fill\">";
				html+=	"<form action=\"post\">";
				html+=	"<div>";
				html+=	"<label for=\"name\">Event Name</label>";
				html+=	"<div>";
				html+=	"<input type=\"text\" id=\"name\">";
				html+=	"</div>";
				html+=	"</div>";

				html+=	"<div>";
				html+=	"<label for=\"description\">Event Description</label>";
				html+=	"<div>";
				html+=	"<input type=\"text\" id=\"description\">";
				html+=	"</div>";
				html+=	"</div>";

				html+=	"<div>";
				html+=	"<label for=\"trigger\">Trigger At</label>";
				html+=	"<div>";
				html+=	"<input type=\"number\" id=\"trigger\">";
				html+=	"</div>";
				html+=	"</div>";

				html+=	"<div>";
				html+=	"<input type=\"submit\" value=\"Submit\">";
				html+=	"</div>";
				html+=	"</form>";
				html+=	"</div>";



				html+=	"<table>";
				html+=	"<thead>";
				html+=	"<tr>";
				html+=	"<th>Name</th>";
				html+=	"<th>Description</th>";
				html+=	"<th>Trigger At</th>";
				html+=	"</tr>";
				html+=	"</thead>";

				html+=	"<tbody>";

				List<Event> events = dao.getAll();
				for(Event event: events){
					html+= "<tr>";
					html+= "<td>" + event.getName() + "</td>";
					html+= "<td>" + event.getDescription() + "</td>";
					html+= "<td>" + event.getTriggerAt() + "</td>";
				}

				html+=	"</tbody>";
				html+=	"</table>";
				html+=	"</div>";

				html+=	"</body>";
				html+=	"</html>";

		out.println(html);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
		EventsDAOImpl dao = new EventsDAOImpl(getServletContext());
		List<Event> events = dao.getAll();

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String trigger = request.getParameter("trigger");
		int trigger2 = Integer.parseInt(trigger);

		Event temp = new Event(events.size(), name, description, trigger2);

		dao.add(temp);
		response.sendRedirect("/events");

	}
}
