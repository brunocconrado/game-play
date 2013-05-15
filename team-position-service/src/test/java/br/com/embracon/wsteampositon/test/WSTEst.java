package br.com.embracon.wsteampositon.test;

import br.com.embracon.wsembraconvo.teamposition.Branch;
import br.com.embracon.wsembraconvo.teamposition.Regional;
import br.com.embracon.wsteamposition.ws.TeamPosition;
import br.com.embracon.wsteamposition.ws.TeamPosition_Service;

public class WSTEst {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Regional regional = 
				new Regional();
		regional.setCode("19");
		regional.setSystemCallIdentificator("009");
		regional.setUserLogin("bruno.conrado");

		getService().findRegional(regional);
		
		Branch b = new Branch();
		b.setCode("23");
		b.setSystemCallIdentificator("009");
		
		getService().findBranch(b);
	}

	private static TeamPosition getService() {
		return new TeamPosition_Service().getTeamPositionPort();
	}

}
