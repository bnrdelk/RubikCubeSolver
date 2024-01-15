import java.util.*;

public class RubikCube {
    // previous versions of the rotation and cube
    char rotation;
    RubikCube oldCube = null;

    //List of the moves that needed to apply to the cube to solve
    ArrayList<Character> solution = new ArrayList<Character>();

    ArrayList<char[]> facesList;

    Cubie cubie1,cubie2,cubie3,cubie4,cubie5,cubie6,cubie7,cubie8;
    char[] face1,face2,face3,face4,face5,face6;

    // no-arg const for initializing a solved cube (initial creation of cube)
    public RubikCube() {
        initializeSolvedCube();
    }

    // const - used for copying the cube
    public RubikCube(RubikCube sourceCube) {
        // get rotate
        rotation = sourceCube.rotation;

        initializeCubies(sourceCube);

        oldCube = sourceCube.oldCube;
        applyUpdates();
    }

    // method to initialize solved cube
    private void initializeSolvedCube() {
        // 2x2x2 cube has 6 faces, every face has 4 cubie side
        face1 = new char[]{'r', 'r', 'r', 'r'};
        face2 = new char[]{'w', 'w', 'w', 'w'};
        face3 = new char[]{'b', 'b', 'b', 'b'};
        face4 = new char[]{'g', 'g', 'g', 'g'};
        face5 = new char[]{'y', 'y', 'y', 'y'};
        face6 = new char[]{'o', 'o', 'o', 'o'};

        facesList = new ArrayList<>();
        Collections.addAll(facesList, face1, face2, face3, face4, face5, face6);

        // 2x2x2 cube has 8 cubies
        cubie1 = new Cubie('b', 'r', 'w');
        cubie2 = new Cubie('w', 'r', 'g');
        cubie3 = new Cubie('g', 'r', 'y');
        cubie4 = new Cubie('y', 'r', 'b');
        cubie5 = new Cubie('w', 'o', 'b');
        cubie6 = new Cubie('g', 'o', 'w');
        cubie7 = new Cubie('y', 'o', 'g');
        cubie8 = new Cubie('b', 'o', 'y');

    }

    // method to initialize cubies based on source cube
    private void initializeCubies(RubikCube sourceCube) {
        cubie1 = new Cubie(sourceCube.cubie1.front, sourceCube.cubie1.top, sourceCube.cubie1.rightSide);
        cubie2 = new Cubie(sourceCube.cubie2.front, sourceCube.cubie2.top, sourceCube.cubie2.rightSide);
        cubie3 = new Cubie(sourceCube.cubie3.front, sourceCube.cubie3.top, sourceCube.cubie3.rightSide);
        cubie4 = new Cubie(sourceCube.cubie4.front, sourceCube.cubie4.top, sourceCube.cubie4.rightSide);
        cubie5 = new Cubie(sourceCube.cubie5.front, sourceCube.cubie5.top, sourceCube.cubie5.rightSide);
        cubie6 = new Cubie(sourceCube.cubie6.front, sourceCube.cubie6.top, sourceCube.cubie6.rightSide);
        cubie7 = new Cubie(sourceCube.cubie7.front, sourceCube.cubie7.top, sourceCube.cubie7.rightSide);
        cubie8 = new Cubie(sourceCube.cubie8.front, sourceCube.cubie8.top, sourceCube.cubie8.rightSide);
    }

    // method to apply updates
    public void applyUpdates() {
        face1 = new char[]{cubie1.top, cubie2.top, cubie3.top, cubie4.top};
        face2 = new char[]{cubie1.rightSide, cubie2.front, cubie5.front, cubie6.rightSide};
        face3 = new char[]{cubie1.front, cubie4.rightSide, cubie5.rightSide, cubie8.front};
        face4 = new char[]{cubie2.rightSide, cubie3.front, cubie6.front, cubie7.rightSide};
        face5 = new char[]{cubie3.rightSide, cubie4.front, cubie8.rightSide, cubie7.front};
        face6 = new char[]{cubie5.top, cubie6.top, cubie7.top, cubie8.top};

        facesList = new ArrayList<>();
        Collections.addAll(facesList, face1, face2, face3, face4, face5, face6);
    }

