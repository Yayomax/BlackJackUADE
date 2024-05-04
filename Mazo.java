public class Mazo {
    private Carta[] cartas;
    private int cantidadCartas;
    
    public Mazo() {
        cartas = new Carta[52*3];
        cantidadCartas = 0;
        String[] palos = {"Corazones", "Diamantes", "Treboles", "Picas"};
        String[] valores = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int[] valoresNumericos = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 13; k++) {
                    cartas[cantidadCartas] = new Carta(valores[k], palos[j], valoresNumericos[k]);
                    cantidadCartas++;
                }
            }
        }  
    }
    
    public void barajar() {
        for (int i = 0; i < cantidadCartas; i++) {
            int random = (int) (Math.random() * cantidadCartas);
            Carta temp = cartas[i];
            cartas[i] = cartas[random];
            cartas[random] = temp;
        }
    }
    
    public Carta getCarta() {
        Carta carta = cartas[cantidadCartas - 1];
        cantidadCartas--;
        return carta;
    }
    
    public Carta[] getCartas() {
        return cartas;
    }
    
    public int getCantidadCartas() {
        return cantidadCartas;
    }
    
    public void setCantidadCartas(int cantidadCartas) {
        this.cantidadCartas = cantidadCartas;
    }
    
    public void setCarta(Carta carta, int i) {
        cartas[i] = carta;
    }
}
