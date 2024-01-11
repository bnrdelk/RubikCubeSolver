public class Cubie {

    // since it is 2x2x2 cube, every cubie has 3 sides
    char top;
    char front;
    char side;

    // constructor
    public Cubie(char front, char top, char side){
        this.side = side;
        this.top = top;
        this.front = front;
    }

    public Cubie rotateCW(){
        // CW (clockwise) rotation
        return new Cubie(this.side, this.top, this.front);
    }

    public Cubie rotateCounterCW(){
        // counterclockwise rotation
        return new Cubie(this.front, this.side, this.top);
    }
}
