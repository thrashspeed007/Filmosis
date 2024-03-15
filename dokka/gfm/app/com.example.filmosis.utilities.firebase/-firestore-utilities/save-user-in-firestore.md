//[app](../../../index.md)/[com.example.filmosis.utilities.firebase](../index.md)/[FirestoreUtilities](index.md)/[saveUserInFirestore](save-user-in-firestore.md)

# saveUserInFirestore

[androidJvm]\
fun [saveUserInFirestore](save-user-in-firestore.md)(firestore: FirebaseFirestore, auth: FirebaseAuth, username: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), fullName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), birthDate: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), callback: ([Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Guarda la información del usuario en Firestore.

#### Parameters

androidJvm

| | |
|---|---|
| firestore | Instancia de FirebaseFirestore. |
| auth | Instancia de FirebaseAuth. |
| username | Nombre de usuario. |
| email | Correo electrónico del usuario. |
| fullName | Nombre completo del usuario. |
| birthDate | Fecha de nacimiento del usuario en formato de cadena. |
| callback | Función de retorno de llamada que indica si la operación fue exitosa o no. |
