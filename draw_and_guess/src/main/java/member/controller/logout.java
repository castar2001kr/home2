package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import game.repository.manager.RoomManager;
import game.repository.player.Player;

/**
 * Servlet implementation class logout
 */
@WebServlet("/logout")
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("logout request detected");
		HttpSession s = request.getSession();
		
		synchronized(s) {
			
			try {
				
				Player p= ((Player) s.getAttribute("player"));
				RoomManager.getInstance().getRoom(p.getRoom()).getInfo().getAct().out(p);

			}catch (Exception e) {
				// TODO: handle exception
			}finally {
				
				try {
					
					s.invalidate();
				}catch(Exception e) {
					
				}
				
			}
		}
		
		
	}

	

}
