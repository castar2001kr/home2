package game.controller;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import game.repository.idgenerator.Info;
import game.repository.idgenerator.Node;
import game.repository.manager.Room;
import game.repository.manager.RoomManager;
import game.repository.player.Player;

/**
 * Servlet implementation class controller
 */
@WebServlet("/make")
public class make extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public make() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		Player p = new Player();
		try {
			
			
			String title=request.getParameter("title");
					
			
			System.out.println(""+RoomManager.getInstance().makeRoom(p,title)+"번 방이 생성되었다.");
			
			System.out.println("mypid = "+p.getPid());
			
			Queue<Node> q=RoomManager.getInstance().getRooms();
			
			while(!q.isEmpty()) {
				
				try {
					Node num = q.poll();
					System.out.print(""+num+" : ");
					System.out.println(RoomManager.getInstance().getRoom(num.getId()).getInfo().getTitle());
				}catch(Exception e) {
					
					System.out.println("없는 방 조회");
				}
				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
