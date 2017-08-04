
public class Feature2 {
	private String tokenCurr;
	private String tokenBefore;
	
	public Feature2(String tokenCurr, String tokenBefore) {
		this.tokenCurr = tokenCurr;
		this.tokenBefore = tokenBefore;
	}

	public String getTokenCurr() {
		return tokenCurr;
	}

	public void setTokenCurr(String tokenCurr) {
		this.tokenCurr = tokenCurr;
	}

	public String getTokenBefore() {
		return tokenBefore;
	}

	public void setTokenBefore(String tokenBefore) {
		this.tokenBefore = tokenBefore;
	}
}
