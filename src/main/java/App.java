public class App {
    public static void main(String[] args) throws SnakeException {
        // Test Case 1: Initial State
        Snake snake1 = new Snake(3, new int[]{5,5}, Snake.Directions.RIGHT);
        print("Body: " + snake1.getSnake());
        print("Direction: " + snake1.getCurrentDirection());

        // Test Case 2: Modifying Direction
        snake1.setDirection(Snake.Directions.DOWN);
        print("Direction: " + snake1.getCurrentDirection());

        // Test Case 3: Moving Snake
        snake1.moveForward();
        print("Body after moving: " + snake1.getSnake());

        // Test Case 4: Growing Snake
        snake1.grow();
        print("Body after growing: " + snake1.getSnake());

        // Test Case 5: Prevent Turning Back on Itself
        snake1.setDirection(Snake.Directions.UP);
        print("Direction: " + snake1.getCurrentDirection());

        // Test Case 6: Moving in Different Directions
        Snake snake2 = new Snake(4, new int[]{2,2}, Snake.Directions.UP);
        print("Body: " + snake2.getSnake());
        print("Direction: " + snake2.getCurrentDirection());

        // Test Case 7: Growing and Changing Direction
        snake2.setDirection(Snake.Directions.LEFT);
        snake2.grow();
        print("Body: " + snake2.getSnake());
        print("Direction: " + snake2.getCurrentDirection());

        // Test Case 8: Growing Multiple Times
        snake2.grow();
        print("Body after growing: " + snake2.getSnake());

        // Test Case 9: Moving after changing Direction
        snake2.setDirection(Snake.Directions.LEFT);
        snake2.moveForward();
        print("Body after moving: " + snake2.getSnake());

    }

    public static void print(String msg) {
        System.out.println(msg);
    }
}
