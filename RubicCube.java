import java.util.ArrayList;

public class RubicCube {
    Cubie[] cubies;
    char[][] faces;

    ArrayList<char[]> facesList;

    // solved cube creation in no-args constructor
    public RubicCube() {
        facesList = new ArrayList<>();

        // 2x2x2 cube has 8 cubies
        cubies = new Cubie[8];
        cubies[0] = new Cubie('b', 'r', 'w');
        cubies[1] = new Cubie('w', 'r', 'g');
        cubies[2] = new Cubie('g', 'r', 'y');
        cubies[3] = new Cubie('y', 'r', 'b');
        cubies[4] = new Cubie('w', 'o', 'b');
        cubies[5] = new Cubie('g', 'o', 'w');
        cubies[6] = new Cubie('y', 'o', 'g');
        cubies[7] = new Cubie('b', 'o', 'y');

        // 2x2x2 cube has 6 faces, every face has 4 cubie side
        faces = new char[][]{
                {'r', 'r', 'r', 'r'},  //red
                {'w', 'w', 'w', 'w'},  //white
                {'b', 'b', 'b', 'b'},  //blue
                {'g', 'g', 'g', 'g'},  //green
                {'y', 'y', 'y', 'y'},  //yellow
                {'o', 'o', 'o', 'o'}   //orange
        };

        for (char[] face : faces) {
            facesList.add(face);
        }
    }

    public void printCube() {
        // 6 faces, 4 cubie sides
        char[][] printedCube = new char[6][4];

        // get faces and char colors to printedCube 2D array
        for (int i = 0; i < 6; i++) {
            char[] face = facesList.get(i);
            for (int j = 0; j < 4; j++) {
                printedCube[i][j] = face[j];
            }
        }

        System.out.println("        ---------");
        for (int i = 0; i < 2; i++) {
            System.out.println("        | " + printedCube[0][i * 2] + " | " + printedCube[0][i * 2 + 1] + " |");
        }
        System.out.println("|-------|-------|-------|-------|");
        for (int i = 0; i < 2; i++) {
            System.out.print("| " + printedCube[1][i * 2] + " | " + printedCube[1][i * 2 + 1] + " | ");
            System.out.print(printedCube[2][i * 2] + " | " + printedCube[2][i * 2 + 1] + " | ");
            System.out.println(printedCube[3][i * 2] + " | " + printedCube[3][i * 2 + 1] + " | "  + printedCube[5][i * 2] + " | " + printedCube[5][i * 2 + 1] + " |" );
        }
        System.out.println("|-------|-------|-------|-------|");
        for (int i = 0; i < 2; i++) {
            System.out.println("        | " + printedCube[4][i * 2] + " | " + printedCube[4][i * 2 + 1] + " |");
        }
        System.out.println("        ---------");
    }
}