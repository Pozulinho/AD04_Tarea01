package tareas;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDateTime;
import entidades.Monitor;
import entidades.Actividad;

public class T02CrearActividad {

    public static void crearActividad() {
		// crea sessionFactory y session
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			    .configure( "hibernate.cfg.xml" )
			    .build();

		Metadata metadata = new MetadataSources( standardRegistry )
			    .addAnnotatedClass( Monitor.class )
			    .addAnnotatedClass( Actividad.class )
			    .getMetadataBuilder()
			    .build();

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
			    .build();    
		
		Session session = sessionFactory.openSession();
		String nombre = escribirNombre();
		String localidad= escribirLocalidad();
		LocalDateTime fecha = escribirFecha();
		int plazas_totales = escribirPlazas();
		Monitor monitor = escribirMonitor(session);


        try {
			// crea un objeto Actividad
			System.out.println("Creando una nueva Actividad...");
			Actividad tempActividad = new Actividad (nombre, localidad, fecha, plazas_totales, plazas_totales, monitor);
        	
        	session.beginTransaction();
			session.persist(tempActividad);
			session.getTransaction().commit();
			
			System.out.println("Hecho! Actividad " + tempActividad.getId() + " creada");
		}
		catch ( Exception e ) {
			// rollback ante alguna excepción
			System.out.println("Realizando Rollback");
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
			sessionFactory.close();
		}
    }
    
    //Escritura del nombre a insertar
    private static String escribirNombre() {
        return Escritura.readNameChecked("Inserta el nombre de la actividad: ");     
    }
    
    //Escritura de la localidad a insertar
    private static String escribirLocalidad() {
    	return Escritura.readNameChecked("Inserta la localidad de la actividad: ");
    }
    
    //Escritura de la plazas totales a insertar
    private static int escribirPlazas() {
    	return Escritura.readIntChecked("Inserta el numero de plazas totales de la actividad: ");
    }
    
    //Escritura de id de monitor
    private static Monitor escribirMonitor(Session session) {
        Monitor monitor;
        do {
            Long monitorId = Escritura.readLongChecked("Introduce el ID del monitor de la actividad: ");
            monitor = session.find(Monitor.class, monitorId);
            if (monitor == null) {
                System.out.println("No existe ningún monitor con ese ID. Inténtalo de nuevo.");
            }
        } while (monitor == null);
        return monitor;
    }
    
    //Escritura de fecha
    private static LocalDateTime escribirFecha() {
    	return Escritura.readDateTimeChecked("Inserta la fecha y hora de la actividad. Utiliza dd/MM/yyyy HH:mm.: ");
    }

}