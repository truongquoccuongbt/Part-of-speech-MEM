import java.io.File;

public class program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PartOfSpeech p = new PartOfSpeech();
		
//		for (int i = 1; i <= 142; i++) {
//			p.Trainning("/home/quoccuong/eclipse-workspace/Part of Speech(MEM)/data/" + i + ".pos");
//		}
		p.Trainning("/home/quoccuong/eclipse-workspace/Part of Speech(MEM)/data/1000.pos");
//		System.out.println("Trainning successfull");
		p.Input("học");
		p.PredictTag();
	}

}
