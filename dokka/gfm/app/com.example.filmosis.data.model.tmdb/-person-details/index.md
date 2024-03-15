//[app](../../../index.md)/[com.example.filmosis.data.model.tmdb](../index.md)/[PersonDetails](index.md)

# PersonDetails

[androidJvm]\
data class [PersonDetails](index.md)(val adult: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val also_known_as: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, val biography: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val birthday: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val deathday: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html), val gender: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val homepage: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html), val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val imdb_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val known_for_department: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val place_of_birth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val popularity: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), val profile_path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Clase que representa los detalles de una persona (actor, director, etc.) según la API de TMDB.

## Constructors

| | |
|---|---|
| [PersonDetails](-person-details.md) | [androidJvm]<br>constructor(adult: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), also_known_as: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, biography: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), birthday: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), deathday: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html), gender: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), homepage: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html), id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), imdb_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), known_for_department: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), place_of_birth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), popularity: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), profile_path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [adult](adult.md) | [androidJvm]<br>val [adult](adult.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indica si la persona es mayor de edad. |
| [also_known_as](also_known_as.md) | [androidJvm]<br>val [also_known_as](also_known_as.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;<br>Una lista de otros nombres conocidos de la persona. |
| [biography](biography.md) | [androidJvm]<br>val [biography](biography.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>La biografía de la persona. |
| [birthday](birthday.md) | [androidJvm]<br>val [birthday](birthday.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>La fecha de nacimiento de la persona. |
| [deathday](deathday.md) | [androidJvm]<br>val [deathday](deathday.md): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)<br>La fecha de fallecimiento de la persona (puede ser nulo si la persona está viva). |
| [gender](gender.md) | [androidJvm]<br>val [gender](gender.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>El género de la persona (1 para mujeres, 2 para hombres). |
| [homepage](homepage.md) | [androidJvm]<br>val [homepage](homepage.md): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)<br>La página web personal de la persona (puede ser nulo). |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>El ID único de la persona. |
| [imdb_id](imdb_id.md) | [androidJvm]<br>val [imdb_id](imdb_id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>El ID de IMDb de la persona. |
| [known_for_department](known_for_department.md) | [androidJvm]<br>val [known_for_department](known_for_department.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>El departamento conocido de la persona. |
| [name](name.md) | [androidJvm]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>El nombre de la persona. |
| [place_of_birth](place_of_birth.md) | [androidJvm]<br>val [place_of_birth](place_of_birth.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>El lugar de nacimiento de la persona. |
| [popularity](popularity.md) | [androidJvm]<br>val [popularity](popularity.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>La popularidad de la persona. |
| [profile_path](profile_path.md) | [androidJvm]<br>val [profile_path](profile_path.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>La ruta de la imagen de perfil de la persona. |
