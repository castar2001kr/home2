package game.repository.MsgRouter;

import org.json.simple.JSONObject;

import game.repository.ActRouter.ActEntrance;

public interface MsgRouter {

	void submit(String msg, int pid, JSONObject obj);
	
}
