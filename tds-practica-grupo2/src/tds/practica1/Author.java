package tds.practica1;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Implementacion de la clase {@link Author} proporciona la creacion de objetos
 * Autor identificado por su nombre y apellidos, un id unico que se
 * autoincrementa y una lista de juegos de los que es propiamente su autor
 * 
 * @author sergsan
 * @author juatorr
 *
 */
@SuppressWarnings("unused")
public class Author implements Cloneable {
	private String name;
	private String surname;
	private int id = 0;
	private Set<Game> games;

	private Author() {

	}

	/**
	 * Se encarga de crear un nuevo {@link Author} a partir de su nombre y apellidos
	 * se asignara un id unico y se inicializara su {@link GameList}
	 * 
	 * @param name
	 * @param surname
	 */
	public Author(String name, String surname) {
		setName(name);
		setSurname(surname);
		setId(getId() + 1);
		games = new HashSet<Game>();
	}

	/**
	 * Proporciona el id del {@link Author}
	 * 
	 * @return ID del {@link Author}
	 */
	public int getId() {
		return id;
	}

	/**
	 * Asigna el id al {@link Author}
	 * 
	 * @param id
	 */
	private void setId(int id) {
		this.id = id;
	}

	/**
	 * Proporciona el nombre del {@link Author}
	 * 
	 * @return name Nombre del {@link Author}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Asigna el nombre del {@link Author}
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Proporciona el apellido del {@link Author}
	 * 
	 * @return surname Apellido del {@link Author}
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Asigna el apellido del {@link Author}
	 * 
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Proporciona la {@link GameList} correspondiente al {@link Author}
	 * 
	 * @return {@link GameList}
	 */
	public Set<Game> getGames() {
		return games;
	}

	/**
	 * Asigna la {@link GameList} del {@link Author}
	 * 
	 * @param games {@link GameList}
	 */
	private void setGames(Set<Game> games) {
		this.games = games;
	}

	/**
	 * Funcion encargada de agregar un {@link Game} a la {@link GameList} del
	 * {@link Author}
	 * 
	 * @param game {@link Game}
	 * @exception IllegalArgumentException si game ya está añadido en {@link #games}
	 */
	public void addGame(Game game) {
		for (Game g : games) {
			if (g.getName().equals(game.getName().toUpperCase())) {
				throw new IllegalArgumentException("Juego ya esta añadido");
			}
		}
		games.add(game);
	}

	/**
	 * Funcion encargada de eliminar un {@link Game} de la {@link GameList} del
	 * {@link Author}
	 * 
	 * @param game {@link Game}
	 * @exception NullPointerException si {@link #games} está vacía
	 * @exception IllegalArgumentException si list no se encuentra en {@link #games}
	 */
	public void removeGame(Game game) {
		int flag = 0;
		if (games.isEmpty()) {
			throw new NullPointerException("Lista vacia");
		}
		for (Game g : games) {
			if (g.getName().equals(game.getName().toUpperCase())) {
				games.remove(game);
				flag = 1;
			}
			if (flag == 0) {
				throw new IllegalArgumentException("Juego no existe en la lista");
			}
		}
	}

}
