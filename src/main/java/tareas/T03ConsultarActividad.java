package tareas;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

import entidades.Monitor;
import entidades.Actividad;

public class T03ConsultarActividad {

    public static void consultarActividad() {
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
		String localidad= escribirLocalidad().toLowerCase();
		LocalDate fecha = escribirFecha();
		int plazas_necesarias = escribirPlazas();

        try {
			// crea un objeto Actividad
        	session.beginTransaction();
        	
            LocalDateTime inicio = fecha.atStartOfDay();
            LocalDateTime fin = fecha.plusDays(1).atStartOfDay();
            
            Query<Actividad> query = session.createQuery("SELECT a FROM Actividad a " +
            		"WHERE LOWER(a.localidad) = LOWER(:localidad) " + 
            		"AND a.plazas_disponibles >= :plazas " +
            		"AND a.fecha_hora >= :inicio "+
            		"AND a.fecha_hora < :fin "+
            		"ORDER BY a.fecha_hora "
            		, Actividad.class);
            query.setParameter("localidad", localidad);
            query.setParameter("plazas", plazas_necesarias);
            query.setParameter("inicio", inicio);
            query.setParameter("fin", fin);
            List<Actividad> actividadSolicitada = query.list();
            
            if(actividadSolicitada.isEmpty()) {
                System.out.println("No hay actividades con estas características");
            } else {
            	System.out.printf("%-5s %-25s %-25s %-15s%n", "ID", "Nombre", "Fecha y hora", "Plazas disponibles");
            	for (Actividad actividad : actividadSolicitada) {
            		System.out.printf("%-5d %-25s %-25s %-5s%n", actividad.getId(), actividad.getNombre(), actividad.getFecha_hora(), actividad.getPlazas_disponibles());
            }
            	session.getTransaction().commit();
            }
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
		finally {
			session.close();
			sessionFactory.close();
		}
    }
        
    //Escritura de la localidad a insertar
    private static String escribirLocalidad() {
    	return Escritura.readNameChecked("Inserta la localidad de la actividad: ");
    }
    
    //Escritura de la plazas totales a insertar
    private static int escribirPlazas() {
    	return Escritura.readIntChecked("Inserta el numero de plazas que necesitas: ");
    }
    
    
    //Escritura de fecha
    private static LocalDate escribirFecha() {
    	return Escritura.readDateChecked("Inserta la fecha de la actividad. Utiliza dd/MM/yyyy: ");
    }

}