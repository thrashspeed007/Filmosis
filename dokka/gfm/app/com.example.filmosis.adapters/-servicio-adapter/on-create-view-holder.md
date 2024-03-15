//[app](../../../index.md)/[com.example.filmosis.adapters](../index.md)/[ServicioAdapter](index.md)/[onCreateViewHolder](on-create-view-holder.md)

# onCreateViewHolder

[androidJvm]\
open override fun [onCreateViewHolder](on-create-view-holder.md)(parent: [ViewGroup](https://developer.android.com/reference/kotlin/android/view/ViewGroup.html), viewType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html)

Crea y devuelve un [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html) correspondiente al tipo de vista.

#### Return

El nuevo [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html) que contiene la vista creada.

#### Parameters

androidJvm

| | |
|---|---|
| parent | El ViewGroup al que se añadirá la nueva vista. |
| viewType | El tipo de la nueva vista. |

#### Throws

| | |
|---|---|
| [IllegalArgumentException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-argument-exception/index.html) | Si el viewType no es válido. |
