package tds.practica1;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *Implementacion de la clase {@link Game} proporciona la creacion de objetos
 * Game identificado por su nombre, número mínimo y máximo de jugadores, la edad mínima, categoría, autores
 * y una lista, además de un id unico
 * y una lista de juegos de los que es propiamente su autor 
 * 
 * 
 * @author sergsan
 * @author juatorr
 *
 */
@SuppressWarnings("unused")
public class Game implements Cloneable, CollectionElement {
	private String nameGame;
	private int minPlayers;
	private int maxPlayers;
	private int minAge;
	private Category category;
	private Set<Author> authors;
	private Set<GameList> gameLists;

	private Game() {

	}
	
	/**
	 * Se encarga de crear un nuevo {@link Game} a partir de su nombre, y demás parámetros: número mínimo de jugadores, número
	 * máximo de jugadores, edad mínima, categoría, y autores. Se inicializará su {@link GameList}
	 * 
	 * @param name
	 * @param minPlayers
	 * @param maxPlayers
	 * @param minAge
	 * @param category
	 * @param authors
	 */
	public Game(String name, int minPlayers, int maxPlayers, int minAge, Category category, Set<Author> authors) {
		setName(name);
		setMaxPlayers(maxPlayers);
		setMinPlayers(minPlayers);
		setMinAge(minAge);
		setCategory(category);
		setAuthors(authors);
		gameLists = new HashSet<GameList>();
	}

	/**
	 * Proporciona el nombre del {@link Game}
	 * 
	 * @return nombre del {@link Game}
	 */
	public String getName() {
		return nameGame;
	}

	/**
	 * Asigna el nombre del {@link Game}
	 * 
	 * @param name 
	 * @exception IllegalArgumentException si el nombre contiene menos de un (1) carácter.
	 * @exception IllegalArgumentException si el nombre contiene más de dieciseis (16) carácteres.
	 */
	private void setName(String name) {
		if (name.length() < 1 || name.length() > 16) {
			throw new IllegalArgumentException("Entrada incorrecta");
		} else {
			name = name.toUpperCase();
			this.nameGame = name;
		}
	}

	/**
	 * Proporciona el numero minimo de jugadores del {@link Game}
	 * 
	 * @return numero mínimo de jugadores del {@link Game}
	 */
	public int getMinPlayers() {
		return minPlayers;
	}

	/**
	 * Establece el numero minimo de jugadores del {@link Game}
	 * 
	 * @param minPlayers
	 * @exception IllegalArgumentException si el numero minimo de jugadores es menor que cero (0).
	 * @exception IllegalArgumentException si el numero minimo de jugadores es mayor que el {@link #maxPlayers}
	 */
	public void setMinPlayers(int minPlayers) {
		if (minPlayers > 0 && minPlayers <= maxPlayers) {
			if (getCategory() == Category.Cooperativos && minPlayers != 2) {
				throw new IllegalArgumentException("Con categoria Cooperativos, jugadores minimos = 2");
			} else {
				this.minPlayers = minPlayers;
			}
		} else {
			throw new IllegalArgumentException("Entrada incorrecta");
		}
	}

	/**
	 * Proporciona el numero maximo de jugadores del {@link Game}
	 * 
	 * @return número máximo de jugadores del {@link Game}
	 */
	public int getMaxPlayers() {
		return maxPlayers;
	}

	/**
	 * Establece el numero máximo de jugadores del {@link Game}
	 * 
	 * @param maxPlayers
	 * @exception NullPointerException si {@link #maxPlayers} es cero (0).
	 * @exception IllegalArgumentException si {@link #maxPlayers} es menor que cero (0).
	 * @exception IllegalArgumentException si {@link #maxPlayers} es mayor que veinte (20).
	 */
	public void setMaxPlayers(int maxPlayers) {
		if (maxPlayers == 0) {
			throw new NullPointerException("Debe de haber al menos un jugador");
		} else if (maxPlayers < 0) {
			throw new IllegalArgumentException("Entrada incorrecta");
		} else if (maxPlayers > 20) {
			throw new IllegalArgumentException("No puede haber más de 20 jugadores");
		} else {
			this.maxPlayers = maxPlayers;
		}
	}

	/**
	 * Proporciona la edad minima para jugar al {@link Game}
	 * 
	 * @return edad mínima
	 */
	public int getMinAge() {
		return minAge;
	}

