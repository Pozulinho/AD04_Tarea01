
import tareas.Escritura;
import tareas.T01CrearMonitor;
import tareas.T02CrearActividad;
import tareas.T03ConsultarActividad;
import tareas.T04CrearCliente;
import tareas.T05CrearBorrarReserva;
import tareas.T07ListarActividad;

public class Menu {

    public static void main(String[] args) {
    	int lista = 0;
    	do {
        	lista = escribirDecision();
        	if(lista == 1) {
        		T01CrearMonitor.crearMonitor();
        	}else if(lista==2){
        		T02CrearActividad.crearActividad();
        	}else if(lista==3){
        		T03ConsultarActividad.consultarActividad();
        	}else if(lista==4){
        		T04CrearCliente.crearCliente();
        	}else if(lista==5){
        		T05CrearBorrarReserva.crearReserva();
        	}else if(lista==6){
        		T05CrearBorrarReserva.borrarReserva();
        	}else if(lista==7){
        		T07ListarActividad.listarActividad();
        	}else if(lista==8){
        		System.out.println("Gracias por utilizar ACTIVA. ¡Vuelve pronto!");
        	}else {
        		System.out.println("No es una opción válida. Prueba otra vez, por favor.");
        	}
    	} while (lista !=8);


    }
    //Solicitud del numero del index que se quiere ejecutar
    private static int escribirDecision() {
    return Escritura.readIntChecked("=== ACTIVA · Gestión de actividades ===\r\n"
			+ "\r\n"
			+ "1. Crear monitor\r\n"
			+ "2. Crear actividad\r\n"
			+ "3. Buscar actividades disponibles\r\n"
			+ "4. Crear cliente\r\n"
			+ "5. Crear reserva\r\n"
			+ "6. Cancelar reserva\r\n"
			+ "7. Listar actividades\r\n"
			+ "8. Salir\r\n"
			+ "¿Qué quieres hacer?");
    }
}