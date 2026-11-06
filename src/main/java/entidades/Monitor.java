package entidades;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "monitor")

public class Monitor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name ="id")
	private Long id	;
	
	@Column (name ="nombre")
	private String nombre;
	
	@Column (name ="especialidad")
	private String especialidad;
	
    @OneToMany(mappedBy = "monitor")
    private List<Actividad> actividades;

	
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

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

	public Monitor(Long id, String nombre, String especialidad) {
		this.id = id;
		this.nombre = nombre;
		this.especialidad = especialidad;
	}
	
	public Monitor(String nombre, String especialidad) {
		this.nombre = nombre;
		this.especialidad = especialidad;
	}

	public Monitor() {
	}

}