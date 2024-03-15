//[app](../../../index.md)/[com.example.filmosis.fragments](../index.md)/[UserFragment](index.md)/[onActivityResult](on-activity-result.md)

# onActivityResult

[androidJvm]\
open override fun [onActivityResult](on-activity-result.md)(requestCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), data: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?)

Método invocado cuando se obtiene un resultado de alguna actividad lanzada para obtener una imagen de la galería. Se encarga de procesar la imagen seleccionada, mostrarla en la interfaz de usuario y cargarla en Firebase Storage.

#### Parameters

androidJvm

| | |
|---|---|
| requestCode | El código de solicitud de la actividad. |
| resultCode | El código de resultado de la actividad. |
| data | La intención que contiene los datos devueltos por la actividad. |
