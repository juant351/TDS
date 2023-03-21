package tds.practica1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import tds.practica1.Author;
import tds.practica1.Category;
import tds.practica1.Game;
import tds.practica1.GameCollection;
import tds.practica1.GameList;

class GameCollectionTest {

	@Test
	public void addGametest() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		GameCollection gameCollection = new GameCollection();
		gameCollection.addGame(game);
		assertEquals(game, gameCollection.getGameByName("COD"));
	}
	@Test
	public void addGameTestError() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		GameCollection gameCollection = new GameCollection();
		Exception exception = assertThrows(NullPointerException.class, () -> gameCollection.addGame(null));
		assertEquals("Game no puede ser nulo", exception.getMessage());
		Game gameInserta = new Game("HOLA", 2, 4, 18, Category.Legacy, authors);
		gameCollection.addGame(gameInserta);
		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> gameCollection.addGame(gameInserta));
		assertEquals("El juego ya esta añadido", exception2.getMessage());
	}
	
	@Test
	public void addGameListTest(){
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		GameList gamelist = new GameList("UNO");
		GameCollection gameCollection = new GameCollection();
		gameCollection.addGameList(gamelist);
		assertEquals(gamelist, gameCollection.getGameListByName("UNO"));
	}
	
	@Test
	public void addGameListTestError() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		GameList gamelist = new GameList("UNO");
		GameCollection gameCollection = new GameCollection();
		Exception exception = assertThrows(NullPointerException.class, () -> gameCollection.addGameList(null));
		assertEquals("GameList no puede ser nulo", exception.getMessage());
		gameCollection.addGameList(gamelist);
		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> gameCollection.addGameList(gamelist));
		assertEquals("Ya existe una lista con ese nombre", exception2.getMessage());
	}
	
	@Test
	public void updateGameTest() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		GameCollection gameCollection = new GameCollection();
		gameCollection.addGame(game);
		game = new Game("COD", 3, 5, 19, Category.Legacy, authors);
		gameCollection.updateGame(game);
		assertEquals(game, gameCollection.getGameByName("COD"));		
	}
	
	@Test
	public void updateGameError() {
		GameCollection gameCollection = new GameCollection();
		Exception exception = assertThrows(NullPointerException.class, () -> gameCollection.updateGame(null));
		assertEquals("Game no puede ser nulo", exception.getMessage());
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> gameCollection.updateGame(game));
		assertEquals("El juego no se puede actualizar porque no está.", exception2.getMessage());
	}
	@Test
	public void addGameToGameList() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		GameCollection gameCollection = new GameCollection();
		GameList gamelist = new GameList("UNO");
		gameCollection.addGameList(gamelist);
		gameCollection.addGameToGameList(gamelist, game);
		assertTrue(gameCollection.getGameListByName("UNO").getGames().contains(game));
	}
	@Test 
	public void addGameToGameListError() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		GameCollection gameCollection = new GameCollection();
		GameList gamelist = new GameList("UNO");
		Exception exception = assertThrows(NullPointerException.class, () -> gameCollection.addGameToGameList(null,game));
		assertEquals("GameList no puede ser nulo", exception.getMessage());
		Exception exception2 = assertThrows(NullPointerException.class, () -> gameCollection.addGameToGameList(gamelist,null));
		assertEquals("Game no puede ser nulo", exception2.getMessage());
	}
	
	@Test
	public void removeGameFromGameList() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		GameCollection gameCollection = new GameCollection();
		GameList gamelist = new GameList("UNO");
		gameCollection.addGameList(gamelist);
		gameCollection.addGameToGameList(gamelist, game);
		gameCollection.removeGameFromGameList(gamelist, game);
		assertFalse(gameCollection.getGameListByName("UNO").getGames().contains(game));
	}
	
	@Test
	public void removeGameFromGameListError(){
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		GameCollection gameCollection = new GameCollection();
		GameList gamelist = new GameList("UNO");
		Exception exception = assertThrows(NullPointerException.class, () -> gameCollection.removeGameFromGameList(null,game));
		assertEquals("GameList no puede ser nulo", exception.getMessage());
		Exception exception2 = assertThrows(NullPointerException.class, () -> gameCollection.removeGameFromGameList(gamelist,null));
		assertEquals("Game no puede ser nulo", exception2.getMessage());
	}
	
	@Test
	public void removeCollectionElement() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		GameCollection gameCollection = new GameCollection();
		GameList gamelist = new GameList("UNO");
		gameCollection.addGameList(gamelist);
		gameCollection.removeCollectionElement(gamelist);
		assertNull(gameCollection.getCollectionElementById(gamelist.getId()));
	}
	
	@Test
	public void removeCollectionElementError() {
		GameCollection gameCollection = new GameCollection();
		Exception exception = assertThrows(NullPointerException.class, () -> gameCollection.removeCollectionElement(null));
		assertEquals("El CollectionElement no puede ser nulo", exception.getMessage());
	}
}
