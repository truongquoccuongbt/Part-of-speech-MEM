
public class Feature {
	private Word beforeWord;
	private Word currWord;
	private Word afterWord;
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
	public Word getAfterWord() {
		return afterWord;
	}
	public void setAfterWord(Word afterWord) {
		this.afterWord = afterWord;
	}
	
	public Feature() {
		currWord = new Word();
		afterWord = new Word();
		beforeWord = new Word();
	}
	
	public Feature(Word before, Word current, Word after) {
		this.beforeWord = before;
		this.currWord = current;
		this.afterWord = after;
	}
}
