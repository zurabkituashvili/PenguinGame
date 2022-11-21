package pgdp.oop;

import java.util.Arrays;

public class Antarktis extends Maze {

    private static int width, height;
    private static Penguin lostPenguin;
    private static Whale[] whales = new Whale[5];
    private static LeopardSeal[] leopardSeals = new LeopardSeal[20];
    private static Fish[] fishes = new Fish[500];
    private static PlayerPenguin playerPenguin;

    public static void main(String[] args) {
        width = 41;
        height = 41;
        antarktis = generateMaze(width, height);
        Animal.setAntarktis(antarktis);
        setupMaze();
        gameLoop();
        // Close the opened frame
        closeFrame();
    }

    private static void gameLoop() {
        while (true) {
            draw();
            if (!playerPenguin.alive || !lostPenguin.alive) break;
            if (currentEvent != NOTHING) {
                if (movePlayer()) {
                    break;
                }
                moveAll();
            }
            currentEvent = NOTHING;
        }
    }

    private static void printArr(Animal[][] antarktis) {
        System.out.println();
        System.out.println("=================================================================================================================");
        System.out.println();
        for (Animal[] animals : antarktis) {
            for (Animal animal : animals) {
                if (animal == null) {
                    System.out.print(" \t");
                }
                else if (Fish.class.equals(animal.getClass())) {
                    System.out.print("F\t");
                } else if (Penguin.class.equals(animal.getClass())) {
                    System.out.print("P\t");
                } else if (PlayerPenguin.class.equals(animal.getClass())) {
                    System.out.print("V\t");
                } else if (Whale.class.equals(animal.getClass())) {
                    System.out.print("W\t");
                } else if (LeopardSeal.class.equals(animal.getClass())) {
                    System.out.print("S\t");
                }
            }
            System.out.println();
        }
    }

    private static boolean movePlayer() {
        if (currentEvent == UP) {
            return playerPenguin.move(playerPenguin.x, playerPenguin.y - 1);
        }
        else if (currentEvent == DOWN) {
            return playerPenguin.move(playerPenguin.x, playerPenguin.y + 1);
        }
        else if (currentEvent == LEFT) {
            return playerPenguin.move(playerPenguin.x - 1, playerPenguin.y);
        }
        else if (currentEvent == RIGHT) {
            return playerPenguin.move(playerPenguin.x + 1, playerPenguin.y);
        }
        return false;
    }

    private static void moveAll() {

        for (Animal animal : whales) {
            animal.move();
        }

        for (Animal animal : leopardSeals) {
            animal.move();
        }

        lostPenguin.move();

        for (Animal animal : fishes) {
            animal.move();
        }

    }

    /**
     * Example Setup for easier Testing during development
     */
    private static void setupMaze() {
        int[] pos;
        playerPenguin = new PlayerPenguin(3, 3);
        antarktis[3][3] = playerPenguin;

        for (int i = 0; i < whales.length; i++) {
            pos = getRandomEmptyField();
            whales[i] = new Whale(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = whales[i];
        }

        for (int i = 0; i < leopardSeals.length; i++) {
            pos = getRandomEmptyField();
            leopardSeals[i] = new LeopardSeal(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = leopardSeals[i];
        }

        for (int i = 0; i < fishes.length; i++) {
            pos = getRandomEmptyField();
            fishes[i] = new Fish(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = fishes[i];
        }

        antarktis[20][20] = new Penguin(20, 20);
        lostPenguin = (Penguin) antarktis[20][20];
    }

}
