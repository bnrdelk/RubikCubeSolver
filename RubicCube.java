import java.util.*;

public class RubicCube {
    ArrayList<char[]> facesList;

    Cubie cubie1,cubie2,cubie3,cubie4,cubie5,cubie6,cubie7,cubie8;
    char[] face1,face2,face3,face4,face5,face6;

    // no-arg const for initializing a solved cube (initial creation of cube)
    public RubicCube() {
        initializeSolvedCube();
    }

    // const for copying cube
    public RubicCube(RubicCube sourceCube) {
        initializeCubies(sourceCube);
        applyUpdates();
    }

    // method to initialize solved cube
    private void initializeSolvedCube() {
        // 2x2x2 cube has 8 cubies
        cubie1 = new Cubie('b', 'r', 'w');
        cubie2 = new Cubie('w', 'r', 'g');
        cubie3 = new Cubie('g', 'r', 'y');
        cubie4 = new Cubie('y', 'r', 'b');
        cubie5 = new Cubie('w', 'o', 'b');
        cubie6 = new Cubie('g', 'o', 'w');
        cubie7 = new Cubie('y', 'o', 'g');
        cubie8 = new Cubie('b', 'o', 'y');

        // 2x2x2 cube has 6 faces, every face has 4 cubie side
        face1 = new char[]{'r', 'r', 'r', 'r'};  //red
        face2 = new char[]{'w', 'w', 'w', 'w'};  //white
        face3 = new char[]{'b', 'b', 'b', 'b'};  //blue
        face4 = new char[]{'g', 'g', 'g', 'g'};  //green
        face5 = new char[]{'y', 'y', 'y', 'y'};  //yellow
        face6 = new char[]{'o', 'o', 'o', 'o'};  //orange

        facesList = new ArrayList<>();
        Collections.addAll(facesList, face1, face2, face3, face4, face5, face6);
    }

    // method to initialize cubies based on source cube
    private void initializeCubies(RubicCube sourceCube) {
        cubie1 = new Cubie(sourceCube.cubie1.front, sourceCube.cubie1.top, sourceCube.cubie1.side);
        cubie2 = new Cubie(sourceCube.cubie2.front, sourceCube.cubie2.top, sourceCube.cubie2.side);
        cubie3 = new Cubie(sourceCube.cubie3.front, sourceCube.cubie3.top, sourceCube.cubie3.side);
        cubie4 = new Cubie(sourceCube.cubie4.front, sourceCube.cubie4.top, sourceCube.cubie4.side);
        cubie5 = new Cubie(sourceCube.cubie5.front, sourceCube.cubie5.top, sourceCube.cubie5.side);
        cubie6 = new Cubie(sourceCube.cubie6.front, sourceCube.cubie6.top, sourceCube.cubie6.side);
        cubie7 = new Cubie(sourceCube.cubie7.front, sourceCube.cubie7.top, sourceCube.cubie7.side);
        cubie8 = new Cubie(sourceCube.cubie8.front, sourceCube.cubie8.top, sourceCube.cubie8.side);
    }

    // method to apply updates
    public void applyUpdates() {
        face1 = new char[]{cubie1.top, cubie2.top, cubie3.top, cubie4.top};
        face2 = new char[]{cubie1.side, cubie2.front, cubie5.front, cubie6.side};
        face3 = new char[]{cubie1.front, cubie4.side, cubie5.side, cubie8.front};
        face4 = new char[]{cubie2.side, cubie3.front, cubie6.front, cubie7.side};
        face5 = new char[]{cubie3.side, cubie4.front, cubie8.side, cubie7.front};
        face6 = new char[]{cubie5.top, cubie6.top, cubie7.top, cubie8.top};

        facesList = new ArrayList<>();
        Collections.addAll(facesList, face1, face2, face3, face4, face5, face6);
    }

    /*       -- ROTATION --       */
    // method to rotate the cube according to parameter
    public RubicCube rotate(char c) {
        RubicCube rotatedCube = new RubicCube(this);

        switch (c) {
            case 'u' -> rotateU(rotatedCube);
            case 'U' -> rotateUInverse(rotatedCube);
            case 'r' -> rotateR(rotatedCube);
            case 'R' -> rotateRInverse(rotatedCube);
            case 'f' -> rotateF(rotatedCube);
            case 'F' -> rotateFInverse(rotatedCube);
            default -> System.out.println("Invalid char for rotation, not rotated!!");
        }

        return rotatedCube;
    }

    /* up */
    private void rotateU(RubicCube cube) {
        Cubie temp = cubie1;
        cube.cubie1 = cubie2;
        cube.cubie2 = cubie3;
        cube.cubie3 = cubie4;
        cube.cubie4 = temp;
        cube.applyUpdates();
    }

    private void rotateUInverse(RubicCube cube) {
        //rotate cubies 1 2 3 and 4 CCW
        cube.cubie1 = cubie4;
        cube.cubie2 = cubie1;
        cube.cubie3 = cubie2;
        cube.cubie4 = cubie3;
        cube.applyUpdates();
    }

    /* right */
    private void rotateR(RubicCube cube) {
        cube.cubie1 = cube.cubie5.rotateCounterCW();
        cube.cubie2 = cube.cubie1.rotateCW();
        cube.cubie6 = cube.cubie2.rotateCounterCW();
        cube.cubie5 = cube.cubie6.rotateCW();
        cube.applyUpdates();
    }

    private void rotateRInverse(RubicCube cube) {
        cube.cubie1 = cube.cubie2.rotateCounterCW();
        cube.cubie2 = cube.cubie6.rotateCW();
        cube.cubie6 = cube.cubie5.rotateCounterCW();
        cube.cubie5 = cube.cubie1.rotateCW();
        cube.applyUpdates();
    }

    /* forward */
    private void rotateF(RubicCube cube) {
        cube.cubie1 = cube.cubie4.rotateCW();
        cube.cubie4 = cube.cubie8.rotateCounterCW();
        cube.cubie8 = cube.cubie5.rotateCW();
        cube.cubie5 = cube.cubie1.rotateCounterCW();
        cube.applyUpdates();
    }

    private void rotateFInverse(RubicCube cube) {
        cube.cubie1 = cube.cubie5.rotateCW();
        cube.cubie4 = cube.cubie1.rotateCounterCW();
        cube.cubie8 = cube.cubie4.rotateCW();
        cube.cubie5 = cube.cubie8.rotateCounterCW();
        cube.applyUpdates();
    }


    /*       -- PRINTING --       */
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