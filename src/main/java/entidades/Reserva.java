package entidades;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
@Table(name = "reserva")

public class Reserva {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name ="id")
	private Long id	;

	@Column (name ="fecha_reserva")
	private LocalDateTime fecha_reserva;
	
	@Column (name ="numero_plazas")
	private int numero_plazas;

	@ManyToOne
	@JoinColumn (name ="actividad_id")
	private Actividad actividad;
	
	@ManyToOne
	@JoinColumn (name ="cliente_id")
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFecha_reserva() {
		return fecha_reserva;
	}

	public void setFecha_reserva(LocalDateTime fecha_reserva) {
		this.fecha_reserva = fecha_reserva;
	}

	public int getNumero_plazas() {
		return numero_plazas;
	}

	public void setNumero_plazas(int numero_plazas) {
		this.numero_plazas = numero_plazas;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad_id(Actividad actividad) {
		this.actividad = actividad;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente_id(Cliente cliente) {
		this.cliente = cliente;
	}

	public Reserva(Long id, LocalDateTime fecha_reserva, int numero_plazas, Actividad actividad, Cliente cliente) {
		super();
		this.id = id;
		this.fecha_reserva = fecha_reserva;
		this.numero_plazas = numero_plazas;
		this.actividad = actividad;
		this.cliente = cliente;
	}
	
	public Reserva(LocalDateTime fecha_reserva, int numero_plazas, Actividad actividad, Cliente cliente) {
		super();
		this.fecha_reserva = fecha_reserva;
		this.numero_plazas = numero_plazas;
		this.actividad = actividad;
		this.cliente = cliente;
	}

	public Reserva() {	
	}	
}