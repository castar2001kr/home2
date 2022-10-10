package member.service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import game.repository.manager.RoomManager;
import game.repository.player.Player;
import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class MemberService {
	
	private static MemberService memberservice;
	
	private HashMap<String, HttpSession> map;
	
	private MemberDAO dao;
	
	private MemberService() {
		
		dao=MemberDAO.getInstance();
		map = new HashMap<>();
		
	}
	
	public boolean checkConnected(HttpSession s) {
		
		MemberDTO dto = (MemberDTO) s.getAttribute("MemberDTO");
		
		
		if(dto!=null) {
			String id = dto.getId();
			return map.get(id).equals(s);
		}
		
		return false;
	}
	
	public static MemberService getInstance() {
		
		if(memberservice==null)
		memberservice=new MemberService();
		
		return memberservice;
		
	}
	
	
	public boolean regist(MemberDTO member) {
		
		return dao.save(member);
	}
	
	@SuppressWarnings("finally")
	public boolean login(MemberDTO member, HttpSession session) {
		
		boolean result = false;
		
		try {
			
			MemberDTO ans = dao.find(member.getId());
			
			result = ans.getId().equals(member.getId()) && ans.getPwd().equals(member.getPwd());
			
			
			if(result) {
				
				System.out.println("login succeed");
				
				member.setName(ans.getName());
				
				HttpSession s = ((HttpSession)map.get(member.getId()));
				
				if(s!=null) {
					
					synchronized (s) {
						
						try {
							
							Player p= ((Player) s.getAttribute("player"));
							
							p.close();
							
						}catch (Exception e) {
							// TODO: handle exception
						}finally {
							
							try{s.invalidate();}
							catch (Exception e) {
								// TODO: handle exception
							}
						
						
						}
						
						map.put(member.getId(), session);
						
						
					}
					
				}else {
					
					map.put(member.getId(), session);
					
				}
				
				
				
				
			}
			
			
		}catch(Exception e) {
			
			
			
		}finally {
			
			if(result) {
				session.setAttribute("MemberDTO", member);
			}
			
			return result;
			
		}
		
	}
	
	public MemberDTO search(MemberDTO member) {
		
		MemberDTO ans = dao.find(member.getId());
		
		return ans;
		
	}

}