package tds.practica1;

import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author sergsan
 * @author juatorr
 *
 */
public class GameCollection {
	Set<Game> games;
	Set<GameList> gameLists;

	public void addGame(Game game) {
		if (game == null) {
			throw new NullPointerException("Game no puede ser nulo");
		}
		if (getGameByName(game.getName()) != null) {
			throw new IllegalArgumentException("El juego ya esta añadido");
		}

		Session session = getSession();
		try {
			session.getTransaction().begin();

			session.save(game);

			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public void addGameList(GameList gameList) {
		if (gameList == null) {
			throw new NullPointerException("GameList no puede ser nulo");
		}
		if (getGameListByName(gameList.getName()) != null) {
			throw new IllegalArgumentException("Ya existe una lista con ese nombre");
		}
		Session session = getSession();
		try {
			session.getTransaction().begin();

			session.save(gameList);

			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public void updateGame(Game game) {
		if (game == null) {
			throw new NullPointerException("Game no puede ser nulo");
		}
		if (getGameByName(game.getName()) == null) {
			throw new IllegalArgumentException("El juego no se puede actualizar porque no está.");
		}
		Session session = getSession();
		try {
			session.getTransaction().begin();
			Game gameUpdate = getGameByName(game.getName());
			session.update(gameUpdate);
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public void addGameToGameList(GameList gameList, Game game) {
		if (gameList == null) {
			throw new NullPointerException("GameList no puede ser nulo");
		}
		if (game == null) {
			throw new NullPointerException("Game no puede ser nulo");
		}
		Session session = getSession();
		try {
			session.getTransaction().begin();
			GameList gameListBuscar = getGameListByName(gameList.getName());
			gameListBuscar.addGame(game);
			session.update(gameListBuscar);
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public void removeGameFromGameList(GameList gameList, Game game) {
		if (gameList == null) {
			throw new NullPointerException("GameList no puede ser nulo");
		}
		if (game == null) {
			throw new NullPointerException("Game no puede ser nulo");
		}
		Session session = getSession();
		try {
			session.getTransaction().begin();
			GameList gameListBuscar = getGameListByName(gameList.getName());
			gameListBuscar.removeGame(game);
			session.update(gameListBuscar);
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public void removeCollectionElement(CollectionElement element) {
		if (element == null) {
			throw new NullPointerException("El CollectionElement no puede ser nulo");
		}
		if (element instanceof Game) {
			Session session = getSession();
			try {
				session.getTransaction().begin();
				Game game = session.get(Game.class, element.getId());
				session.remove(game);

				session.getTransaction().commit();
				session.close();

			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
		} else if (element instanceof GameList) {
			Session session = getSession();
			try {
				session.getTransaction().begin();
				GameList gamelist = session.get(GameList.class, element.getId());
				session.remove(gamelist);

				session.getTransaction().commit();
				session.close();

			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
		}
	}

	public CollectionElement getCollectionElementById(String id) {
		if (id == null) {
			throw new NullPointerException();
		}
		Session session = getSession();
		try {
			session.getTransaction().begin();
			CollectionElement element = session.get(CollectionElement.class, id);
			session.close();
			return element;

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	public Game getGameByName(String name) {
		if (name == null) {
			throw new NullPointerException();
		}
		Session session = getSession();
		try {
			session.getTransaction().begin();
			Game game = session.get(Game.class, name);
			session.close();
			return game;

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	public GameList getGameListByName(String name) {
		if (name == null) {
			throw new NullPointerException();
		}
		Session session = getSession();
		try {
			session.getTransaction().begin();
			GameList gamelist = session.get(GameList.class, name);
			session.close();
			return gamelist;

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	public void clearDatabase() {
		Session session = getSession();
		session.getTransaction().begin();
		Query query = session.createSQLQuery("Truncate table AUTHORS");
		query.executeUpdate();
		query = session.createSQLQuery("Truncate table GAME");
		query.executeUpdate();
		query = session.createSQLQuery("Truncate table GAMELIST");
		query.executeUpdate();
		session.close();
	}

	private Session getSession() {
		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session session = factory.getCurrentSession();

		return session;
	}

}
