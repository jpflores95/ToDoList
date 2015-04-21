package todochallenge.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todochallenge.dto.Todo;
import todochallenge.services.TodoManager;

import com.google.gson.Gson;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns={"/Controller","/start", "/done", "/add", "/next", "/previous"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		switch(request.getServletPath()){
			case "/start":
				request.setAttribute("todo", TodoManager.getFirstTodo());
				request.getRequestDispatcher("index.jsp").forward(request, response);
				break;
			case "/done":
				id = Integer.parseInt(request.getParameter("id"));
				TodoManager.doneTodo(id);
				request.getRequestDispatcher("start").forward(request, response);
				break;
			case "/add":
				String note = request.getParameter("note");
				TodoManager.addTodo(note);
				//request.getRequestDispatcher("start").forward(request, response);
				break;
			case "/next":
				id = Integer.parseInt(request.getParameter("id"));
				//request.setAttribute("todo", TodoManager.getNextTodo(id));
				//request.getRequestDispatcher("index.jsp").forward(request, response);
				Todo t = TodoManager.getNextTodo(id);
				Gson g = new Gson();
				response.setContentType("application/json");
				PrintWriter pw = response.getWriter();
				pw.write(g.toJson(t));
				break;
				
			case "/previous":
				id = Integer.parseInt(request.getParameter("id"));
				//request.setAttribute("todo", TodoManager.getPreviousTodo(id));
				//request.getRequestDispatcher("index.jsp").forward(request, response);
				t = TodoManager.getPreviousTodo(id);
				g = new Gson();
				response.setContentType("application/json");
				pw = response.getWriter();
				pw.write(g.toJson(t));
				break;
		}
	}
}
