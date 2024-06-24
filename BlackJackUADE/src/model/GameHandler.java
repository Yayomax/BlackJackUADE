package model;

public class GameHandler {
    private Jugador jugador;
    private Dealer dealer;
    private Mazo mazo;

    private static GameHandler instancia;

    public static GameHandler getInstancia() {
        if (instancia == null) {
            instancia = new GameHandler();
        }
        return instancia;
    }

    public GameHandler() {
        jugador = Jugador.getInstancia();
        dealer = Dealer.getInstancia();
        mazo = new Mazo();
        mazo.barajar();
    }

    public void resetMano() {
        jugador.resetMano();
        dealer.resetMano();
    }

    public void repartir() {
        jugador.setCarta(mazo.getCarta());
        dealer.setCarta(mazo.getCarta());
        jugador.setCarta(mazo.getCarta());
        dealer.setCarta(mazo.getCarta());
    }

    public boolean checkPlayerBlackjack() {
        if (jugador.getValorMano() == 21) {
            jugador.setBlackjack(true);
        }
        return jugador.getBlackjack();
    }

    public boolean checkDealerBlackjack() {
        if (dealer.getValorMano() == 21) {
            dealer.setBlackjack(true);
        }
        return dealer.getBlackjack();
    }

    public boolean hitCondition() {
        return jugador.getValorMano() < 21 && !jugador.getPlantado();
    }

    public void agregarCartaJugador() {
        jugador.setCarta(mazo.getCarta());
    }

    public void agregarCartaDealer() {
        dealer.setCarta(mazo.getCarta());
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Mazo getMazo() {
        return mazo;
    }
}


