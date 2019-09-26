package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import impl.DefaultTourDao;
import models.TourData;

/**
 * Servlet implementation class UpdateTourServlet
 */
public class UpdateTourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DefaultTourDao tourDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));
		tourDao = DefaultTourDao.getTourDaoInstance();
		TourData tour = tourDao.getTourById(id);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/admin.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));
		tourDao = DefaultTourDao.getTourDaoInstance();
		TourData tour = tourDao.getTourById(id);
		tour.setId(id);
		tour.setName(request.getParameter("name"));
		tour.setPrice(Double.valueOf(request.getParameter("price")));
		tour.setDescription(request.getParameter("description"));
		tourDao.updateTourRecord(id);
		request.getRequestDispatcher("WEB-INF/views/tourUpdate.jsp").forward(request, response);
	}

}
