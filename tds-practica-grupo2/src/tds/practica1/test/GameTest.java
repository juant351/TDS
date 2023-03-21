package tds.practica1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import tds.practica1.Author;
import tds.practica1.Category;
import tds.practica1.Game;
import tds.practica1.GameList;

@SuppressWarnings("unused")
class GameTest {

	@Test
	public void gameTest() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		game.addAuthor(authorTwo);
		assertEquals("COD", game.getName());
		assertEquals(2, game.getMinPlayers());
		assertEquals(4, game.getMaxPlayers());
		assertEquals(18, game.getMinAge());
		assertEquals(Category.Legacy, game.getCategory());
		assertArrayEquals(authors.toArray(), game.getAuthors().toArray());
		
		GameList gamelist = new GameList("Uno");
		game.addList(gamelist);
		game.removeList(gamelist);
		Set<GameList> gameLists = new HashSet<GameList>();
		assertEquals(gameLists, game.getGameLists());
		
		game.removeAuthor(author);
		authors.remove(author);
		assertArrayEquals(authors.toArray(), game.getAuthors().toArray());
		
	}

	@Test
	public void testSetNameLimInf() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> new Game("", 2, 4, 18, Category.Legacy, authors));
		assertEquals("Entrada incorrecta", exception.getMessage());
	}
	
	@Test
	public void testSetNameLimSup() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> new Game("FarCry6Juegazobro", 2, 4, 18, Category.Legacy, authors));
		assertEquals("Entrada incorrecta", exception.getMessage());
	}
	@Test
	public void testSetMinPlayersError() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> game.setMinPlayers(0));
		assertEquals("Entrada incorrecta", exception.getMessage());
		game.setCategory(Category.Cooperativos);
		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> game.setMinPlayers(3));
		assertEquals("Con categoria Cooperativos, jugadores minimos = 2", exception2.getMessage());
		Exception exception3 = assertThrows(IllegalArgumentException.class, () -> game.setMinPlayers(5));
		assertEquals("Entrada incorrecta", exception3.getMessage());
	}

	@Test
	public void testSetMaxPlayerError() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> game.setMaxPlayers(21));
		assertEquals("No puede haber más de 20 jugadores", exception.getMessage());
		
		Exception exception2 = assertThrows(NullPointerException.class, () -> game.setMaxPlayers(0));
		assertEquals("Debe de haber al menos un jugador", exception2.getMessage());
		
		Exception exception3 = assertThrows(IllegalArgumentException.class, () -> game.setMaxPlayers(-1));
		assertEquals("Entrada incorrecta", exception3.getMessage());
	}
	
	@Test
	public void testSetMinAgeError() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> game.setMinAge(-1));
		assertEquals("Entrada incorrecta", exception.getMessage());
	}
	
	@Test
	public void testSetAuthors() {
		Set<Author> authors = new HashSet<Author>();
		Exception exception = assertThrows(IllegalArgumentException.class, () -> new Game("COD", 2, 4, 18, Category.Legacy, authors));
		assertEquals("Debe contener al menos un autor", exception.getMessage());
	}
	@Test 
	public void testAddAuthorError() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		Exception exceptionAnnadido= assertThrows(IllegalArgumentException.class, () -> game.addAuthor(author));
		assertEquals("El autor ya está añadido", exceptionAnnadido.getMessage());
		Author authorNull = new Author(null,"Fernández");
		Exception exceptionNull = assertThrows(NullPointerException.class, () -> game.addAuthor(authorNull));
		assertEquals("El nombre y el apellido no pueden ser nulos", exceptionNull.getMessage());
	}
	
	@Test
	public void testRemoveAuthorError() {
		Author author = new Author("James", "Clancy");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		Author authorNull = new Author("James", "Rich");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> game.removeAuthor(authorNull));
		assertEquals("El autor no corresponde a este juego", exception.getMessage());
	}
	
	@Test
	public void testAddListError() {
		Author author = new Author("James", "Clancy");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		GameList gamelist1 = new GameList("Uno");
		game.addList(gamelist1);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> game.addList(gamelist1));
		assertEquals("La lista ya esta añadida", exception.getMessage());
		
	}
	
	@Test
	public void testRemoveGameListError() {
		Author author = new Author("James", "Clancy");
		Author authorTwo = new Author("Mohammad", "Alavi");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		authors.add(authorTwo);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		GameList gameList = new GameList("Lista");
		Exception exceptionNull = assertThrows(NullPointerException.class, () -> game.removeList(gameList));
		assertEquals("La lista de juegos está vacía", exceptionNull.getMessage());
		
	}
	@Test
	public void testRemoveListError() {
		Author author = new Author("James", "Clancy");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		Game game = new Game("COD", 2, 4, 18, Category.Legacy, authors);
		Game game2 = new Game("ZELDA", 2, 4, 18, Category.Legacy, authors);
		GameList gamelist = new GameList("Uno");
		GameList gamelist2 = new GameList("Dos");
		gamelist.addGame(game);
		gamelist.addGame(game2);
		game.addList(gamelist);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> game.removeList(gamelist2));
		assertEquals("La lista no existe", exception.getMessage());
		
	}
}
