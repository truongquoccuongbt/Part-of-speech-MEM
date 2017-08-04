
public class Feature {
	private Word beforeWord;
	private Word currWord;
	
	public Word getBeforeWord() {
		return beforeWord;
	}
	public void setBeforeWord(Word beforeWord) {
		this.beforeWord = beforeWord;
	}
	public Word getCurrWord() {
		return currWord;
	}
	public void setCurrWord(Word currWord) {
		this.currWord = currWord;
	}
	
	public Feature() {
		currWord = new Word();
		beforeWord = new Word();
	}
	
	public Feature(Word before, Word current) {
		this.beforeWord = before;
		this.currWord = current;
	}
	
	public String GetToken(Word w) {
		return w.getToken();
	}
	
	public String GetTag(Word w) {
		return w.getTag();
	}
	
	
	public void PrintFeature() {
		System.out.println(this.beforeWord.getToken() + " " + this.beforeWord.getTag());
		System.out.println(this.currWord.getToken() + " " + this.currWord.getTag());
	}
}
