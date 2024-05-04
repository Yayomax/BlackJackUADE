public class Jugador {
    private Carta[] cartas;
    private int numCartas;
    private double dinero;
    private double apuesta;
    private boolean plantado;
    private boolean blackjack;

    public Jugador(int dineroInicial) {
        this.cartas = new Carta[17];
        this.numCartas = 0;
        this.dinero = dineroInicial;
        this.apuesta = 0;
        this.plantado = false;
        this.blackjack = false;
    }

    public void resetMano() {
        this.numCartas = 0;
        this.apuesta = 0;
        this.plantado = false;
        this.blackjack = false;
    }

    public void apostar() {
        System.out.println("Minimo de apuesta: $50");
        System.out.println("¿Cuánto quieres apostar? (tienes $" + this.getDinero() + ")");
        int apuesta = Integer.parseInt(System.console().readLine());
        while (apuesta < 50 || apuesta > this.getDinero()) {
            System.out.println("Apuesta no válida, intenta de nuevo");
            System.out.println("¿Cuánto quieres apostar? (tienes $" + this.getDinero() + ")");
            apuesta = Integer.parseInt(System.console().readLine());
        }
        this.setApuesta(apuesta);
        this.setDinero(this.getDinero() - apuesta);
    }

    public void blackjack() {
        System.out.println("Blackjack!");
        this.setDinero(this.getDinero() + this.getApuesta() * 2.5);
        this.setApuesta(0);
    }

    public void ganar() {
        System.out.println("Has ganado!");
        this.setDinero(this.getDinero() + this.getApuesta() * 2);
        this.setApuesta(0);
    }

    public void empatar() {
        System.out.println("Empate!");
        this.setDinero(this.getDinero() + this.getApuesta());
        this.setApuesta(0);
    }

    public int getValorMano() {
        int valor = 0;
        int ases = 0;
        for (int i = 0; i < numCartas; i++) {
            if (cartas[i].getValorNumerico() == 1) {
                ases++;
            }
            valor += cartas[i].getValorNumerico();
        }
        while (valor < 12 && ases > 0) {
            valor += 10;
            ases--;
        }
        return valor;
    }
    
    public void setCarta(Carta carta) {
        cartas[numCartas] = carta;
        numCartas++;
    }

    public Carta[] getCartas() {
        return cartas;
    }

    public Carta getCarta(int i) {
        return cartas[i];
    }
    
    public void setNumCartas(int numCartas) {
        this.numCartas = numCartas;
    }

    public int getNumCartas() {
        return numCartas;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public double getDinero() {
        return dinero;
    }

    public void setApuesta(double apuesta) {
        this.apuesta = apuesta;
    }

    public double getApuesta() {
        return apuesta;
    }

    public void setPlantado(boolean plantado) {
        this.plantado = plantado;
    }

    public boolean getPlantado() {
        return plantado;
    }

    public void setBlackjack(boolean blackjack) {
        this.blackjack = blackjack;
    }

    public boolean getBlackjack() {
        return blackjack;
    }

}
