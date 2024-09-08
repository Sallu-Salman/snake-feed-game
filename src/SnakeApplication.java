import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SnakeApplication {
    static Cell head = null, tail = null;
    static boolean[][] foodPosition;
    static boolean[][] snakeBody;
    static int size;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Cell cell = new Cell(0, 0);
        head = tail = cell;

        size = 10;
        foodPosition = new boolean[size][size];
        snakeBody = new boolean[size][size];

        snakeBody[0][0] = true;
        fillFood();

        game: while(true) {
            renderSnake();
            System.out.print("Enter direction: ");
            char move = readStroke();

            int u, v;
            switch (move) {
                case 'u':
                    u = head.i - 1;
                    v = head.j;
                    break;
                case 'd':
                    u = head.i + 1;
                    v = head.j;
                    break;
                case 'l':
                    u = head.i;
                    v = head.j - 1;
                    break;
                default:
                    u = head.i;
                    v = head.j + 1;
                    break;
            }

            if(u < 0 || v < 0 || u == size || v == size || snakeBody[u][v]) {
                System.out.println("GAME OVER !!!");
                break game;
            }
            else if(foodPosition[u][v]) {
                growSnake(u, v);
            }
            else {
                moveSnake(u, v);
            }
        }
    }

    private static void moveSnake(int u, int v) {
        Cell cell = new Cell(u, v);

        snakeBody[u][v] = true;
        snakeBody[tail.i][tail.j] = false;

        cell.next = head;
        head.prev = cell;
        head = cell;

        tail = tail.prev;
        tail.next = null;
    }

    private static void growSnake(int u, int v) {
        Cell cell = new Cell(u, v);

        snakeBody[u][v] = true;

        cell.next = head;
        head.prev = cell;
        head = cell;

        foodPosition[u][v] = false;
    }

    private static char readStroke() {
        return scanner.nextLine().charAt(0);
    }

    private static void fillFood() {
        Random random = new Random();
        for(int i = 0; i < 15; i++) {
            int u = random.nextInt(size), v = random.nextInt(size);
            while(snakeBody[u][v]) {
                u = random.nextInt(size);
                v = random.nextInt(size);
            }
            foodPosition[u][v] = true;
        }
    }

    private static void renderSnake() {
        char[][] arena = new char[size][size];
        Arrays.stream(arena).forEach(a -> Arrays.fill(a, ' '));

        Cell cur = head;
        while(cur != null) {
            char sym = 'o';
            if(cur == head) {
                sym = 'O';
            }
            arena[cur.i][cur.j] = sym;
            cur = cur.next;
        }
        System.out.println();
        for(int i = 0; i < size; i++) {
            System.out.print("===");
        }
        System.out.println("==");
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if (j == 0) {
                    System.out.print("|");
                }
                if(foodPosition[i][j]) {
                    System.out.print(" - ");
                }
                else {
                    System.out.print(" " + arena[i][j] + " ");
                }
                if(j == size - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
        for(int i = 0; i < size; i++) {
            System.out.print("===");
        }
        System.out.println("==");
        System.out.println();
    }
}



















