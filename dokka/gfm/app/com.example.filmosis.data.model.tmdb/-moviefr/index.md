//[app](../../../index.md)/[com.example.filmosis.data.model.tmdb](../index.md)/[Moviefr](index.md)

# Moviefr

[androidJvm]\
data class [Moviefr](index.md)(val adult: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val backdrop_path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val genres: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Genre](../-genre/index.md)&gt;, val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val original_language: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val original_title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val overview: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val popularity: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), val poster_path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val release_date: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val video: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val vote_average: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), val vote_count: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val spoken_languages: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Idioma](../-idioma/index.md)&gt;)

Clase que representa una película según la API de TMDB (francés).

## Constructors

| | |
|---|---|
| [Moviefr](-moviefr.md) | [androidJvm]<br>constructor(adult: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), backdrop_path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), genres: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Genre](../-genre/index.md)&gt;, id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), original_language: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), original_title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), overview: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), popularity: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), poster_path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), release_date: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), video: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), vote_average: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), vote_count: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), spoken_languages: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Idioma](../-idioma/index.md)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [adult](adult.md) | [androidJvm]<br>val [adult](adult.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indica si la película es para adultos. |
| [backdrop_path](backdrop_path.md) | [androidJvm]<br>val [backdrop_path](backdrop_path.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>La ruta de fondo de la imagen de la película. |
| [genres](genres.md) | [androidJvm]<br>val [genres](genres.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Genre](../-genre/index.md)&gt;<br>La lista de géneros asociados a la película. |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>El ID único de la película. |
| [original_language](original_language.md) | [androidJvm]<br>val [original_language](original_language.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>El idioma original de la película. |
| [original_title](original_title.md) | [androidJvm]<br>val [original_title](original_title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>El título original de la película. |
| [overview](overview.md) | [androidJvm]<br>val [overview](overview.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Una descripción general de la película. |
| [popularity](popularity.md) | [androidJvm]<br>val [popularity](popularity.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>La popularidad de la película. |
| [poster_path](poster_path.md) | [androidJvm]<br>val [poster_path](poster_path.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>La ruta de la imagen del póster de la película. |
| [release_date](release_date.md) | [androidJvm]<br>val [release_date](release_date.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>La fecha de estreno de la película. |
| [spoken_languages](spoken_languages.md) | [androidJvm]<br>val [spoken_languages](spoken_languages.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Idioma](../-idioma/index.md)&gt;<br>La lista de idiomas hablados en la película. |
| [title](title.md) | [androidJvm]<br>val [title](title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>El título de la película. |
| [video](video.md) | [androidJvm]<br>val [video](video.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indica si la película tiene video. |
| [vote_average](vote_average.md) | [androidJvm]<br>val [vote_average](vote_average.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>El promedio de votos de la película. |
| [vote_count](vote_count.md) | [androidJvm]<br>val [vote_count](vote_count.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>El número total de votos recibidos por la película. |
