/*
 * Autores: 
 * - Juan Manuel Silvera
 * - Carlos Gomez
 * - Luisa Fernanda Palmera
 * 
 * Breve explicación del método de encriptación y desencriptación:
 * Se utilizó el método de sustitución por desplazamiento ASCII (variante del Cifrado César).
 * 1. Encriptación: Se recorre la frase de forma recursiva carácter por carácter. A cada 
 *    carácter se le suma un valor fijo (llave = 3) a su código ASCII, transformándolo 
 *    en un nuevo símbolo o letra, y el resultado final se guarda en "frase_encriptada.txt" 
 *    sobrescribiendo cualquier texto anterior.
 * 2. Desencriptación: Se lee la línea guardada en el archivo y, mediante una función 
 *    recursiva inversa, se resta el mismo valor fijo (llave = 3) al código ASCII de cada 
 *    carácter para reconstruir y mostrar la frase original en pantalla.
 * un cafecito para los que hicieron este código, gracias por su esfuerzo y dedicación.
 * fer cho
 * 
 */



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class arealimitrofe
{
 private static final int llave = 3; 
 private static final String archivoEntrada = "entrada.txt"; 
 private static final Scanner sc = new Scanner(System.in);

 public static void main(String[] args) 
 {
    mostrarMenu();

 }

 public static void mostrarMenu()
 {
    System.out.println("\n=== SISTEMA DE ENCRIPTACIÓN RECURSIVO ===");
    System.out.println("1. Leer frase y encriptar en archivo");
    System.out.println("2. Desencriptar frase desde el archivo y mostrar");
    System.out.println("3. Salir");
    System.out.print("Seleccione una opción: ");

    int opcion = sc.nextInt();
    sc.nextLine();

    switch (opcion)
    {
        case 1:
            System.out.print("Ingrese la frase a encriptar: ");
            String frase = sc.nextLine();
            String fraseEncriptada = encriptar(frase, 0);
            guardarEnArchivo(fraseEncriptada);
            System.out.println("Frase encriptada y guardada en el archivo.");

            mostrarMenu();
            break;
            
        case 2:
            String fraseDesencriptada = leerDesdeArchivo();
            if (fraseDesencriptada != null) 
            {
                String textoDesencriptado = desencriptar(fraseDesencriptada, 0);
                    System.out.println("\n-> Texto en el archivo (encriptado): " + fraseDesencriptada);
                    System.out.println("-> Frase desencriptada: " + textoDesencriptado);
            }
            mostrarMenu();
            break;

            case 3:
                System.out.println("\nSaliendo del programa...");
                break; 

            default:
                System.out.println("\nOpción no válida. Intente de nuevo.");
                mostrarMenu();
                break;
    }
 }

 public static String encriptar(String frase, int indice)
 {
    if (indice >= frase.length())
    {
        return "";
    }

    char caracterEncriptado = (char) (frase.charAt(indice) + llave);
    return caracterEncriptado + encriptar(frase, indice + 1);
 }

 public static String desencriptar(String texto, int indice) 
    {
        
        if (indice == texto.length()) 
        {
            return "";
        }
        char caracterDesencriptado = (char) (texto.charAt(indice) - llave);
        return caracterDesencriptado + desencriptar(texto, indice + 1);

    }

    public static void guardarEnArchivo(String textoEncriptado) 
    {
        try (FileWriter escritor = new FileWriter(archivoEntrada, false)) 
        {
            escritor.write(textoEncriptado);
            System.out.println("-> Frase encriptada y guardada con éxito: " + textoEncriptado);
        } catch (IOException e) 
        {
            System.out.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }

    public static String leerDesdeArchivo() 
    {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada))) 
        {
            return lector.readLine();
        } catch (IOException e) 
        {
            System.out.println("\nNo se encontró un archivo previo. Primero debe encriptar una frase (Opción 1).");
            return null;
        }
    }
}
