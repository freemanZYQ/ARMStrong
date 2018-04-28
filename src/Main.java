import java.util.List;
import java.util.Scanner;

import simulator.*;

public class Main {
	public static void main(String[] args) {
		ArmSimulator jpp = new ArmSimulator();

			Cpu cpuTest = new Cpu();
			Scanner in = new Scanner(System.in);
			String test = "";
			while (in.hasNext()) {
				test = test + in.nextLine() + System.lineSeparator();
			}
			System.out.println(test);
			Interpretor interpretorTest = new Interpretor(cpuTest,new Program(test));
			while (interpretorTest.hasNext()) {
				try {
					cpuTest.addInstruction(interpretorTest.next());
				} catch (InvalidSyntaxException | InvalidOperationException | InvalidRegisterException
						| InvalidInstructionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			cpuTest.execute();
	}
}
