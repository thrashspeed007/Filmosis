//[app](../../../index.md)/[com.example.filmosis.data.access.tmdb](../index.md)/[MoviesAccess](index.md)/[listUpcomingMoviesWithGenres](list-upcoming-movies-with-genres.md)

# listUpcomingMoviesWithGenres

[androidJvm]\
fun [listUpcomingMoviesWithGenres](list-upcoming-movies-with-genres.md)(genres: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;, callback: ([List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Movie](../../com.example.filmosis.data.model.tmdb/-movie/index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Obtiene una lista de próximas películas con los géneros especificados.

#### Parameters

androidJvm

| | |
|---|---|
| genres | Lista de identificadores de géneros. |
| callback | Función de devolución de llamada que se invocará cuando se obtengan los datos. |
