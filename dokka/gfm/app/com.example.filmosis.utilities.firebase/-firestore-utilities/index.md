//[app](../../../index.md)/[com.example.filmosis.utilities.firebase](../index.md)/[FirestoreUtilities](index.md)

# FirestoreUtilities

[androidJvm]\
object [FirestoreUtilities](index.md)

Clase singleton que proporciona utilidades para interactuar con Firestore en Firebase.

## Functions

| Name | Summary |
|---|---|
| [createUserListEntryInFirestore](create-user-list-entry-in-firestore.md) | [androidJvm]<br>fun [createUserListEntryInFirestore](create-user-list-entry-in-firestore.md)(firestore: FirebaseFirestore, email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Crea una entrada de lista de usuario en Firestore. |
| [saveUserInFirestore](save-user-in-firestore.md) | [androidJvm]<br>fun [saveUserInFirestore](save-user-in-firestore.md)(firestore: FirebaseFirestore, auth: FirebaseAuth, username: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), fullName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), birthDate: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), callback: ([Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Guarda la información del usuario en Firestore. |
