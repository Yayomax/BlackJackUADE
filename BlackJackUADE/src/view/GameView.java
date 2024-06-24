package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView {
    private static GameView instancia;
    private JFrame frame;
    private JTextArea textArea;
    private JTextField inputField;
    private JButton submitButton;
    private String userInput;

    public static GameView getInstancia() {
        if (instancia == null) {
            instancia = new GameView();
        }
        return instancia;
    }

    private GameView() {
        frame = new JFrame("Blackjack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        textArea = new JTextArea();
        textArea.setEditable(false);

        inputField = new JTextField(10);
        submitButton = new JButton("Submit");
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userInput = inputField.getText();
                synchronized (GameView.this) {
                    GameView.this.notify();
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.add(inputField);
        inputPanel.add(submitButton);
        panel.add(inputPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public synchronized String getUserInput() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userInput;
    }

    public void printWelcomeMessage() {
        textArea.append("Bienvenido a Blackjack!\n");
    }

    public void printMinimunBetMessage() {
        textArea.append("La apuesta mínima es de $50\n");
    }

    public void printBetMessage(double dinero) {
        textArea.append("¿Cuánto quieres apostar? (tienes $" + dinero + ")\n");
    }

    public double getBetInput() {
        while (true) {
            try {
                double bet = Double.parseDouble(getUserInput());
                if (bet >= 50) {
                    return bet;
                } else {
                    textArea.append("La apuesta mínima es de $50\n");
                }
            } catch (NumberFormatException e) {
                textArea.append("Ingresa un número válido\n");
            }
        }
    }

    public void printPlayerCardsMessage() {
        textArea.append("Cartas jugador:\n");
    }

    public void printCard(String card) {
        textArea.append(card + "\n");
    }

    public void printDealerCardMessage() {
        textArea.append("Carta dealer:\n");
    }

    public void printPlayerHandValueMessage(int value) {
        textArea.append("Valor de tu mano: " + value + "\n");
    }

    public void printDealerHandValueMessage(int value) {
        textArea.append("Valor de la mano del dealer: " + value + "\n");
    }

    public void printBlackjackMessage() {
        textArea.append("Blackjack!\n");
    }

    public void printWantAnotherCardMessage() {
        textArea.append("¿Quieres otra carta? (s/n)\n");
    }

    public String getWantAnotherCardInput() {
        while (true) {
            String input = getUserInput();
            if (input.equals("s") || input.equals("n")) {
                return input;
            } else {
                textArea.append("Ingresa una opción válida\n");
            }
        }
    }

    public void printPlayerBustedMessage() {
        textArea.append("Te pasaste de 21, perdiste!\n");
    }

    public void printPlayerStandMessage() {
        textArea.append("Te plantaste!\n");
    }

    public void printDealerBustedMessage() {
        textArea.append("El dealer se pasó de 21!\n");
    }

    public void printDealerBlackjackMessage() {
        textArea.append("Blackjack del dealer!\n");
    }

    public void printDealerStandMessage() {
        textArea.append("El dealer se plantó!\n");
    }

    public void printLoseMessage() {
        textArea.append("El Dealer gana! Perdiste tu apuesta\n");
    }

    public void printWinMessage() {
        textArea.append("Has ganado!\n");
    }

    public void printTieMessage() {
        textArea.append("Empate!\n");
    }

    public void printNoMoneyMessage() {
        textArea.append("No te alcanza para jugar, fin del juego!\n");
    }

    public void printMoneyMessage(double dinero) {
        textArea.append("Dinero restante: " + dinero + "\n");
    }

    public void printContinuePlayingMessage() {
        textArea.append("¿Quieres seguir jugando? (s/n)\n");
    }

    public String getContinuePlayingInput() {
        while (true) {
            String input = getUserInput();
            if (input.equals("s") || input.equals("n")) {
                return input;
            } else {
                textArea.append("Ingresa una opción válida\n");
            }
        }
    }

    public void printThanksForPlayingMessage() {
        textArea.append("Gracias por jugar!\n");
    }
}
