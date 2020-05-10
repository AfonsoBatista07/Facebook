package user;

public class SelfCenteredClass extends UserClass implements SelfCentered {
	
	public SelfCenteredClass(String id) {
		super(id, SELF_CENTERED);
	}
	
	public void comment() {
		
	}
}
