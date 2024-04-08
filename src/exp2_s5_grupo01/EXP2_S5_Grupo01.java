
package exp2_s5_grupo01;

import java.util.Scanner;


public class EXP2_S5_Grupo01 {

   //Variables estáticas
  static int entradasCompradas = 0;
  static int opcionMenu = 0;
  static double totalPagarEntradas = 0;
  static double totalPagarEntradasDescuento = 0;
  static double descuento = 0;
  static int edad = 0;

  //Variables de instancia o 'globales'

  public static void main(String[] args) {

    Scanner entradaUsuario = new Scanner(System.in);

    String ubicacionTexto = "";
    int precioEntrada = 0;
    double precioEntradaConDescuento = 0;
    String descuentoTexto = "";
    int estudiante = 0;
    int promocionPalco = 0;

    /* MENU GESTION ENTRADAS */
    do {
      System.out.println(" -----------------------------------------------------------");
      System.out.println("            SISTEMA GESTIÓN ENTRADAS TEATRO MORO            ");
      System.out.println(" -----------------------------------------------------------");
      System.out.println("                           Menú                             ");
      System.out.println("------------------------------------------------------------");
      System.out.println("    [1]Mostrar promociones");
      System.out.println("    [2]Comprar Entradas");
      System.out.println("    [3]Salir");
      System.out.println("------------------------------------------------------------");

      opcionMenu = entradaUsuario.nextInt();

      if(opcionMenu < 1 || opcionMenu > 3){
        System.out.println("Ingresa el número correspondiente a la acción que deseas realizar");
      }
    } while ( opcionMenu < 1 || opcionMenu > 3);

    do {
      switch (opcionMenu){
        /* CASO 1: VER PROMOCIONES */
        case 1:

          System.out.println("Promociones");
          System.out.println("----------------------------------------");
          System.out.println("10% descuento pagando con TC");
          System.out.println("Lleva un snack gratis con entrada Palco");

          // Mostrar menú simplificado para continuar con el flujo.
          do {
            System.out.println("[2]Comprar entradas [3]Salir");
            opcionMenu = entradaUsuario.nextInt();
            if (opcionMenu != 2 && opcionMenu != 3){
              System.out.println("Ingresa el número correspondiente a la acción");
            }
          } while (opcionMenu != 2 && opcionMenu != 3);
          break;

        /* CASO 2: COMPRAR ENTRADAS */
        case 2:
          // Elegir la ubicación de la entrada
          int ubicacion;
          do {
            System.out.println("Entradas");
            System.out.println("---------------------------------------------");
            System.out.println("[1]VIP [2]Platea Baja [3]Platea Alta [4]Palco");
            ubicacion = entradaUsuario.nextInt();
            if(ubicacion == 0 || ubicacion > 4){
              System.out.println("Ingresa el número correspondiente a la entrada");
            }
          } while(ubicacion == 0 || ubicacion > 4);

          switch (ubicacion){
            case 1:
              precioEntrada = 25000;
              ubicacionTexto = "VIP";
              break;

            case 2:
              precioEntrada = 19000;
              ubicacionTexto = "Platea Baja";
              break;

            case 3:
              precioEntrada = 11000;
              ubicacionTexto = "Platea Alta";
              break;

            case 4:
              promocionPalco += 1;
              precioEntrada = 7200;
              ubicacionTexto = "Palco";
              break;
          }

          double descuentoTerceraEdad = 0.15;
          double descuentoEstudiante = 0.10;

          // Ingresar edad
          do {
            System.out.println("Ingresa la edad del comprador");
            edad = entradaUsuario.nextInt();

            //Aplica el descuento automáticamente si el comprador está en el rango de edad escolar.
            if(edad > 5 && edad < 18){
              descuentoTexto = "Tienes un 10% de descuento por ser estudiante";
              descuento = precioEntrada * descuentoEstudiante;
              precioEntradaConDescuento = precioEntrada - descuento;
              estudiante = 1;
            }
            //Se verifica que si se aplica el descuento estudiante a mayores de 18 y menores de 60
            else if (edad > 17 && edad < 60) {
              do {
                System.out.println("El comprador, ¿es estudiante?");
                System.out.println("[1]Si [2]No");
                estudiante = entradaUsuario.nextInt();
                if(estudiante != 1 && estudiante != 2){
                  System.out.println("Ingresa el número correspondiente si es estudiante.");
                }
              } while(estudiante != 1 && estudiante != 2);

              if(estudiante == 1){
                descuentoTexto = "Tienes un 10% de descuento por ser estudiante";
                descuento = precioEntrada * descuentoEstudiante;
                precioEntradaConDescuento = precioEntrada - descuento;
              }
            }
            // Se aplica el descuento de tercera edad
            else if (edad >= 60 && edad <= 100) {
              descuentoTexto = "Tienes un 15% de descuento por ser tercera edad";
              descuento = precioEntrada * descuentoTerceraEdad;
              precioEntradaConDescuento = precioEntrada - descuento;
            }

            if(edad < 6 || edad > 100){
              System.out.println("Has ingresado una edad no permitida");
            }
          } while (edad < 6 || edad > 100);

          // Resumen cada compra
          entradasCompradas += 1;

          if( estudiante == 1 || edad >= 60){
            totalPagarEntradasDescuento = totalPagarEntradasDescuento + precioEntradaConDescuento;
          } else{
            totalPagarEntradas = totalPagarEntradas + precioEntrada;
          }

          System.out.println("Resumen compra entrada " + entradasCompradas);
          System.out.println("-------------------------------------");
          System.out.println("Ubicación: " + ubicacionTexto);
          System.out.println("Precio Entrada: $" + precioEntrada);
          if( estudiante == 1 || edad >= 60){
            System.out.println(descuentoTexto + " equivalente a : $" + descuento);
            System.out.println("Total a pagar por la entrada: $" + precioEntradaConDescuento);
          }
          if( ubicacion == 4 && promocionPalco > 0 ){
            System.out.println("Te llevas 1 snack gratis por cada entrada en ubicación de Palco");
          }

          //Menú para comprar otra entrada o pagar
          do {
            System.out.println("[2]Comprar otra entrada [3]Pagar entradas y salir");
            opcionMenu = entradaUsuario.nextInt();
            if (opcionMenu != 2 && opcionMenu != 3){
              System.out.println("Ingresa el número correspondiente a la acción");
            }
          } while (opcionMenu != 2 && opcionMenu != 3);
          break;
      }
    } while( opcionMenu != 3);

    if(entradasCompradas == 0){
      System.out.println("Has salido de la aplicación");
    } else {
      System.out.println("---------------------------------------------------------------------");
      System.out.println("                         RESUMEN DE LA COMPRA                        ");
      System.out.println("---------------------------------------------------------------------");
      System.out.println("El total a pagar por las " + entradasCompradas + " entradas es de: ");
      System.out.println("$" + (totalPagarEntradas + totalPagarEntradasDescuento));
      System.out.println("---------------------------------------------------------------------");
      System.out.println("(Si pagas con Tarjeta de crédito tiene un 10% de descuento adicional)");

      int opcionPago;
      do {
        System.out.println("[1]Pagar con tarjeta de crédito [2]Pagar con efectivo");
        opcionPago = entradaUsuario.nextInt();
        if (opcionPago != 1 && opcionPago != 2){
          System.out.println("Ingresa el número correspondiente al método de pago.");
        }
      } while (opcionPago != 1 && opcionPago != 2);

      if (opcionPago == 1){
        double descuentoPromocionTarjetaCredito = (totalPagarEntradas + totalPagarEntradasDescuento) * 0.1;
        double totalPagarEntradasTarjetaCredito = (totalPagarEntradas + totalPagarEntradasDescuento) - descuentoPromocionTarjetaCredito;
        System.out.println("Total con tarjeta de crédito");
        System.out.println("$" + totalPagarEntradasTarjetaCredito);
      } else {
        System.out.println("Total a pagar");
        System.out.println("$" + (totalPagarEntradas + totalPagarEntradasDescuento));
      }

      System.out.println("---------------------------------------------------------------------");
      System.out.println("                          Disfruta la función"                        );
      System.out.println("---------------------------------------------------------------------");


    }

  }
  
}
