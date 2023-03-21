package tds.practica1;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Implementación de la clase {@link GameList}, proporciona la creación de
 * objetos. Cuenta con dos atributos: name y games, el primero es el nombre de
 * la {@link GameList} y el segundo la lista {@link HashSet} de {@link Game}
 * 
 * @author sergsan
 * @author juatorr
 *
 */
@SuppressWarnings("unused")
public class GameList implements Cloneable, CollectionElement {

	private String name;
	private Set<Game> games;

	private GameList() {

	}

	/**
	 * Se crea una nueva {@link GameList} a partir de su nombre y se inicializará su
	 * {@link HashSet} formado por {@link Game}
	 * 
	 * @param name
	 */
	public GameList(String name) {
		setName(name);
		games = new HashSet<Game>();
		setGames(games);
	}

	/**
	 * Proporciona el nombre de la {@link GameList}
	 * 
	 * @return nombre de la {@link GameList}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Establece el nombre de la {@link GameList}
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Proporciona el {@link HashSet} formado por {@link Game} de la
	 * {@link GameList}
	 * 
	 * @return la lista de {@link Game}
	 */
	public Set<Game> getGames() {
		return games;
	}

	/**
	 * Establece el {@link HashSet} formado por {@link Game} de la {@link GameList}
	 * 
	 * @param games
	 */
	private void setGames(Set<Game> games) {
		this.games = games;
	}

	/**
	 * Añade un {@link Game} a {@link GameList}, para ello lo añade en
	 * {@link #games}
	 * 
	 * @param game {@link Game}
	 * @exception IllegalArgumentException si en {@link #games} ya hay un {@link Game} con el mismo nombre.
	 */
	public void addGame(Game game) {
		for (Game g : games) {
			if (g.getName().equals(game.getName().toUpperCase())) {
				throw new IllegalArgumentException("Ya existe un juego con ese nombre");
			}
		}
		games.add(game);
	}

	/**
	 * Elimina un {@link Game} de la {@link GameList}, para ello lo elimina de {@link #games}
	 * 
	 * @param game {@link Game}
	 * @exception IllegalArgumentException si game no se encuentra en {@link #games}
	 */
	public void removeGame(Game game) {
		int flag = 0;
		for (Game g : games) {
			if (g.getName().equals(game.getName().toUpperCase())) {
				games.remove(game);
				flag = 1;
			}
		}
		if (flag == 0) {
			throw new IllegalArgumentException("El juego no está en la lista");
		}
	}

	/**
	 * Comprueba si una {@link GameList} contiene un elemento, para ello comprueba si el elemento se 
	 * encuentra en {@link #games}
	 * 
	 * @param element
	 * 
	 * @return TRUE si el elemento se encuentra en la {@link GameList}
	 * @return FALSE si el elemento no se encuentra en la {@link GameList}
	 * 
	 */
	public boolean contains(CollectionElement element) {
		return games.contains(element);
	}

	@Override
	public String getId() {
		return name;
	}

}
