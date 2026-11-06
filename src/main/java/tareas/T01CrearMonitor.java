package tareas;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidades.Monitor;
import entidades.Actividad;

public class T01CrearMonitor {

    public static void crearMonitor() {
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
		String especialidad = escribirEspecialidad();

        try {
			// crea un objeto Monitor
			System.out.println("Creando un nuevo Monitor...");
			Monitor tempMonitor = new Monitor (nombre, especialidad);
        	
        	session.beginTransaction();
			session.persist(tempMonitor);
			session.getTransaction().commit();
			
			System.out.println("Hecho! Monitor " + tempMonitor.getId() + " creado");
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
    	return Escritura.readNameChecked("Inserta el nombre del monitor: ");        
    }
    
    //Escritura de la especialidad a insertar
    private static String escribirEspecialidad() {
    	return Escritura.readNameChecked("Inserta la especialidad del monitor: "); 
    }

}