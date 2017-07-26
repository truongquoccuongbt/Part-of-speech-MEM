import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PartOfSpeech {
	public void Trainning(String pathTrain) {
		ReadFile(pathTrain);
	}
	
	private void ReadFile(String path) {
		try {
			FileInputStream fstream = new FileInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			ArrayList<String> arr = new ArrayList<>();
			String line;
			String []tmp;
			String[] feature;
			while ((line = br.readLine()) != null) {
				tmp = line.split(" ");
				for (int i = 0; i < tmp.length; i++) {
					feature = CreateFeature(i, tmp);
					AddFeature(arr, feature);
				}
			}
			br.close();
			WriteFeature(arr);
		}catch (IOException e) {
			System.out.println("File not found");
		}
	}
	
	private void AddFeature(ArrayList<String> list, String[] f) {
		String str = f[0] + " " + f[1] + " " + f[2];
		for (int i = 0; i < list.size(); i++) {
			if (str.equals(list.get(i))) return;
		}
		list.add(str);
	}
	
	private void WriteFeature(ArrayList<String> feature) {
		String path = new File("").getAbsolutePath() + "/Feature.txt";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
			for (int i = 0; i < feature.size(); i++) {
				bw.write(feature.get(i) + "\n");
			}
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String[] CreateFeature(int posCurr, String[] sentence) {
		String[] tmp = new String[3];
		tmp[1] = sentence[posCurr];
		if (posCurr == 0) {
			tmp[0] = null;
		}
		else {
			tmp[0] = sentence[posCurr - 1];
			
		}
		
		if (posCurr == (sentence.length - 1)) {
			tmp[2] = null;
		}
		else {
			tmp[2] = sentence[posCurr + 1];
		}
		return tmp;
	}
	//*************************************************************************
	
	
	//--------------------------------------------------------------------------
	// Test
	//--------------------------------------------------------------------------
	
	
	
	private Word CreateWord(String wordWithTag) {
		Word w;
		if (wordWithTag != null) {
			String[] tmp = wordWithTag.split("/");
			w = new Word(tmp[0], tmp[1]);
		}
		else {
			w = new Word(null, null);
		}
		return w;
	}

}
