package controller;

import model.GameHandler;
import view.GameView;

public class GameController {
    private GameHandler game;
    private GameView view;

    private static GameController instancia;

    public static GameController getInstancia() {
        if (instancia == null) {
            instancia = new GameController();
        }
        return instancia;
    }


    public GameController() {
        game = new GameHandler();
        view = GameView.getInstancia();
    }

    public void startGame() {
        // Bienvenida
        view.printWelcomeMessage();

        // Resetear mano
        game.resetMano();

        // Apuesta
        apostar();

        // Repartir
        repartir();

        // Turno jugador
        turnoJugador();

        if(game.getJugador().getValorMano() > 21){
            view.printPlayerBustedMessage();
        } else {
            view.printPlayerStandMessage();
            // Turno dealer
            turnoDealer();
            // Terminar ronda
            terminarRonda();
        }
        if (game.getJugador().getDinero() < 50) {
            view.printNoMoneyMessage();
        } else {
            view.printMoneyMessage(game.getJugador().getDinero());
            view.printContinuePlayingMessage();
            if (view.getContinuePlayingInput().equals("s")) { 
                startGame();
            } else {
                view.printThanksForPlayingMessage();
            }
        }
    }


    private void apostar() {
        view.printMinimunBetMessage();
        view.printBetMessage(game.getJugador().getDinero());
        game.getJugador().setApuesta(view.getBetInput());
        game.getJugador().setDinero(game.getJugador().getDinero() - game.getJugador().getApuesta());
    }

    public void repartir() {
        game.repartir();

        view.printPlayerCardsMessage();
        view.printCard(game.getJugador().getCarta(0).toString());
        view.printCard(game.getJugador().getCarta(1).toString());

        view.printDealerCardMessage();
        view.printCard(game.getDealer().getCarta(0).toString());

        view.printPlayerHandValueMessage(game.getJugador().getValorMano());
    }


    public void turnoJugador() {
        if (game.checkPlayerBlackjack()){
            view.printBlackjackMessage();
        } else {
            while (game.hitCondition()) {
                view.printWantAnotherCardMessage();
                String input = view.getWantAnotherCardInput();
                if (input.equals("s")) {  
                    game.agregarCartaJugador();

                    view.printPlayerCardsMessage();
                    for (int i = 0; i < game.getJugador().getNumCartas(); i++) {
                        view.printCard(game.getJugador().getCarta(i).toString());
                    }

                    view.printPlayerHandValueMessage(game.getJugador().getValorMano());

                } else if (input.equals("n")) {  
                    game.getJugador().setPlantado(true);
                    break;  
                }
            }
        }
    }


    public void turnoDealer() {
        view.printDealerCardMessage();
        view.printCard(game.getDealer().getCarta(0).toString());
        view.printCard(game.getDealer().getCarta(1).toString());
        view.printDealerHandValueMessage(game.getDealer().getValorMano());

        game.checkDealerBlackjack();
        
        while (game.getDealer().getValorMano() < 17) {
            game.agregarCartaDealer();

            view.printDealerCardMessage();
            for (int i = 0; i < game.getDealer().getNumCartas(); i++) {
                view.printCard(game.getDealer().getCarta(i).toString());
            }

            view.printDealerHandValueMessage(game.getDealer().getValorMano());
        }
        if (game.getDealer().getValorMano() > 21) {
            view.printDealerBustedMessage();
            game.getDealer().setPerdido(true);
        } 
        else if (game.getDealer().getBlackjack()) {
            view.printDealerBlackjackMessage(); 
        } 
        else {
            view.printDealerStandMessage();
        }
    }

    public void terminarRonda() {
        if (game.getDealer().getBlackjack() && game.getJugador().getBlackjack()) {
            view.printTieMessage();
            game.getJugador().empatar();
        }
        else if (game.getDealer().getBlackjack()){
            view.printLoseMessage();
        }
        else if (game.getJugador().getBlackjack()) {
            view.printBlackjackMessage();
            game.getJugador().blackjack();
        }
        else if (game.getDealer().getPerdido() || game.getJugador().getValorMano() > game.getDealer().getValorMano()) {
            view.printWinMessage();
            game.getJugador().ganar();
        }
        else if (game.getJugador().getValorMano() == game.getDealer().getValorMano()) {
            view.printTieMessage();
            game.getJugador().empatar();
        }
        else {
            view.printLoseMessage();
        }
    }
}
