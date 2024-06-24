import controller.GameController;

public final class App {
    public static void main(String[] args) {
        GameController controller = GameController.getInstancia();
        controller.startGame();
    }
}
