import java.util.Scanner;

public class Main {
    /*
     * Counter for checking if there are more than 4 of same color.
     * index 0 for red, 1 for blue, 2 for yellow, 3 for orange, 4 for green, 5 for white
     */
	static int[] colorCounter = new int[6];

    //Storing every face of the cube
    static String[] cubeFaces = {"Top", "Left", "Front", "Right", "Bottom", "Back"};

    //Storing every cubie face for every face of the cube (NAME OF THE VARIABLE MIGHT NEED TO BE CHANGED!!)
    static String[] cubieFaces = {"Top Left", "Top Right", "Bottom Left", "Bottom Right"};

    public static void main(String[] args) {
        RubicCube cube = new RubicCube();

        takeInput(cube);

        //Printing the cube based on the inputs
        System.out.println("\n\t\t\tYour cube");
        cube.printCube();
    }

    private static void takeInput(RubicCube cube){
        System.out.println("Enter your cube's current state for each face please. (First letters of the colors are enough.)");
        for(int i = 0; i < 6; i++){
            System.out.println("\n\t\t\tState of the " + cubeFaces[i] + " face: ");
            for(int j = 0; j < 4; j++){
                System.out.print("Color of the " + cubieFaces[j] + ": ");
                cube.faces[i][j] = getColorFromUser();
            }
        }
    }

    private static char getColorFromUser(){
        Scanner scanner = new Scanner(System.in);
        boolean isValid = true;
    
        char input = scanner.next().charAt(0);
        input = Character.toLowerCase(input);
        
        if(!isColorValid(input)){
            do{
                input = scanner.next().charAt(0);
                input = Character.toLowerCase(input);
                if(isColorValid(input)){
                    break;
                }
                else{
                    isValid = false;
                }
            } while(!isValid);
        }

        // Scanner'ı kapatınca NoSuchElementException atıyor!!!!!
        //scanner.close();

        return input;
    }

    private static boolean isColorValid(char input){
		switch(input){
			case 'r':
				colorCounter[0]++;
				if(colorCounter[0] > 4){
					System.out.println("Invalid color! There are already 4 red cubie face on cube!");
					System.out.print("Enter a valid color please: ");
					return false;
				}
				break;
			case 'b':
				colorCounter[1]++;
				if(colorCounter[1] > 4){
					System.out.println("Invalid color! There are already 4 blue cubie face on cube!");
					System.out.print("Enter a valid color please: ");
					return false;
				}
				break;
			case 'y':
				colorCounter[2]++;
				if(colorCounter[2] > 4){
					System.out.println("Invalid color! There are already 4 yellow cubie face on cube!");
					System.out.print("Enter a valid color please: ");
					return false;
				}
				break;
			case 'o':
				colorCounter[3]++;
				if(colorCounter[3] > 4){
					System.out.println("Invalid color! There are already 4 orange cubie face on cube!");
					System.out.print("Enter a valid color please: ");
					return false;
				}
				break;
			case 'g':
				colorCounter[4]++;
				if(colorCounter[4] > 4){
					System.out.println("Invalid color! There are already 4 green cubie face on cube!");
					System.out.print("Enter a valid color please: ");
					return false;
				}
				break;
			case 'w':
				colorCounter[5]++;
				if(colorCounter[5] > 4){
					System.out.println("Invalid color! There are already 4 white cubie face on cube!");
					System.out.print("Enter a valid color please: ");
					return false;
				}
				break;
			default:
				System.out.print("Enter a valid color please: ");
				return false;
		}

		return true;
	}
}