	/**
	 * Establece la edad mínima para jugar al {@link Game}
	 * 
	 * @param minAge
	 * @exception IllegalArgumentException si {@link #minAge} es menor que cero (0).
	 */
	public void setMinAge(int minAge) {
		if (minAge < 0) {
			throw new IllegalArgumentException("Entrada incorrecta");
		} else {
			this.minAge = minAge;
		}
	}

	/**
	 * Proporciona la {@link Category} del {@link Game}
	 * 
	 * @return categoria del juego
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Establece la {@link Category} del {@link Game}
	 * Si la categoria es {@link Category#Cooperativos} se establece el {@link #minPlayers} del {@link Game} en dos (2).
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;
		if (getCategory() == Category.Cooperativos) {
			setMinPlayers(2);
		}
	}

	/**
	 * Proporciona los {@link Author} del {@link Game}
	 * 
	 * @return los {@link Author} del {@link Game}
	 */
	public Set<Author> getAuthors() {
		return authors;
	}

	private void setAuthors(Set<Author> authors) {
		if (authors.size() < 1) {
			throw new IllegalArgumentException("Debe contener al menos un autor");
		} else {
			this.authors = authors;
		}

	}

	/**
	 * Añade un {@link Author} a {@link #authors} del {@link Game}
	 * 
	 * @param author
	 * @exception NullPointerException si {@link Author#getName()} es null.
	 * @exception NullPointerException si {@link Author#getSurname()} es null.
	 * @exception IllegalArgumentException si el {@link Author} ya está incluido en {@link #authors}
	 */
	public void addAuthor(Author author) {
		if (author.getName() == null || author.getSurname() == null) {
			throw new NullPointerException("El nombre y el apellido no pueden ser nulos");
		} else {
			for (Author aut : authors) {
				if (aut.getName().equals(author.getName()) && aut.getSurname().equals(author.getSurname())) {
					throw new IllegalArgumentException("El autor ya está añadido");
				}
			}
			authors.add(author);
		}
	}

	/**
	 * Elimina un {@link Author} de {@link #authors}
	 * 
	 * @param author
	 * @exception IllegalArgumentException si el {@link Author} no se encuentra en {@link #authors}
	 */
	public void removeAuthor(Author author) {
		int flag = 0;
		for (Author aut : authors) {
			if (aut.getName().equals(author.getName()) && aut.getSurname().equals(author.getSurname())) {
				flag = 1;
				authors.remove(author);
			}
		}
		if (flag == 0) {
			throw new IllegalArgumentException("El autor no corresponde a este juego");
		}
	}

	/**
	 * Proporciona el {@link HashSet} formado por {@link GameList} de {@link Game}
	 * 
	 * @return la lista de listas de juegos
	 */
	public Set<GameList> getGameLists() {
		return gameLists;
	}

	private void setGameLists(Set<GameList> gameLists) {
		if (gameLists.size() < 1) {
			throw new IllegalArgumentException("Debe contener al menos una lista");
		}
		this.gameLists = gameLists;
	}

	/**
	 * Añade una {@link GameList} a {@link #gameLists}
	 * 
	 * @param list que se va a añadir
	 * @exception IllegalArgumentException si list ya está incluida en {@link Game#gameLists}
	 */
	public void addList(GameList list) {
		for (GameList gl : gameLists) {
			if (gl.getName().equals(list.getName())) {
				throw new IllegalArgumentException("La lista ya esta añadida");
			}
		}
		gameLists.add(list);
	}

	/**
	 * Elimina una {@link GameList} de {@link #gameLists}
	 * 
	 * @param list a eliminar
	 * @exception NullPointerException si {@link #gameLists} está vacía
	 * @exception IllegalArgumentException si no se encuentra list en {@link #gameLists}
	 */
	public void removeList(GameList list) {
		int flag = 0;

		if (gameLists.size() < 1) {
			throw new NullPointerException("La lista de juegos está vacía");
		}
		for (GameList gl : gameLists) {
			if (gl.getName().equals(list.getName())) {
				flag = 1;
				gameLists.remove(list);
			}
		}
		if (flag == 0) {
			throw new IllegalArgumentException("La lista no existe");
		}

	}

	@Override
	public String getId() {
		return nameGame;

	}

}
