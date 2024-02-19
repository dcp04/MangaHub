package mangahub.app.error.exception;

public class ExcepcionCampoVacio extends RuntimeException {
	private String field;

	public ExcepcionCampoVacio(String field, String message) {
		super(message);
		this.field = field;
	}

	public String getField() {
		return field;
	}
}
