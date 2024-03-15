//[app](../../../index.md)/[com.example.filmosis.data.access.tmdb](../index.md)/[PersonsAccess](index.md)

# PersonsAccess

[androidJvm]\
class [PersonsAccess](index.md)

Clase que proporciona métodos para acceder a datos relacionados con personas desde la API de TMDb. Se utiliza la interfaz TmdbApiInterface donde se declaran las consultas con los parámetros necesarios.

## Constructors

| | |
|---|---|
| [PersonsAccess](-persons-access.md) | [androidJvm]<br>constructor()<br>Crea un objeto PersonsAccess. |

## Functions

| Name | Summary |
|---|---|
| [getPersonCombinedCredits](get-person-combined-credits.md) | [androidJvm]<br>fun [getPersonCombinedCredits](get-person-combined-credits.md)(personId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), callback: ([List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Cast](../../com.example.filmosis.data.model.tmdb/-cast/index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Obtiene los créditos combinados de una persona, que incluyen películas y programas de televisión en los que ha participado. |
| [getPersonDetails](get-person-details.md) | [androidJvm]<br>fun [getPersonDetails](get-person-details.md)(personId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), callback: ([PersonDetails](../../com.example.filmosis.data.model.tmdb/-person-details/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Obtiene los detalles de una persona específica. |
| [searchPerson](search-person.md) | [androidJvm]<br>fun [searchPerson](search-person.md)(query: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), callback: ([List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Person](../../com.example.filmosis.data.model.tmdb/-person/index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Busca personas basadas en una consulta proporcionada. |
