package usuarios.repositorio;

import repositorio.RepositorioException;
import repositorio.RepositorioMemoria;
import usuarios.modelo.Rol;
import usuarios.modelo.Usuario;

public class RepositorioUsuariosMemoria extends RepositorioMemoria<Usuario> {

	public RepositorioUsuariosMemoria()  {
		
		// Datos iniciales
		
		try {
			Usuario usuario1 = new Usuario("Marcos", "marcos@um.es", "MarcosMenarguez", Rol.GESTOR);
			this.add(usuario1);
			
			Usuario usuario2 = new Usuario("Silvia", "silvia.perezr@um.es", "silvat0", Rol.GESTOR);
			this.add(usuario2);
			
			Usuario usuario3 = new Usuario("Raul", "raul.hernandezm@um.es", "raul-umu", Rol.GESTOR);
			this.add(usuario3);
			
		} catch (RepositorioException e) {
			e.printStackTrace(); // no debe suceder en un repositorio en memoria
		}
		
	}
	
}
