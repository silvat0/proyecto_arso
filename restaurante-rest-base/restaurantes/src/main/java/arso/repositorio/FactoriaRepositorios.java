package arso.repositorio;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import arso.utils.PropertiesReader;

public class FactoriaRepositorios {

private static final String PROPERTIES = "repositorios.properties";
	
	private static Map<Class<?>, Object> repositorios = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	public static <T, K, R extends IRepositorio<T, K>> R getRepositorio(Class<?> entidad) {
				
		
			
			try {
				if (repositorios.containsKey(entidad)) {
					return (R) repositorios.get(entidad);
				}
				else {
					 PropertiesReader properties = new PropertiesReader(PROPERTIES);
					 
			            String clase = properties.getProperty(entidad.getName());
			            Class<R> repositorioClass = (Class<R>) Class.forName(clase);
			            Constructor<R> constructor = repositorioClass.getConstructor(Class.class);
			            R repositorio = constructor.newInstance(entidad);
			            repositorios.put(entidad, repositorio);
			           
			            return repositorio;
				}
			}
			catch (Exception e) {
				
				throw new RuntimeException("No se ha podido obtener el repositorio para la entidad: " + entidad.getName());
			}
			
	}
}
