package mangahub.app.error.exception;

public class MangaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MangaNotFoundException(String message) {
		super(message);
	}
}