package entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
@Table(name = "cliente")

public class Cliente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name ="id")
	private Long id	;
	
	@Column (name ="nombre")
	private String nombre;
	
	@Column (name ="email")
	private String email;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cliente(Long id, String nombre, String email) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
	}
	
	public Cliente(String nombre, String email) {
		this.nombre = nombre;
		this.email = email;
	}

	public Cliente() {
	}

}