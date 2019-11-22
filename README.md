# Journi TvAds

Implemented the project in `Kotlin` as per mention in the requirement in problem statement that any JVM language can be used and expected a functional approach.
As I am not experienced with `Scala` but `Kotlin`, so chosen `Kotlin`.

#### Build
```bash
./gradlew fatJar
```

#### Clean
```bash
./gradlew clean
```

#### Run
```bash
java -jar ./build/libs/journi-tvads-1.0.jar {path_json_file}
```

### Performance
```bash
Then basic solution runs on: O(n*m)
where,
- n is the number of users
- m is the number of tvSpots
```
