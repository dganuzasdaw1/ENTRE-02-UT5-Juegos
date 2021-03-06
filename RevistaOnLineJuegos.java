import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
/**
 * La clase representa a una tienda on-line en la
 * que se publican los juegos que se van lanzando al mercado
 * 
 * Un objeto de esta clase guarda en un array los juegos 
 *
 * @author - David Ganuza
 */
public class RevistaOnLineJuegos 
{
    private String nombre;
    private Juego[] juegos;
    private int total;

    /**
     * Constructor  
     * Crea el array de juegos al tama�o m�ximo indicado por la constante
     * e inicializa el resto de atributos
     */
    public RevistaOnLineJuegos(String nombre, int n) {
        this.nombre = nombre;
        juegos = new Juego[n];
        total = 0;
    }

    /**
     * Devuelve true si el array est� completo, false en otro caso
     */
    public boolean estaCompleta() {
        if(total == juegos.length){
            return true;
        }
        return false;
    }

    /**
     *    A�ade un nuevo juego solo si el array no est� completo y no existe otro juego
     *    ya con el mismo nombre.  Si no se puede a�adir se muestra los mensajes adecuados 
     *    (diferentes en cada caso)
     *    
     *    El juego se a�ade de tal forma que queda insertado en orden alfab�tico de t�tulo
     *    (de menor a mayor)
     *     !!OJO!! No hay que ordenar ni utilizar ning�n algoritmo de ordenaci�n
     *    Hay que insertar en orden 
     *    
     */
    public void add(Juego juego) {
        if(estaCompleta()){
            System.out.println("La revista ya esta completa");
        }else if(existeJuego(juego.getTitulo()) >= 0){
            System.out.println("El juego ya esta a�adido a la revista");
        }else{
            int posmin = 0;
            if(total == 0){
                juegos[total] = juego;
                total++;
            }else{
                for(int aux = total - 1; aux >= 0; aux--){
                    if( juegos[aux].getTitulo().compareToIgnoreCase(juego.getTitulo()) > 0){
                        juegos[aux + 1] = juegos[aux];
                    }else{
                        juegos[aux + 1] = juego;
                        total++;
                    }
                }
                juegos[0] = juego;
                total++;
            }
        }
    }

    /**
     * Efect�a una b�squeda en el array del juego cuyo titulo se
     * recibe como par�metro. Es ndiferente may�sculas y min�sculas
     * Si existe el juego devuelve su posici�n, si no existe devuelve -1
     */
    public int existeJuego(String titulo) {
        int posicion = -1;
        if(total > 0){
            for(int i = 0; i < total; i++){
                if(juegos[i].getTitulo().compareToIgnoreCase(titulo) == 0){
                    posicion = i;
                }
            }
        }
        return posicion;
    }

    /**
     * Representaci�n textual de la revista
     * Utiliza StringBuilder como clase de apoyo.
     * Se incluye el nombre de la  revista on-line.
     * (Ver resultados de ejecuci�n)
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int aux = 0;
        for(int i = 0; i < total; i++){
            if(existeJuego(juegos[i].getTitulo()) >= 0){
                sb.append("Ya est� publicado el juego " + juegos[i].getTitulo() + " en la revista on-line");
            }
        }
        sb.append("Los mejores juegos en nuestra revista " + this.nombre + "(" + total + " juegos)");
        for(int j = 0; j < total; j++){
            sb.append(juegos[j].toString());
            sb.append("\n----------------------");
        }
        return sb.toString();
    }

    /**
     *  Se punt�a el juego de t�tulo indicado con 
     *  la puntuaci�n recibida como par�metro. 
     *  La puntuaci�n es un valor entre 1 y 10 (asumimos esto como correcto)
     *  Si el juego no existe se muestra un mensaje en pantalla
     */
    public void puntuar(String titulo, int puntuacion) {
        int aux = existeJuego(titulo);
        if(aux >= 0){
            juegos[aux].puntuar(puntuacion);
        }else{
            System.out.println("El juego que desea puntuar no existe");
        }
    }

    /**
     * Devuelve un array con los nombres de los juegos 
     * con una valoraci�n media mayor a la indicada  
     * 
     * El array se devuelve todo en may�sculas y ordenado ascendentemente
     */
    public String[] valoracionMayorQue(double valoracion) {
        int mediaMasGrande = 1;
        int posMedia = 0;
        for(int j = 0; j < total; j++){
            if(juegos[j].getValoracionMedia() > valoracion){
                mediaMasGrande++;
            }
        }
        String[] mayorValoracion = new String[mediaMasGrande];
        for(int i = 0; i < total; i++){
            if(juegos[i].getValoracionMedia() > valoracion){
                mayorValoracion[posMedia] = juegos[i].getTitulo().toUpperCase();
                posMedia++;
            }
        }
        for(int h = 0; h < posMedia - 1; h++){
            if(mayorValoracion[h].compareTo(mayorValoracion[h + 1]) > 0){
                String aux = mayorValoracion[h];
                mayorValoracion[h] = mayorValoracion[h + 1];
                mayorValoracion[h + 1] = aux;
            }
        }
        return mayorValoracion;
    }

    /**
     * Borrar los juegos del g�nero indicado devolviendo
     * el n� de juegos borradas
     */
    public int borrarDeGenero(Genero genero) {
        int borrados = 0;
        for(int i = total; i > 0; i--){
            if(juegos[i].getGenero().equals(genero)){
                juegos[i] = juegos[i + 1];
                borrados++;
                total--;
            }
        }
        return borrados;
    }

    /**
     * Lee de un fichero de texto los datos de los juegos
     * con ayuda de un objeto de la  clase Scanner
     * y los guarda en el array. 
     */
    public void leerDeFichero() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("juegos.txt"));
            while (sc.hasNextLine()) {
                Juego juego = new Juego(sc.nextLine());
                this.add(juego);

            }

        } catch (IOException e) {
            System.out.println("Error al leer del fichero");
        } finally {
            sc.close();
        }

    }

}
