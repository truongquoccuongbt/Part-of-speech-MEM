import java.io.File;

public class program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PartOfSpeech p = new PartOfSpeech();
		String path = new File("").getAbsoluteFile() + "/Feature.txt";
		File file = new File(path);
		if (file.exists()) file.delete();
		for (int i = 1; i <= 142; i++) {
			p.Trainning("/home/quoccuong/eclipse-workspace/Part of Speech(MEM)/data/" + i + ".pos");
		}
		System.out.println("Trainning successfull");
		
	}

}
