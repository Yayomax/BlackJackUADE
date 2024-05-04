public class Game {
    private Jugador jugador;
    private Dealer dealer;
    private Mazo mazo;
    
    public Game() {
        jugador = new Jugador(2000);
        dealer = new Dealer();
        mazo = new Mazo();
        mazo.barajar();
        System.out.println("Bienvenido a Blackjack!");
    }

    public void jugar() {
        jugador.resetMano();
        dealer.resetMano();
        jugador.apostar();
        repartir();
        System.out.println("Cartas jugador: " + jugador.getCarta(0).toString() + " y " + jugador.getCarta(1).toString());
        System.out.println("Carta dealer: " + dealer.getCarta(0));
        System.out.println("Valor de tu mano: " + jugador.getValorMano());
        turnoJugador();
        if (jugador.getValorMano() > 21) {
            System.out.println("Te has pasado de 21, has perdido!");
        } else {
            System.out.println("Te plantaste con " + jugador.getValorMano() + " puntos");
            turnoDealer();
            terminarRonda();
        }
        if (jugador.getDinero() < 50) {
            System.out.println("No te alcanza para jugar, fin del juego!");
        }else{
            System.out.println("Dinero restante: " + jugador.getDinero());
            System.out.println("¿Quieres seguir jugando? (s/n)");
            if (System.console().readLine().equals("s")) {
                jugar();
            } else {
                System.out.println("Gracias por jugar!");
            }
        }
    }

    private void repartir() {
        jugador.setCarta(mazo.getCarta());
        dealer.setCarta(mazo.getCarta());
        jugador.setCarta(mazo.getCarta());
        dealer.setCarta(mazo.getCarta());
    }

    private void turnoJugador() {
        if (jugador.getValorMano() == 21) {
            jugador.setBlackjack(true);
            System.out.println("Blackjack!");
        } else {
            while (jugador.getValorMano() < 21 && !jugador.getPlantado()){
                System.out.println("¿Quieres otra carta? (s/n)");
                if (System.console().readLine().equals("s")) {
                    jugador.setCarta(mazo.getCarta());
                    System.out.println("Cartas del jugador: ");
                    for (int i = 0; i < jugador.getNumCartas(); i++) {
                        Carta carta = jugador.getCarta(i);
                        System.out.println(carta.toString());
                    }
                    System.out.println("Valor de la mano: " + jugador.getValorMano());
                } else {
                    jugador.setPlantado(true);
                }
            }
        }
    }

    private void turnoDealer(){
        System.out.println("Cartas del dealer: ");
        for (int i = 0; i < dealer.getNumCartas(); i++) {
            Carta carta = dealer.getCarta(i);
            System.out.println(carta.toString());
        }
        if (dealer.getValorMano() == 21) {
            dealer.setBlackjack(true);
        } else {
            while (dealer.getValorMano() < 17) {
                dealer.setCarta(mazo.getCarta());
                System.out.println("Cartas del dealer: ");
                for (int i = 0; i < dealer.getNumCartas(); i++) {
                    Carta carta = dealer.getCarta(i);
                    System.out.println(carta.toString());
                } 
            }
        }
        if (dealer.getValorMano() > 21) {
            System.out.println("El dealer se pasó de 21!");
            dealer.setPerdido(true);
        } else if (dealer.getBlackjack()) {
            System.out.println("Blackjack del dealer!");
        } else {
            System.out.println("El dealer se plantó con " + dealer.getValorMano() + " puntos");
        }
    }

    private void terminarRonda(){
        if (dealer.getBlackjack() && jugador.getBlackjack()){
            jugador.empatar();
        } else if (dealer.getBlackjack()) {
            System.out.println("El dealer Gana! Perdiste tu apuesta");
        } else if (jugador.getBlackjack()){
            jugador.blackjack();
        } else if (dealer.getPerdido() || jugador.getValorMano() > dealer.getValorMano()) {
            jugador.ganar();
        } else if (jugador.getValorMano() == dealer.getValorMano()) {
            jugador.empatar();
        } else {
            System.out.println("Has perdido!");
        }
    }

}

