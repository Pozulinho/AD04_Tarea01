package tareas;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

import entidades.Monitor;
import entidades.Actividad;

public class T07ListarActividad {

    public static void listarActividad() {
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


        try {
			// crea un objeto Actividad
        	session.beginTransaction();
            
            Query<Actividad> query = session.createQuery("SELECT a " +
            		"FROM Actividad a INNER JOIN FETCH a.monitor  " +
            		"ORDER BY a.fecha_hora"
            		, Actividad.class);
            List<Actividad> actividadSolicitada = query.list();
            
            if(actividadSolicitada.isEmpty()) {
                System.out.println("No hay actividades");
            } else {
            	System.out.printf("%-5s %-25s %-20s %-15s %-15s %-15s %-15s%n", "ID", "Nombre", "Fecha y hora", "Localidad", "Monitor", "Plazas totales", "Plazas disponibles");
            	for (Actividad actividad : actividadSolicitada) {
            		System.out.printf("%-5s %-25s %-20s %-15s %-15s %-15s %-5s%n", actividad.getId(), actividad.getNombre(), actividad.getFecha_hora(), actividad.getLocalidad(), actividad.getMonitor().getNombre(), actividad.getPlazas_totales(), actividad.getPlazas_disponibles());
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
        
}