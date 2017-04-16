package streetfighter.condition;

public class PostConditionError extends Exception {

	String msg;
	public PostConditionError(String string) {
		msg = string;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
