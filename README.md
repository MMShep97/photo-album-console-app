# Photo Album Kata

## Problem Statement

Create a console application that displays photo ids and titles in an album. The photos are available in this online web
service: https://jsonplaceholder.typicode.com/photos

Hint: Photos are filtered with a query string. This will return photos within `albumId=3`
https://jsonplaceholder.typicode.com/photos?albumId=3

Example:
```
> photo-album 2
[53] soluta et harum aliquid officiis ab omnis consequatur
[54] ut ex quibusdam dolore mollitia
…
> photo-album 3
[101] incidunt alias vel enim
[102] eaque iste corporis tempora vero distinctio consequuntur nisi nesciunt
```


## Running locally

### Options:

> Note: You can peak inside of the `Makefile` and run the commands individually if you don't have `Make` installed.

#### Option 1 (containerized)

Prerequisites: `Docker`, `Make`

`make dockerRun`

#### Option 2 (local)

Prerequisites: `JDK >= 11`, `Intellij` (that should be all...)

Navigate to `Main.kt` (`src/main/kotlin/photoalbum/Main.kt`) and hit the green run button!
