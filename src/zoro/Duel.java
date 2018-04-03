package zoro;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Duel {

	public Duel() {
		System.out.println("Zoro Duel...");
	}
	
	@WebMethod
	public String winner() {
		return "ZORO" ;
	}
}
