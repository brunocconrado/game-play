package br.com.embracon.wsteamposition.ws.util;

import br.com.embracon.wsteamposition.ws.TeamPosition;
import br.com.embracon.wsteamposition.ws.TeamPosition_Service;

public class WSFactory {
	
	private static TeamPosition service;
	
	private WSFactory(){}
	
	public static TeamPosition getService() {
		if(service == null) {
			service =  new TeamPosition_Service().getTeamPositionPort();
		}
		return service;
	}

}