    /*       -- ROTATION --       */
    // method to rotate the cube according to parameter
    public RubikCube rotate(char c) {
        RubikCube rotated = new RubikCube(this); // Initialize the rotatedCube variable

        switch (c) {
            case 'u': // Rotate the upper face clockwise
                rotateUpperFaceCW(rotated);
                break;
            case 'U': // Rotate the upper face counterclockwise
                rotateUpperFaceCCW(rotated);
                break;
            case 'r': // rotate the right face clockwise
                rotateRightFaceCW(rotated);
                break;
            case 'R': // Rotate the right face counterclockwise
                rotateRightFaceCCW(rotated);
                break;
            case 'f': // Rotate the front face clockwise
                rotateFrontFaceCW(rotated);
                break;
            case 'F': //Rotate the front face counterclockwise
                rotateFrontFaceCCW(rotated);
                break;
            case 'e': // Exits the input phase
                break;
            default:
                System.out.println("Invalid char for rotation, not rotated!!");
                break;
        }

        return rotated;
    }

    // Rotation methods for faces
    public void rotateUpperFaceCW(RubikCube rotated){
        Cubie temp = cubie1;
        rotated.cubie1 = cubie2;
        rotated.cubie2 = cubie3;
        rotated.cubie3 = cubie4;
        rotated.cubie4 = temp;
        rotated.applyUpdates();
    }

    public void rotateUpperFaceCCW(RubikCube rotated){
        rotated.cubie1 = cubie4;
        rotated.cubie2 = cubie1;
        rotated.cubie3 = cubie2;
        rotated.cubie4 = cubie3;
        rotated.applyUpdates();
    }

    public void rotateRightFaceCW(RubikCube rotated){
        rotated.cubie1 = cubie5.rotateCounterCW();
        rotated.cubie2 = cubie1.rotateCW();
        rotated.cubie6 = cubie2.rotateCounterCW();
        rotated.cubie5 = cubie6.rotateCW();
        rotated.applyUpdates();
    }

    public void rotateRightFaceCCW(RubikCube rotated){
        rotated.cubie1 = cubie2.rotateCounterCW();
        rotated.cubie2 = cubie6.rotateCW();
        rotated.cubie6 = cubie5.rotateCounterCW();
        rotated.cubie5 = cubie1.rotateCW();
        rotated.applyUpdates();
    }

    public void rotateFrontFaceCW(RubikCube rotated){
        rotated.cubie1 = cubie4.rotateCW();
        rotated.cubie4 = cubie8.rotateCounterCW();
        rotated.cubie8 = cubie5.rotateCW();
        rotated.cubie5 = cubie1.rotateCounterCW();
        rotated.applyUpdates();
    }

    public void rotateFrontFaceCCW(RubikCube rotated){
        rotated.cubie1 = cubie5.rotateCW();
        rotated.cubie4 = cubie1.rotateCounterCW();
        rotated.cubie8 = cubie4.rotateCW();
        rotated.cubie5 = cubie8.rotateCounterCW();
        rotated.applyUpdates();
    }

    // given a list of rotations, return a cube with the rotations applied
    public RubikCube rotate(List<Character> rotations) {
        RubikCube current = this;

        for (char r : rotations) {
            current = current.rotate(r);
        }

        return current;
    }

    // BFS algorithm to solve the rubik's cube
    // returns the list of rotations needed to solve the cube
    public List<Character> solve() {

        if(this.isSolved()){
            System.out.println("The cube is already solved.");
            return new ArrayList<>();
        }

        HashSet<RubikCube> visited = new HashSet<>();
        Queue<RubikCube> queue = new LinkedList<RubikCube>();
        visited.add(this);
        queue.add(this);

        while (!queue.isEmpty()) {
            RubikCube state = queue.poll();
        
            for (RubikCube current : state.getNeighborStates()) {
                if (current.isSolved()) {
                    return handleSolution(current);
                }
        
                if (!visited.contains(current)) {
                    visited.add(current);
                    queue.add(current);
                }
            }
        }

        return new ArrayList<>();
    }

    // Returns the rotaion list to solve the cube
    public List<Character> handleSolution(RubikCube neighbor){
        solution.clear();

        while (neighbor.oldCube != null) {
            char move = neighbor.rotation;
            solution.add(move);
            neighbor = neighbor.oldCube;
        }

        Collections.reverse(solution);
        System.out.println(solution);
        return solution;
    }

    // method to get neighbr states according to rotation types
    public Iterable<RubikCube> getNeighborStates() {
        List<RubikCube> states = new ArrayList<RubikCube>();
        char[] rotates = {'u', 'U', 'R', 'r', 'F', 'f'};

        for (char r : rotates) {
            RubikCube neighbor = new RubikCube(this);
            neighbor.oldCube = this;
            neighbor.rotation = r;

            states.add(neighbor.rotate(r));
        }

        return states;
    }

    // Checks if the current state is equal to solved state
    public boolean equals(RubikCube otherCube){
        for (int i = 0; i < 6; i++) {
            if (!Arrays.equals(facesList.get(i), otherCube.facesList.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isSolved(){
        return (this.equals(new RubikCube()));
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