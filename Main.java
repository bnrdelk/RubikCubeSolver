import java.util.*;

class Main {
    public static void main(String[] args) {
        takeInput();
    }

	private static void takeInput(){
		RubikCube cube = new RubikCube();

		System.out.println("Welcome to Rubic Cube solver. Please enter the rotations to scramble the cube.");
		System.out.println("\t\t\tRotation list");
		System.out.println("u = Rotating the upper face clockwise.");
		System.out.println("U = Rotating the upper face counter-clockwise.");
		System.out.println("f = Rotating the front face clockwise.");
		System.out.println("F = Rotating the front face counter-clockwise.");
		System.out.println("r = Rotating the right face clockwise.");
		System.out.println("R = Rotating the right face counter-clockwise.");

		Scanner scanner = new Scanner(System.in);
		char input;

		System.out.println("\n\t\t\tInitial cube:");
		cube.printCube();

		do {
			System.out.print("Please enter a rotation key based on the instruction list above (You can exit by entering 'e'): ");
			input = scanner.next().charAt(0);
			cube = cube.rotate(input);

			System.out.println("\n\t\t\tCurrent State of the cube:");
			cube.printCube();

		}while(input != 'e');

		scanner.close();

		System.out.print("\nSolution: ");
		List<Character> solution = cube.solve();

		System.out.println("\t\t\tSolved Cube");
		cube.rotate(solution).printCube();
	}
}