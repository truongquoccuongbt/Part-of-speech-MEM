import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MEM {
	private String tokenCurr;
	private String tokenBefore;
	private String tokenAfter;
	private String tagBefore;
	private HashMap<Integer, Feature0> listFeature0;
	private HashMap<Integer, Feature1> listFeature1;
	private HashMap<Integer, Feature2> listFeature2;
	private HashMap<Integer, Feature3> listFeature3;
	private ArrayList<String> listTag;
	private int[][] matrixFeaTag;
	private double[][] matrixLamda;
	
	
	public MEM(String tokenCurr, String tokenBefore, String tokenAfter, String tagBefore) {
		this.tokenCurr = tokenCurr;
		this.tokenBefore = tokenBefore;
		this.tokenAfter = tokenAfter;
		this.tagBefore = tagBefore;
		this.listTag = new ArrayList<>();
		InitialListFeature0();
		InitialListFeature1();
		InitialListFeature2();
		InitialListFeature3();
		InitialMaTrixFeaTag();
		InitialMatrixLamda();
	}
	
	//--------------------------------------------------------------------------
	//Khởi tạo các thuộc tính
	//-------------------------------------------------------------------------
	
	private void InitialMaTrixFeaTag() {
		this.matrixFeaTag = new int[this.listTag.size()][4];
		for (int i = 0; i < this.listTag.size(); i++) {
			for (int j = 0; j < 4; j++) {
				this.matrixFeaTag[i][j] = 0;
			}
		}
	}
	
	private void InitialMatrixLamda() {
		this.matrixLamda = new double[this.listTag.size()][4];
		for (int i = 0; i < this.listTag.size(); i++) {
			for (int j  = 0; j < 4; j++) {
				this.matrixLamda[i][j] = 0;
			}
		}
	}
	
	private void InitialListFeature0() {
		this.listFeature0 = new HashMap<>();
		SeparateFeature0();
	}
	
	private void InitialListFeature1() {
		this.listFeature1 = new HashMap<>();
		SeparateFeature1();
	}
	
	private void InitialListFeature2() {
		this.listFeature2 = new HashMap<>();
		SeparateFeature2();
	}
	
	private void InitialListFeature3() {
		this.listFeature3 = new HashMap<>();
		SeparateFeature3();
	}
	
	private void SeparateFeature0() {
		try {
			String path = new File("").getAbsolutePath() + "/Feature0.txt";
			FileInputStream fs = new FileInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			String[] tmp;
			Feature0 f;
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				tmp = line.split("/");
				f = new Feature0(tmp[0], tmp[1]);
				this.listFeature0.put(i, f);
				AddListTag(tmp[1]);
				i++;
			}
			br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void SeparateFeature1() {
		String path = new File("").getAbsolutePath() + "/Feature1.txt";
		try {
			FileInputStream fs = new FileInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			String line;
			String[] tmp;
			Feature1 f;
			int i = 0;
			while ((line = br.readLine()) != null) {
				tmp = line.split(" ");
				f = new Feature1(tmp[0], tmp[1]);
				this.listFeature1.put(i, f);
				i++;
			}
			br.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void SeparateFeature2() {
		String path = new File("").getAbsolutePath() + "/Feature2.txt";
		try {
			FileInputStream fs = new FileInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			String line;
			String[] tmp;
			Feature2 f;
			int i = 0;
			while ((line = br.readLine()) != null) {
				tmp = line.split(" ");
				f = new Feature2(tmp[0], tmp[1]);
				this.listFeature2.put(i, f);
				i++;
			}
			br.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void SeparateFeature3() {
		String path = new File("").getAbsolutePath() + "/Feature3.txt";
		try {
			FileInputStream fs = new FileInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			String line;
			String[] tmp;
			Feature3 f;
			int i = 0;
			while ((line = br.readLine()) != null) {
				tmp = line.split(" ");
				f = new Feature3(tmp[0], tmp[1]);
				this.listFeature3.put(i, f);
				i++;
			}
			br.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void AddListTag(String tag) {
		for (int i = 0; i < this.listTag.size(); i++) {
			if (this.listTag.get(i).equals(tag)) {
				return;
			}
		}
		this.listTag.add(tag);
	}

	//---------------------------------------------------------
	// Hàm getter và setter
	//---------------------------------------------------------
	
	
	
	
	//-------------------------------------------------------------------------
	// Tìm feature có trong tập trainning
	//-------------------------------------------------------------------------
	
	public void DetermindFeaturesInDataset() {
		for (int i = 0; i < this.listTag.size(); i++) {
			for (int j = 0; j < 4; j++) {
				switch(j) {
				case 0:
					if (DetermindFeature0(this.tokenCurr, this.listTag.get(i))) {
						this.matrixFeaTag[i][j] = 1;
						this.matrixLamda[i][j] = CalculateLamda();
					}
					break;
				case 1:
					if (DetermindFeature1(this.listTag.get(i), this.tagBefore)) {
						this.matrixFeaTag[i][j] = 1;
						this.matrixLamda[i][j] = CalculateLamda();
					}
					break;
				case 2:
					if (DetermindFeature2(this.tokenCurr, this.tokenBefore)) {
						this.matrixFeaTag[i][j] = 1;
						this.matrixLamda[i][j] = CalculateLamda();
					}
					break;
				case 3:
					if (DetermindFeature3(this.tokenCurr, this.tokenAfter)) {
						this.matrixFeaTag[i][j] = 1;
						this.matrixLamda[i][j] = CalculateLamda();
					}
					break;
				}
			}
		}
	}
	
	private boolean DetermindFeature0(String token, String tag) {
		for (Integer i : this.listFeature0.keySet()) {
			if (token.equals(this.listFeature0.get(i).getToken()) && tag.equals(this.listFeature0.get(i).getTag())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean DetermindFeature1(String tagCurr, String tagBefore) {
		for (Integer i : this.listFeature1.keySet()) {
			if (tagCurr.equals(this.listFeature1.get(i).getTagCurr()) && tagBefore.equals(this.listFeature1.get(i).getTagBefore())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean DetermindFeature2(String tokenCurr, String tokenBefore) {
		for (Integer i : this.listFeature2.keySet()) {
			if (tokenCurr.equals(this.listFeature2.get(i).getTokenCurr()) && tokenBefore.equals(this.listFeature2.get(i).getTokenBefore())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean DetermindFeature3(String tokenCurr, String tokenAfter) {
		for (Integer i : this.listFeature3.keySet()) {
			if (tokenCurr.equals(this.listFeature3.get(i).getTokenCurr()) && tokenAfter.equals(this.listFeature3.get(i).getTokenAfter())) {
				return true;
			}
		}
		return false;
	}
	
	
	//--------------------------------------------------------------
	// tính xác xuất của nhãn với âm tiết
	//--------------------------------------------------------------
	
//	public double ComputeNumerator(int posInListTag) {
//		double numerator = 0;
//		for (Integer i : this.listFeature.keySet()) {
//			if (this.matrixFeaTag[posInListTag][i] == 1) {
//				numerator += Math.exp(this.matrixLamda[posInListTag][i]);
//			}
//		}
//		return numerator;
//	}
//	
//	public double ComputeDenominator() {
//		double denominator = 0;
//		for (int i = 0; i < this.listTag.size(); i++) {
//			for (Integer j : this.listFeature.keySet()) {
//				if (this.matrixFeaTag[i][j] == 1) {
//					denominator += Math.exp(this.matrixLamda[i][j]);
//				}
//			}
//		}
//		return denominator;
//	}
	
//	public double ComputeProWordWithTag(int posOfTag, double denominator) {
//		double numerator = ComputeNumerator(posOfTag);
//		double pro = numerator / denominator;
//		return pro;
//	}
//	
//	public void PredictTag() {
//		double denominator = ComputeDenominator();
//		double max = 0;
//		double pro = 0;
//		int posTag = -1;
//		for (int i = 0; i < this.listTag.size(); i++) {
//			pro = ComputeProWordWithTag(i, denominator);
//			if (max < pro) {
//				max = pro;
//				posTag = i;
//			}
//		}
//		
//		if (posTag == -1) {
//			
//		}
//	}
	
	public double CalculateLamda() {
		double lamda = Math.random();
		return lamda;
	}
	
	//----------------------------------------------------------------------
	//Print
	//----------------------------------------------------------------------
	
	public void Printtag() {
		for (int i = 0; i < this.listTag.size(); i++) {
			System.out.println(this.listTag.get(i));
		}
	}
	
	public void PrintMatrixFea() {
		for (int i = 0; i < this.listTag.size(); i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(this.matrixFeaTag[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void PrintMatrixLamda() {
		for (int i = 0; i < this.listTag.size(); i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(this.matrixLamda[i][j] + " ");
			}
			System.out.println();
		}
	}
}
