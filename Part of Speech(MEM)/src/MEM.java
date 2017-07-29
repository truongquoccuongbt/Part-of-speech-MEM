import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MEM {
	private String word;
	private HashMap<Integer, Feature> listFeature;
	private ArrayList<String> listTag;
	private int[][] matrixFeaTag;
	private double[][] matrixLamda;
	
	
	public MEM(String word) {
		this.word = word;
		this.listFeature = new HashMap<>();
		listTag = new ArrayList<>();
		CreateListFeature();
		InitialMatrixFeaTag(this.listTag.size(), this.listFeature.size());
		IntialMatrixLamda(this.listTag.size(), this.listFeature.size());
	}
	
	private void InitialMatrixFeaTag(int row, int col) {
		matrixFeaTag = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrixFeaTag[i][j] = 0;
			}
		}
	}
	
	private void IntialMatrixLamda(int row, int col) {
		matrixLamda = new double[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrixLamda[i][j] = 0;
			}
		}
	}
	
	private Feature CreateFeature(String str) {
		String[] tmp = str.split(" ");
		Word w1, w2, w3;
		String[] tmp1 = tmp[0].split("/");
		String[] tmp2 = tmp[1].split("/");
		String[] tmp3 = tmp[2].split("/");
		w1 = new Word(tmp1[0], tmp1[1]);
		w2 = new Word(tmp2[0], tmp2[1]);
		w3 = new Word(tmp3[0], tmp3[1]);
		
		AddTag(tmp1[1]);
		AddTag(tmp2[1]);
		AddTag(tmp3[1]);
		return new Feature(w1, w2, w3);
	}
	
	private void AddTag(String tag) {
		for (int i = 0; i < this.listTag.size(); i++) {
			if (this.listTag.get(i).equals(tag)) {
				return;
			}
		}
		this.listTag.add(tag);
	}
	
	private void CreateListFeature() {
		try {
			String path = new File("").getAbsolutePath() + "/Feature.txt";
			FileInputStream fs = new FileInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				this.listFeature.put(i, CreateFeature(line));
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ProWord(String tag) {
		ArrayList<Integer> lsPosFea = FindFeature(this.word, tag);
		double result  = 0;
		
	}
	
	public ArrayList<Integer> FindFeature(String currWord, String tag) {
		ArrayList<Integer> ls = new ArrayList<>();
		for (int i = 0; i < this.listFeature.size(); i++) {
			if (this.listFeature.get(i).getCurrWord().getToken() == currWord && this.listFeature.get(i).getCurrWord().getTag() == tag) {
				ls.add(i);
			}
		}
		return ls;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	
	
	public HashMap<Integer, Feature> getListFeature() {
		return listFeature;
	}

	public void setListFeature(HashMap<Integer, Feature> listFeature) {
		this.listFeature = listFeature;
	}
	
	public double CalculateLamda() {
		double lamda = Math.random();
		return lamda;
	}
}
