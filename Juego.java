/**
 * Un objeto de esta clase guarda información relativa a un juego
 * 
 * @author - David Ganuza
 */
public class Juego {
    private static final String SEPARADOR = ":";
    private String titulo;
    private Genero genero;
    private int year;
    private int[] valoraciones;

    /**
     *  Inicializa los atributos a partir de la información recibida
     *  Esta información se encuentra en linea
     */
    public Juego(String linea) {
        String[] informacion = linea.replaceAll(" ","").split(SEPARADOR);
        titulo = informacion[0];
        genero = Genero.valueOf(informacion[1].toUpperCase());
        year = Integer.parseInt(informacion[2]);
        valoraciones = new int[10];
        int aux = 3;
        for(int i = 0; i < valoraciones.length; i++){
            valoraciones[i] = Integer.parseInt(informacion[aux]);
            aux++;
        }
    }

    /**
     * accesor título
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * accesor género
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * accesor year
     */
    public int getYear() {
        return year;
    }

    /**
     * accesor valoraciones
     */
    public int[] getValoraciones() {
        return valoraciones;
    }

    /**
     * total votos emitidos
     */
    public int getVotos() {
        int total = 0;
        for(int i = 0; i < valoraciones.length; i++){
            total += valoraciones[i];
        }
        return total;
    }

    /**
     *  obtener valoración media
     */
    public double getValoracionMedia() {
        double media = 0;
        for(int i = 0; i < valoraciones.length; i++){
            for(int j = 0; j < valoraciones[i]; j++){
                media += i;
            }
        }
        media = media / getVotos();
        return media;
    }

    /**
     *  Un usuario puntúa el juego con un valor entre 1 y 10 
     */
    public void puntuar(int puntuacion) {
        if(puntuacion >= 1 && puntuacion <= 10){
            valoraciones[puntuacion]++;
        }
    }

    /**
     * Representación textual del juego 
     * (Ver enunciado)
     */
    public String toString() {
        return titulo + "\nGénero: " + this.genero +
        "| Lanzamiento: " + this.year +
        "\nValoración (" + getVotos() + " votos): "
        + String.format("%.2f", this.getValoracionMedia());

    }

    public static void main(String[] args) {
        Juego juego1 = new Juego(
                "Steep: deporte: 2016  : 0:0:0:0: 0: 0:0:0:12:  10");
        System.out.println(juego1.toString());

        Juego juego2 = new Juego(
                "For the King: estrategia: 2018  : 0:0:0:7: 12: 0:33:13:2: 0");
        System.out.println(juego2.toString());

    }
}
