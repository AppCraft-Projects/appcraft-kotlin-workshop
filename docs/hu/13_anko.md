# Anko 

Az Android SDK a Java-val időnként meglehetősen körülményes és terjengős tud lenni. Ugye sokszor mocorog a Ti fejetekben is az, hogy tuti kell legyen erre egy egyszerűbb és rövidebb megoldás. Nos, a Kotlin csapat is hasonlóképp gondolta, ezért hozta létre az Anko library-t, amivel az Android fejlesztés könnyebbé és egyszerűbbé válhat. Tisztább lesz a kód, egyszerűbb lesz megírni és persze olvasni is.

Számos területen segíthet, öt nagy területen:
- Commons
  - Intentek
  - Dialógusablakok és toastok
  - Loggolás
  - Resource-ok kezelése
- Layout  
  - Layoutok készítése kódból
- SQLite
  - SQLite kezelés 
- Corutins
  - Corutinok kezelése
- Support libraries
  - Kiegészítések tucatnyi support library osztályhoz

Itt csak úgy csendben jegyzem meg, az Anko készítői előszeretettel használták kiegészítő funkciókat.

## Egy kis setup

A Gradle függőségek is ennek a bontásnak megfelelően jelennek meg. Nem egy nagyot kell behúzni, hanem tucatnyi kisebbet, így kisebb lehet az APK és method count szempontból is sokkal kíméletesebb.

Előbb az első négy kapcsán:
```groovy
dependencies {
  // Anko Commons
  compile "org.jetbrains.anko:anko-commons:$anko_version"

  // Anko Layouts
  compile "org.jetbrains.anko:anko-sdk25:$anko_version" // sdk15, sdk19, sdk21, sdk23 are also available
  compile "org.jetbrains.anko:anko-appcompat-v7:$anko_version"

  // Coroutine listeners for Anko Layouts
  compile "org.jetbrains.anko:anko-sdk25-coroutines:$anko_version"
  compile "org.jetbrains.anko:anko-appcompat-v7-coroutines:$anko_version"

  // Anko SQLite
  compile "org.jetbrains.anko:anko-sqlite:$anko_version"
}
```

Valamint a support libraryhoz tartozó:
```groovy
dependencies {
  // Appcompat-v7 (only Anko Commons)
  compile "org.jetbrains.anko:anko-appcompat-v7-commons:$anko_version"

  // Appcompat-v7 (Anko Layouts)
  compile "org.jetbrains.anko:anko-appcompat-v7:$anko_version"
  compile "org.jetbrains.anko:anko-coroutines:$anko_version"

  // CardView-v7
  compile "org.jetbrains.anko:anko-cardview-v7:$anko_version"

  // Design
  compile "org.jetbrains.anko:anko-design:$anko_version"
  compile "org.jetbrains.anko:anko-design-coroutines:$anko_version"

  // GridLayout-v7
  compile "org.jetbrains.anko:anko-gridlayout-v7:$anko_version"

  // Percent
  compile "org.jetbrains.anko:anko-percent:$anko_version"

  // RecyclerView-v7
  compile "org.jetbrains.anko:anko-recyclerview-v7:$anko_version"
  compile "org.jetbrains.anko:anko-recyclerview-v7-coroutines:$anko_version"

  // Support-v4 (only Anko Commons)
  compile "org.jetbrains.anko:anko-support-v4-commons:$anko_version"

  // Support-v4 (Anko Layouts)
  compile "org.jetbrains.anko:anko-support-v4:$anko_version"
}
```

## Példák

Nézzünk néhányra példákat, szigorúan a teljesség igénye nélkül.

### Intentek

Korábban a Java-ban ez így nézett ki:

```java
val intent = Intent(this, SomeOtherActivity::class.java)
intent.putExtra("id", 5)
intent.setFlag(Intent.FLAG_ACTIVITY_SINGLE_TOP)
startActivity(intent)
```

Ez az, amire az elején utaltam, érzésre nem kell ennek négy sor, mellesleg boilerplate szagú. De ezen segít az Anko:
```kotlin
startActivity(intentFor<SomeOtherActivity>("id" to 5).singleTop())
```

Sőőt, van még rövidebb is, ha nem szeretnél flaget átadni:
```kotlin
startActivity<SomeOtherActivity>("id" to 5)
```

Ez persze csak a navigáció, de támogatott a többi intent típus is.

### Dialógusablakok és toastok

#### Toast
Korábban Java-ban így ment ez:
```java
Context context = getApplicationContext();
CharSequence text = "Hello toast!";
int duration = Toast.LENGTH_SHORT;

Toast toast = Toast.makeText(context, text, duration);
toast.show();
```

Most viszont ennyiből megvannak:
```kotlin
toast("Hi there!")
toast(R.string.message)
longToast("Wow, such duration")
```

#### Snackbar

Korábban Java-ban így ment ez:
```java
Snackbar snackbar = Snackbar
  .make(coordinatorLayout, "Welcome to AndroidHive", Snackbar.LENGTH_LONG);
 
snackbar.show();
```

Most pedig: 
```kotlin
snackbar(view, "Hi there!")
snackbar(view, R.string.message)
longSnackbar(view, "Wow, such duration")
snackbar(view, "Action, reaction", "Click me!") { doStuff() }
```

#### Alert 

Most már tuti elhiszitek nekem, hogy ez Java-ban körülményes, Anko-val így néz ki:
```kotlin
alert("Hi, I'm Roy", "Have you tried turning it off and on again?") {
  yesButton { toast("Oh…") }
  noButton {}
}.show()

// VAGY rövidebben

alert(Appcompat, "Some text message").show()
```

### Loggolás

Habár az android.util.Log sem rossz, de azért lehet ezt még jobban csinálni. Nem kell a "tag"-el sem foglalkozni.

```kotlin
class SomeActivity : Activity(), AnkoLogger {
  private fun someMethod() {
    info("London is the capital of Great Britain")
    debug(5) // .toString() method will be executed
    warn(null) // "null" will be printed
  }
}
```
