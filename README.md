**TIEMPO ESTIMADO HORAS_HOMBRE:**

Juan Torres Viloria (@juatorr) 5h 30m.

Sergio Sanz Sanz (@sergsan) 8h.

Estimado por las issues hechas, estos datos no son fiables o realistas ya que hemos trabajado a la vez y nos hemos turnado issues.

**CLASES QUE FORMAN PARTE DE LA SOLUCIÓN:**

Author, Category, CollectionElement, Game, GameCollection, GameList, Main, HibernateUtil.

_Ficheros XML Hibernate_: Author.hbm.xml, Game.hbm.xml, GameList.hmb.xml, hibernate1.cfg.xml.

_Clases Test_: AuthorTest, GameListTest, GameTest, GameCollectionTest.

**INFORMACION ADICIONAL:**

Para la realización de la clase GameCollection y su correspondiente conexión con la base de datos a traves de Hibernate, hemos tenido algún problema, ya que los archivos de mapeo les tenemos correctos y la estructura de tablas en la base de datos se crea correctamente pero a la hora de guardar (session.save) los objetos nos da error que no hemos conseguido solucionar (Al hacer Halter Table).
