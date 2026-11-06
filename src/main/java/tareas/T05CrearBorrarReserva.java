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
import entidades.Reserva;
import entidades.Cliente;

public class T05CrearBorrarReserva {

    public static void crearReserva() {
		// crea sessionFactory y session
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			    .configure( "hibernate.cfg.xml" )
			    .build();

		Metadata metadata = new MetadataSources( standardRegistry )
			    .addAnnotatedClass( Monitor.class )
			    .addAnnotatedClass( Actividad.class )
			    .addAnnotatedClass( Cliente.class )
			    .addAnnotatedClass( Reserva.class )
			    .getMetadataBuilder()
			    .build();

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
			    .build();    
		
		Session session = sessionFactory.openSession();
		Cliente cliente = escribirCliente(session);
		Actividad actividad= escribirActividad(session);
		int plazas_requeridas = escribirPlazas();
		LocalDateTime fecha = LocalDateTime.now();

        try {
			// crea un objeto Reserva
        	
        	session.beginTransaction();
            int plazas_disponibles = actividad.getPlazas_disponibles();
            
            if(plazas_requeridas > plazas_disponibles) {
                System.out.println("No hay disponibilidad para la reserva");
            } else {
            	//creamos la reserva
    			System.out.println("Creando una nueva Reserva...");
    			Reserva tempReserva = new Reserva (fecha, plazas_requeridas, actividad, cliente);
            	session.persist(tempReserva);
            	
            	//modificamos las plazas disponibles
            	int nuevasPlazasDisp = plazas_disponibles - plazas_requeridas;
            	actividad.setPlazas_disponibles(nuevasPlazasDisp);
    			
    			//ejecutamos el update
    			session.getTransaction().commit();
            	
    			//Imprimimos los datos de la reserva
    			System.out.println("Hecho! Reserva numero " + tempReserva.getId() + " creada. Plazas restantes disponibles: " + nuevasPlazasDisp);
    			}

			}
		catch ( Exception e ) {
			// rollback ante alguna excepción
			System.out.println("Realizando Rollback");
			session.getTransaction().rollback();
			System.out.println("No se pudo crear la reserva. Se han deshecho los cambios.");
			e.printStackTrace();
		}
		finally {
			session.close();
			sessionFactory.close();
		}
    }
    
    public static void borrarReserva() {
		// crea sessionFactory y session
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			    .configure( "hibernate.cfg.xml" )
			    .build();

		Metadata metadata = new MetadataSources( standardRegistry )
			    .addAnnotatedClass( Monitor.class )
			    .addAnnotatedClass( Actividad.class )
			    .addAnnotatedClass( Cliente.class )
			    .addAnnotatedClass( Reserva.class )
			    .getMetadataBuilder()
			    .build();

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
			    .build();    
		
		Session session = sessionFactory.openSession();
		Reserva reserva = escribirReserva(session);
		if(reserva==null){
		    session.close();
		    sessionFactory.close();
		    return;
		}

        try {
			// crea un objeto Reserva
        	
        	session.beginTransaction();
            int plazas_canceladas = reserva.getNumero_plazas();
            Actividad actividad = reserva.getActividad();

            	//cancelamos la reserva
    			System.out.println("Elminando la Reserva...");

            	
            	//modificamos las plazas disponibles
            	int nuevasPlazasDisp = actividad.getPlazas_disponibles() + plazas_canceladas;
            	actividad.setPlazas_disponibles(nuevasPlazasDisp);
    		
    			session.remove(reserva);
    			
    			//ejecutamos el update
    			session.getTransaction().commit();
            	
    			//Imprimimos los datos de la reserva
    			System.out.println("Hecho! La reserva ha sido elminada. Se han devuelto " + plazas_canceladas + " plazas. Plazas restantes disponibles: " + nuevasPlazasDisp);

			}
		catch ( Exception e ) {
			// rollback ante alguna excepción
			System.out.println("Realizando Rollback");
			session.getTransaction().rollback();
			System.out.println("No se pudo crear la reserva. Se han deshecho los cambios.");
			e.printStackTrace();
		}
		finally {
			session.close();
			sessionFactory.close();
		}
    }
    
    //Escritura de la plazas a reservar
    private static int escribirPlazas() {
    	return Escritura.readIntChecked("Inserta el numero de plazas a reservar: ");
    }

    //Escritura de id de cliente
    private static Cliente escribirCliente(Session session) {
        Cliente cliente;
        do {
            Long id = Escritura.readLongChecked("Introduce el ID del cliente : ");
            cliente = session.find(Cliente.class, id);
            if (cliente == null) {
                System.out.println("No existe ningún cliente con ese ID. Inténtalo de nuevo o date de alta.");
            }
        } while (cliente == null);
        return cliente;
    }
    
    //Escritura de id de actividad
    private static Actividad escribirActividad(Session session) {
        Actividad actividad;
        do {
            Long id = Escritura.readLongChecked("Introduce el ID de la actividad : ");
            actividad = session.find(Actividad.class, id);
            if (actividad == null) {
                System.out.println("No existe ninguna actividad con ese ID. Inténtalo de nuevo");
            }
        } while (actividad == null);
        return actividad;
    }
    
    //Escritura de id de reserva
    private static Reserva escribirReserva(Session session) {
        Reserva reserva;
        Long id;
        do {
            id = Escritura.readLongChecked("Introduce el ID de la reserva que quieres cancelar : ");
            if(id==0) {
            	System.out.println("Operación cancelada");
                return null;
            }
            reserva = session.find(Reserva.class, id);
            if (reserva == null) {
                System.out.println("No existe ninguna actividad con ese ID. Inténtalo de nuevo. Para salir pulsa 0");
            }
        } while (reserva == null);
        return reserva;
    }
}