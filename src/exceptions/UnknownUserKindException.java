package exceptions;

public class UnknownUserKindException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -177486162299409744L;
	
	private String kind;
	
	public UnknownUserKindException(String kind) {
		super();
		this.kind = kind;
	}
	
	public String getKind() {
		return kind;
	}
}
