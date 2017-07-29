
public class Word {
	private String token;
	private String tag;
	
	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Word() {
		token = null;
		tag = null;
	}
	
	public Word(String token, String tag) {
		this.token = token;
		this.tag = tag;
	}
}
