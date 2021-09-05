# Käyttöohje

## Asennus- ja käynnistysohje

Lataa asennustiedosto GitHubista. Avaa kansio terminaalilla ja anna seuraavat komennot:

```
cd mazeGenerationAndSolving
./gradlew shadowJar
java -jar build/libs/mazeGenerationAndSolving.jar
```


## Sovelluksen käyttöohje

Sovelluksella on käyttöliittymä, jota käytetään antamalla komentoja numeroilla. Sovellus tulostaa ennen kunkin komennon antamista näkyville listan käytettävissä olevista komennoista ja niiden merkityksistä. Käyttäjä valitsee ensin, millä algoritmilla haluaa luoda labyrintin, ja sen jälkeen, millä algoritmilla haluaa sen ratkaista. Käyttäjä voi ratkaista saman labyrintin usealla algoritmilla. Labyrintit tulostetaan automaattisesti niiden luomisen ja ratkaisemisen jälkeen. Lisäksi käyttäjä voi valita suorittavansa algoritmien suorituskyvyn testit.