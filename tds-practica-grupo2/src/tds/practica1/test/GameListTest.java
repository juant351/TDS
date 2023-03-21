package tds.practica1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import tds.practica1.Author;
import tds.practica1.Category;
import tds.practica1.Game;
import tds.practica1.GameList;

/**
 * 
 * @author sergsan
 * @author juatorr
 *
 *
 * Clase para analizar y cubrir la clase GameList.java
 */
class GameListTest {

	@Test
	public void gameListTest(){
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		Game game2 = new Game("ZELDA", 1, 1, 12, Category.Eurogames, authors);
		
		GameList gameList = new GameList("ListaJuegosDivertidos");
		gameList.addGame(game);
		gameList.addGame(game2);
		
		assertEquals("ListaJuegosDivertidos", gameList.getName());
	}
	
	@Test
	public void testAddGameError() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		Game game2 = new Game("COD", 1, 1, 12, Category.Eurogames, authors);
		
		GameList gameList = new GameList("ListaJuegosDivertidos");
		gameList.addGame(game);
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> gameList.addGame(game2));
		assertEquals("Ya existe un juego con ese nombre", exception.getMessage());
	}
	
	@Test
	public void testRemoveGameError() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		Game game2 = new Game("ZELDA", 1, 1, 12, Category.Eurogames, authors);
		
		GameList gameList = new GameList("ListaJuegosDivertidos");
		gameList.addGame(game);
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> gameList.removeGame(game2));
		assertEquals("El juego no está en la lista", exception.getMessage());
	}
}
