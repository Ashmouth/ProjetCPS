package streetfighter.condition;

public class PreConditionError extends Exception {

	String msg;
	public PreConditionError(String string) {
		msg = string;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
