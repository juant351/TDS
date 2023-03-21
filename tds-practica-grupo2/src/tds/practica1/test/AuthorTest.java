package tds.practica1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import tds.practica1.*;

class AuthorTest {

	@Test
	public void authorTest() {
		Author autor = new Author("José", "Fernandez");
		assertEquals("José", autor.getName());
		assertEquals("Fernandez",autor.getSurname());
		assertEquals(1, autor.getId());
		autor.setName("Alberto");
		autor.setSurname("Alonso");
		assertEquals("Alberto", autor.getName());
		assertEquals("Alonso", autor.getSurname());
		assertEquals(1, autor.getId());
	}
	
	@Test
	public void testGetGames() {
		Author author = new Author("Juan", "Hernández");
		GameList gameList = new GameList("Lista");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		Game game = new Game("Minecraft", 2,4,14, Category.Eurogames, authors);
		author.addGame(game);
		gameList.addGame(game);
		assertEquals(gameList.getGames(), author.getGames());
		author.removeGame(game);
		gameList.removeGame(game);
		assertEquals(gameList.getGames(), author.getGames());
	}
	@Test
	public void testAddGameError() {
		Author author = new Author("Juan", "Hernández");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		Game game = new Game("Minecraft", 2,4,14, Category.Eurogames, authors);
		author.addGame(game);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> author.addGame(game));
		assertEquals("Juego ya esta añadido", exception.getMessage());
	}
	
	@Test 
	public void testRemoveGameError() {
		Author author = new Author("Juan", "Hernández");
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
		Game game = new Game("Minecraft", 2,4,14, Category.Eurogames, authors);
		Exception exception = assertThrows(NullPointerException.class, () -> author.removeGame(game));
		assertEquals("Lista vacia", exception.getMessage());
		author.addGame(game);
		Game gameNoExiste = new Game("COD", 2,4,14, Category.Eurogames, authors);
		Exception exceptionNoExiste = assertThrows(IllegalArgumentException.class, () -> author.removeGame(gameNoExiste));
		assertEquals("Juego no existe en la lista", exceptionNoExiste.getMessage());
	}

}
