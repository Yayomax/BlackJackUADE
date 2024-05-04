public class Dealer {
    private Carta[] cartas;
    private int numCartas;
    private boolean perdido;
    private boolean blackjack;

    public Dealer() {
        this.cartas = new Carta[17];
        this.numCartas = 0;
        this.perdido = false;
        this.blackjack = false;
    }

    public void resetMano() {
        numCartas = 0;
        perdido = false;
        blackjack = false;
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

    public void setCarta(Carta carta, int i) {
        cartas[i] = carta;
    }

    public void setPerdido(boolean perdido) {
        this.perdido = perdido;
    }

    public boolean getPerdido() {
        return perdido;
    }

    public void setBlackjack(boolean blackjack) {
        this.blackjack = blackjack;
    }

    public boolean getBlackjack() {
        return blackjack;
    }

}
