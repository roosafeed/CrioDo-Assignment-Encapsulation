import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Snake {
    // Should display Encapsulation concepts
    // Assumptions : Snake Box size is 100x100 by default

    private Directions currentDirection;
    private List<Point> snake;
    public enum Directions {
        LEFT, RIGHT, UP, DOWN
    }
    private final int[] snakeBox = {100, 100};

    Snake(int size, int[] initPos, Directions initDir) throws SnakeException {
        if (initPos.length != 2 || initPos[0] > snakeBox[0] || initPos[1] > snakeBox[1]) {
            throw new InvalidInitialPositionException();
        }

        if (size > snakeBox[0]) {
            throw new InvalidSnakeSizeException();
        }

        this.currentDirection = initDir;
        this.snake = new ArrayList<>();
        makeSnake(size, initPos);
//        printSnake();
    }

    //Initialise snake
    private void makeSnake(int size, int[] initPos) {
        if (this.currentDirection == Directions.RIGHT) {
            for (int i = 0; i < size; i++) {
                this.snake.add(new Point(((initPos[0] + snakeBox[0] - i)) % snakeBox[0], initPos[1]));
            }
        }
        else if (this.currentDirection == Directions.LEFT) {
            for (int i = 0; i < size; i++) {
                this.snake.add(new Point((initPos[0] + i) % snakeBox[0], initPos[1]));
            }
        }
        else if (this.currentDirection == Directions.UP) {
            for (int i = 0; i < size; i++) {
                this.snake.add(new Point(initPos[0], (initPos[1] + snakeBox[1] - i) % snakeBox[1]));
            }
        }
        else if (this.currentDirection == Directions.DOWN) {
            for (int i = 0; i < size; i++) {
                this.snake.add(new Point(initPos[0], (initPos[1] + i) % snakeBox[1]));
            }
        }
    }

    private void printSnake() {
        System.out.println(getSnakeStr());
    }

    private String getSnakeStr() {
        StringBuilder snakeStr = new StringBuilder("[");
        Iterator<Point> snakeIter = snake.iterator();
        while (snakeIter.hasNext()) {
            Point p = snakeIter.next();
            snakeStr.append(String.format("(%d,%d)", p.x, p.y));
            if(snakeIter.hasNext()) {
                snakeStr.append(",");
            }
        }
        snakeStr.append("]");

        return snakeStr.toString();
    }

    public void moveForward() {
        Point newHeadPos = new Point(this.snake.get(0));
        switch (this.currentDirection) {
            case LEFT: newHeadPos.setLocation((newHeadPos.getX() + snakeBox[0] - 1) % snakeBox[0], newHeadPos.getY());
                break;
            case RIGHT: newHeadPos.setLocation((newHeadPos.getX() + 1) % snakeBox[0], newHeadPos.getY());
                break;
            case DOWN: newHeadPos.setLocation(newHeadPos.getX(), (newHeadPos.getY() + snakeBox[1] - 1) % snakeBox[1]);
                break;
            case UP: newHeadPos.setLocation(newHeadPos.getX(), (newHeadPos.getY() + 1) % snakeBox[1]);
                break;
            default:
                break;
        }

        // move the snake forward
        for (int i = this.snake.size() - 1; i > 0; i--) {
            this.snake.get(i).setLocation(this.snake.get(i-1));
        }

        this.snake.get(0).setLocation(newHeadPos);

//        printSnake();
    }

    public void grow() {
        Point newHeadPos = new Point(this.snake.get(0));
        switch (this.currentDirection) {
            case LEFT: newHeadPos.setLocation((newHeadPos.getX() + snakeBox[0] - 1) % snakeBox[0], newHeadPos.getY());
                break;
            case RIGHT: newHeadPos.setLocation((newHeadPos.getX() + 1) % snakeBox[0], newHeadPos.getY());
                break;
            case DOWN: newHeadPos.setLocation(newHeadPos.getX(), (newHeadPos.getY() + snakeBox[1] - 1) % snakeBox[1]);
                break;
            case UP: newHeadPos.setLocation(newHeadPos.getX(), (newHeadPos.getY() + 1) % snakeBox[1]);
                break;
            default:
                break;
        }

        // grow the snake by adding one unit to the head
        this.snake.add(0, newHeadPos);

//        printSnake();
    }

    public void setDirection(Directions direction) {
        if ((this.currentDirection == Directions.LEFT && direction != Directions.RIGHT) ||
            (this.currentDirection == Directions.RIGHT && direction != Directions.LEFT) ||
            (this.currentDirection == Directions.DOWN && direction != Directions.UP) ||
            (this.currentDirection == Directions.UP && direction != Directions.DOWN)) {
            this.currentDirection = direction;
        }
    }

    public Directions getCurrentDirection() {
        return this.currentDirection;
    }

    public String getSnake() {
        return getSnakeStr();
    }

    public void setBoxSize(int w, int h) {
        this.snakeBox[0] = w;
        this.snakeBox[1] = h;
    }
}

class InvalidSnakeSizeException extends SnakeException {
    InvalidSnakeSizeException() {
        super("The size of the snake is bigger than the box width");
    }
}

class InvalidInitialPositionException extends SnakeException {

    InvalidInitialPositionException() {
        super("The initial position is invalid");
    }
}
