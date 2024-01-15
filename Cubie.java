public class Cubie {

    // since it is 2x2x2 cube, every cubie has 3 sides
    char top;
    char rightSide;
    char front;

    // constructor
    public Cubie(char front, char top, char rightSide){
        this.rightSide = rightSide;
        this.top = top;
        this.front = front;
    }

    /* cubie rotation methods */
    public Cubie rotateCW(){
        Cubie rotatedCubie = new Cubie(this.front, this.top, this.rightSide);
        //swapping for rotating cw change the cubie's faces
        char temp = top;
        rotatedCubie.top = front;
        rotatedCubie.front = rightSide;
        rotatedCubie.rightSide = temp;

        return rotatedCubie;
    }

    public Cubie rotateCounterCW(){
        Cubie rotatedCubie = new Cubie(this.front, this.top, this.rightSide);
        //swapping for rotating counter cw change the cubie's faces
        char temp = top;
        rotatedCubie.top = rightSide;
        rotatedCubie.rightSide = front;
        rotatedCubie.front = temp;

        return rotatedCubie;
    }
}
