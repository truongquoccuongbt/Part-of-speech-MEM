import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PartOfSpeech {
	private boolean check = false;
	private Word[] listInput;
	public void Trainning(String pathTrain) {
		ReadFile(pathTrain);
	}
	
	private void ReadFile(String path) {
		try {
			FileInputStream fstream = new FileInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String line;
			String []tmp;
			while ((line = br.readLine()) != null) {
				line = "st/st " + line + " sf/sf";
				tmp = line.split(" ");
				CreateFeature0(tmp);
				CreateFeature1(tmp);
				CreateFeature2(tmp);
				CreateFeature3(tmp);
			}
			br.close();
			if (!check) check = true;
		}catch (IOException e) {
			System.out.println("File not found");
		}
	}
	
	private void CreateFeature0(String[] tmp) {
		String path = new File("").getAbsolutePath() + "/Feature0.txt";
		if (new File(path).exists() && !check) new File(path).delete();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
			for (int  i = 0; i < tmp.length; i++) {
				bw.write(tmp[i] + "\n");
			}
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void CreateFeature1(String[] tmp) {
		String[] tagCurr;
		String[] tagBefore;
		String str;
		String path = new File("").getAbsolutePath() + "/Feature1.txt";
		if (new File(path).exists() && !check) new File(path).delete();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
			for (int i = 1; i < tmp.length; i++) {
				tagCurr = tmp[i].split("/");
				tagBefore = tmp[i - 1].split("/");
				str = tagCurr[1] + " " + tagBefore[1];
				bw.write(str + "\n");
			}
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void CreateFeature2(String[] tmp) {
		String[] tokenCurr;
		String[] tokenBefore;
		String str;
		String path = new File("").getAbsolutePath() + "/Feature2.txt";
		if (new File(path).exists() && !check) new File(path).delete();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
			for (int i = 1; i < tmp.length; i++) {
				tokenCurr = tmp[i].split("/");
				tokenBefore = tmp[i - 1].split("/");
				str = tokenCurr[0] + " " + tokenBefore[0];
				bw.write(str + "\n");
			}
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void CreateFeature3(String[] tmp) {
		String[] tokenCurr;
		String[] tokenAfter;
		String str;
		String path = new File("").getAbsolutePath() + "/Feature3.txt";
		if (new File(path).exists() && !check) new File(path).delete();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
			for (int i = 0; i < tmp.length - 1; i++) {
				tokenCurr = tmp[i].split("/");
				tokenAfter = tmp[i + 1].split("/");
				str = tokenCurr[0] + " " + tokenAfter[0];
				bw.write(str + "\n");
			}
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//*************************************************************************
	
	
	//--------------------------------------------------------------------------
	// Test
	//--------------------------------------------------------------------------
	
	public void Input(String input) {
		input = "st/st " + input + " sf/sf";
		String[] tmp = input.split(" ");
		this.listInput = new Word[tmp.length];
		for (int i = 0; i < this.listInput.length; i++) {
			this.listInput[i] = new Word();
		}
		this.listInput[0].setToken("st");
		this.listInput[0].setTag("st");
		for (int i = 1; i < tmp.length - 1; i++) {
			this.listInput[i].setToken(tmp[i]);
		}
		this.listInput[tmp.length - 1].setToken("sf");
		this.listInput[tmp.length - 1].setTag("sf");
	}
	
	public void PredictTag() {
		for (int i = 1; i < this.listInput.length - 1; i++) {
			PartOfSpeechToken(this.listInput[i].getToken(), this.listInput[i + 1].getToken(), this.listInput[i - 1].getToken(), this.listInput[i - 1].getTag());
		}
	}
	
	private void PartOfSpeechToken(String tokenCurr, String tokenAfter, String tokenBefore, String tagBefore) {
		MEM m = new MEM(tokenCurr, tokenAfter, tokenBefore, tagBefore);
		m.DetermindFeaturesInDataset();
		m.PrintMatrixFea();
		System.out.println();
		m.PrintMatrixLamda();
	}
}
