//[app](../../../../index.md)/[com.example.filmosis.adapters](../../index.md)/[MoviesInListAdapter](../index.md)/[MovieRowViewHolder](index.md)

# MovieRowViewHolder

class [MovieRowViewHolder](index.md)(itemView: [View](https://developer.android.com/reference/kotlin/android/view/View.html)) : [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html), [View.OnCreateContextMenuListener](https://developer.android.com/reference/kotlin/android/view/View.OnCreateContextMenuListener.html)

ViewHolder para una fila de la RecyclerView que representa una película.

#### Parameters

androidJvm

| | |
|---|---|
| itemView | Vista de la fila. |

## Constructors

| | |
|---|---|
| [MovieRowViewHolder](-movie-row-view-holder.md) | [androidJvm]<br>constructor(itemView: [View](https://developer.android.com/reference/kotlin/android/view/View.html)) |

## Properties

| Name | Summary |
|---|---|
| [itemView](../../-servicio-adapter/-network-details-view-holder/index.md#29975211%2FProperties%2F-912451524) | [androidJvm]<br>@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)<br>val [itemView](../../-servicio-adapter/-network-details-view-holder/index.md#29975211%2FProperties%2F-912451524): [View](https://developer.android.com/reference/kotlin/android/view/View.html) |
| [movieDate](movie-date.md) | [androidJvm]<br>val [movieDate](movie-date.md): [TextView](https://developer.android.com/reference/kotlin/android/widget/TextView.html) |
| [movieName](movie-name.md) | [androidJvm]<br>val [movieName](movie-name.md): [TextView](https://developer.android.com/reference/kotlin/android/widget/TextView.html) |
| [moviePoster](movie-poster.md) | [androidJvm]<br>val [moviePoster](movie-poster.md): [ImageView](https://developer.android.com/reference/kotlin/android/widget/ImageView.html) |
| [movieVoteAverage](movie-vote-average.md) | [androidJvm]<br>val [movieVoteAverage](movie-vote-average.md): [TextView](https://developer.android.com/reference/kotlin/android/widget/TextView.html) |

## Functions

| Name | Summary |
|---|---|
| [getAbsoluteAdapterPosition](../../-servicio-adapter/-network-details-view-holder/index.md#358648312%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getAbsoluteAdapterPosition](../../-servicio-adapter/-network-details-view-holder/index.md#358648312%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getAdapterPosition](../../-servicio-adapter/-network-details-view-holder/index.md#644519777%2FFunctions%2F-912451524) | [androidJvm]<br>fun [~~getAdapterPosition~~](../../-servicio-adapter/-network-details-view-holder/index.md#644519777%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getBindingAdapter](../../-servicio-adapter/-network-details-view-holder/index.md#-646392777%2FFunctions%2F-912451524) | [androidJvm]<br>@[Nullable](https://developer.android.com/reference/kotlin/androidx/annotation/Nullable.html)<br>fun [getBindingAdapter](../../-servicio-adapter/-network-details-view-holder/index.md#-646392777%2FFunctions%2F-912451524)(): [RecyclerView.Adapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html)&lt;out [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html)&gt;? |
| [getBindingAdapterPosition](../../-servicio-adapter/-network-details-view-holder/index.md#1427640590%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getBindingAdapterPosition](../../-servicio-adapter/-network-details-view-holder/index.md#1427640590%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getItemId](../../-servicio-adapter/-network-details-view-holder/index.md#1378485811%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getItemId](../../-servicio-adapter/-network-details-view-holder/index.md#1378485811%2FFunctions%2F-912451524)(): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [getItemViewType](../../-servicio-adapter/-network-details-view-holder/index.md#-1649344625%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getItemViewType](../../-servicio-adapter/-network-details-view-holder/index.md#-1649344625%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getLayoutPosition](../../-servicio-adapter/-network-details-view-holder/index.md#-1407255826%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getLayoutPosition](../../-servicio-adapter/-network-details-view-holder/index.md#-1407255826%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getOldPosition](../../-servicio-adapter/-network-details-view-holder/index.md#-1203059319%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getOldPosition](../../-servicio-adapter/-network-details-view-holder/index.md#-1203059319%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getPosition](../../-servicio-adapter/-network-details-view-holder/index.md#-1155470344%2FFunctions%2F-912451524) | [androidJvm]<br>fun [~~getPosition~~](../../-servicio-adapter/-network-details-view-holder/index.md#-1155470344%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isRecyclable](../../-servicio-adapter/-network-details-view-holder/index.md#-1703443315%2FFunctions%2F-912451524) | [androidJvm]<br>fun [isRecyclable](../../-servicio-adapter/-network-details-view-holder/index.md#-1703443315%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onCreateContextMenu](on-create-context-menu.md) | [androidJvm]<br>open override fun [onCreateContextMenu](on-create-context-menu.md)(menu: [ContextMenu](https://developer.android.com/reference/kotlin/android/view/ContextMenu.html)?, v: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, menuInfo: [ContextMenu.ContextMenuInfo](https://developer.android.com/reference/kotlin/android/view/ContextMenu.ContextMenuInfo.html)?)<br>Configura el menú contextual para la fila de la película. |
| [setIsRecyclable](../../-servicio-adapter/-network-details-view-holder/index.md#-1860912636%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setIsRecyclable](../../-servicio-adapter/-network-details-view-holder/index.md#-1860912636%2FFunctions%2F-912451524)(p0: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [toString](../../-servicio-adapter/-network-details-view-holder/index.md#-1200015593%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [toString](../../-servicio-adapter/-network-details-view-holder/index.md#-1200015593%2FFunctions%2F-912451524)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
