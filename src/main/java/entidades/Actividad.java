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
@Table(name = "actividad")

public class Actividad {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name ="id")
	private Long id	;
	
	@Column (name ="nombre")
	private String nombre;
	
	@Column (name ="localidad")
	private String localidad;
	
	@Column (name ="fecha_hora")
	private LocalDateTime fecha_hora;
	
	@Column (name ="plazas_totales")
	private int plazas_totales;

	@Column (name ="plazas_disponibles")
	private int plazas_disponibles;
	
	@ManyToOne
	@JoinColumn(name ="monitor_id")
	private Monitor monitor;
	
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

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public LocalDateTime getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(LocalDateTime fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public int getPlazas_totales() {
		return plazas_totales;
	}

	public void setPlazas_totales(int plazas_totales) {
		this.plazas_totales = plazas_totales;
	}

	public int getPlazas_disponibles() {
		return plazas_disponibles;
	}

	public void setPlazas_disponibles(int plazas_disponibles) {
		this.plazas_disponibles = plazas_disponibles;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public Actividad(Long id, String nombre, String localidad, LocalDateTime fecha_hora, int plazas_totales,
			int plazas_disponibles, Monitor monitor) {
		this.id = id;
		this.nombre = nombre;
		this.localidad = localidad;
		this.fecha_hora = fecha_hora;
		this.plazas_totales = plazas_totales;
		this.plazas_disponibles = plazas_disponibles;
		this.monitor = monitor;
	}
	
	public Actividad(String nombre, String localidad, LocalDateTime fecha_hora, int plazas_totales,
			int plazas_disponibles, Monitor monitor) {
		this.nombre = nombre;
		this.localidad = localidad;
		this.fecha_hora = fecha_hora;
		this.plazas_totales = plazas_totales;
		this.plazas_disponibles = plazas_disponibles;
		this.monitor = monitor;
	}

	public Actividad() {
	}

}