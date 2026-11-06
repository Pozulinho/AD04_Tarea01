package tareas;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidades.Cliente;

public class T04CrearCliente {

    public static void crearCliente() {
		// crea sessionFactory y session
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			    .configure( "hibernate.cfg.xml" )
			    .build();

		Metadata metadata = new MetadataSources( standardRegistry )
			    .addAnnotatedClass( Cliente.class )
			    .getMetadataBuilder()
			    .build();

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
			    .build();    
		
		Session session = sessionFactory.openSession();
		String nombre = escribirNombre();
		String email= escribirEmail();

        try {
			// crea un objeto Cliente
			System.out.println("Creando un nuevo Cliente...");
			Cliente tempCliente = new Cliente (nombre, email);
        	
        	session.beginTransaction();
			session.persist(tempCliente);
			session.getTransaction().commit();
			
			System.out.println("Hecho! Cliente " + tempCliente.getId() + " creado");
		}
		catch ( Exception e ) {
			// rollback ante alguna excepción
			System.out.println("Realizando Rollback");
			session.getTransaction().rollback();
			System.out.println("No se ha podido crear el cliente.");
		}
		finally {
			session.close();
			sessionFactory.close();
		}
    }
    
    //Escritura del nombre a insertar
    private static String escribirNombre() {
        return Escritura.readNameChecked("Inserta el nombre del cliente: ");     
    }
    
    //Escritura de email
    private static String escribirEmail() {
        while (true) {
            String email = Escritura.readStringChecked("Introduce el email del cliente: ").trim().toLowerCase();
            if (email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
                return email;
            }
            System.out.println("Email no válido. Introduce un correo con formato nombre@dominio.com.");
        }
    }

}