//[app](../../../index.md)/[com.example.filmosis.data.model.tmdb](../index.md)/[KnownFor](index.md)

# KnownFor

[androidJvm]\
data class [KnownFor](index.md)(val adult: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val backdrop_path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val genre_ids: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;, val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val media_type: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val original_language: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val original_title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val overview: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val popularity: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), val poster_path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val release_date: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val video: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val vote_average: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), val vote_count: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Clase que representa las producciones conocidas por un miembro del elenco o equipo de producción según la API de TMDB.

## Constructors

| | |
|---|---|
| [KnownFor](-known-for.md) | [androidJvm]<br>constructor(adult: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), backdrop_path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), genre_ids: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;, id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), media_type: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), original_language: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), original_title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), overview: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), popularity: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), poster_path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), release_date: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), video: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), vote_average: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), vote_count: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [adult](adult.md) | [androidJvm]<br>val [adult](adult.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indica si la producción es para adultos. |
| [backdrop_path](backdrop_path.md) | [androidJvm]<br>val [backdrop_path](backdrop_path.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>La ruta de fondo de la imagen de la producción. |
| [genre_ids](genre_ids.md) | [androidJvm]<br>val [genre_ids](genre_ids.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;<br>La lista de IDs de género asociados a la producción. |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>El ID único de la producción. |
| [media_type](media_type.md) | [androidJvm]<br>val [media_type](media_type.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>El tipo de medio de la producción (película, serie de televisión, etc.). |
| [original_language](original_language.md) | [androidJvm]<br>val [original_language](original_language.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>El idioma original de la producción. |
| [original_title](original_title.md) | [androidJvm]<br>val [original_title](original_title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>El título original de la producción. |
| [overview](overview.md) | [androidJvm]<br>val [overview](overview.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Una descripción general de la producción. |
| [popularity](popularity.md) | [androidJvm]<br>val [popularity](popularity.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>La popularidad de la producción. |
| [poster_path](poster_path.md) | [androidJvm]<br>val [poster_path](poster_path.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>La ruta de la imagen del póster de la producción. |
| [release_date](release_date.md) | [androidJvm]<br>val [release_date](release_date.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>La fecha de estreno de la producción. |
| [title](title.md) | [androidJvm]<br>val [title](title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>El título de la producción. |
| [video](video.md) | [androidJvm]<br>val [video](video.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indica si la producción tiene video. |
| [vote_average](vote_average.md) | [androidJvm]<br>val [vote_average](vote_average.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>El promedio de votos de la producción. |
| [vote_count](vote_count.md) | [androidJvm]<br>val [vote_count](vote_count.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>El número total de votos recibidos por la producción. |
