
public class Word {
	private String word;
	private String tag;
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public Word() {
		word = null;
		tag = null;
	}
	
	public Word(String word, String tag) {
		this.word = word;
		this.tag = tag;
	}
}
