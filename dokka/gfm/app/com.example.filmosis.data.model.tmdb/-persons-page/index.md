//[app](../../../index.md)/[com.example.filmosis.data.model.tmdb](../index.md)/[PersonsPage](index.md)

# PersonsPage

[androidJvm]\
data class [PersonsPage](index.md)(val page: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val results: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Person](../-person/index.md)&gt;, val total_pages: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val total_results: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Clase que representa una página de resultados de personas obtenida de la API de TMDB.

## Constructors

| | |
|---|---|
| [PersonsPage](-persons-page.md) | [androidJvm]<br>constructor(page: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), results: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Person](../-person/index.md)&gt;, total_pages: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), total_results: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [page](page.md) | [androidJvm]<br>val [page](page.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>El número de página actual. |
| [results](results.md) | [androidJvm]<br>val [results](results.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Person](../-person/index.md)&gt;<br>La lista de personas en la página actual. |
| [total_pages](total_pages.md) | [androidJvm]<br>val [total_pages](total_pages.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>El número total de páginas disponibles. |
| [total_results](total_results.md) | [androidJvm]<br>val [total_results](total_results.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>El número total de resultados de personas. |