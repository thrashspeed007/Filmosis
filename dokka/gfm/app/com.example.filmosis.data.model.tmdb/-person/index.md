//[app](../../../index.md)/[com.example.filmosis.data.model.tmdb](../index.md)/[Person](index.md)

# Person

[androidJvm]\
data class [Person](index.md)(val adult: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val gender: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val known_for: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[KnownFor](../-known-for/index.md)&gt;, val known_for_department: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val original_name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val popularity: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), val profile_path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Clase que representa a una persona (actor, director, etc.) según la API de TMDB.

## Constructors

| | |
|---|---|
| [Person](-person.md) | [androidJvm]<br>constructor(adult: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), gender: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), known_for: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[KnownFor](../-known-for/index.md)&gt;, known_for_department: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), original_name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), popularity: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), profile_path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [adult](adult.md) | [androidJvm]<br>val [adult](adult.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indica si la persona es mayor de edad. |
| [gender](gender.md) | [androidJvm]<br>val [gender](gender.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>El género de la persona (1 para mujeres, 2 para hombres). |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>El ID único de la persona. |
| [known_for](known_for.md) | [androidJvm]<br>val [known_for](known_for.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[KnownFor](../-known-for/index.md)&gt;<br>La lista de producciones conocidas por la persona. |
| [known_for_department](known_for_department.md) | [androidJvm]<br>val [known_for_department](known_for_department.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>El departamento conocido de la persona. |
| [name](name.md) | [androidJvm]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>El nombre de la persona. |
| [original_name](original_name.md) | [androidJvm]<br>val [original_name](original_name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>El nombre original de la persona. |
| [popularity](popularity.md) | [androidJvm]<br>val [popularity](popularity.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>La popularidad de la persona. |
| [profile_path](profile_path.md) | [androidJvm]<br>val [profile_path](profile_path.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>La ruta de la imagen de perfil de la persona. |
