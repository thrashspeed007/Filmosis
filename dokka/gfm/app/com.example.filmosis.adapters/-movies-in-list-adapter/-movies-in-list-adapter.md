//[app](../../../index.md)/[com.example.filmosis.adapters](../index.md)/[MoviesInListAdapter](index.md)/[MoviesInListAdapter](-movies-in-list-adapter.md)

# MoviesInListAdapter

[androidJvm]\
constructor(movies: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[ListedMovie](../../com.example.filmosis.dataclass/-listed-movie/index.md)&gt;, onMovieClick: ([ListedMovie](../../com.example.filmosis.dataclass/-listed-movie/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), isDeleteable: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, onDeleteMovie: ([ListedMovie](../../com.example.filmosis.dataclass/-listed-movie/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {})

#### Parameters

androidJvm

| | |
|---|---|
| movies | Lista de películas a mostrar. |
| onMovieClick | Acción a realizar cuando se hace clic en una película. |
| isDeleteable | Indica si se permite eliminar películas de la lista (predeterminado: false). |
| onDeleteMovie | Acción a realizar cuando se elimina una película (predeterminado: vacío). |
