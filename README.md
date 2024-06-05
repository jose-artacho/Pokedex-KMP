# ![alt text](https://github.com/jose-artacho/Pokedex-KMP/assets/13620416/d2878cf8-458a-4148-99ba-9be55b7187a1) Pokedex-KMP

This is a Kotlin Multiplatform project targeting Android and iOS. 
The logic and the UI is shared. This is a simple app using the [PokeApi](https://pokeapi.co/docs/v2)

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

| Android       | iOS           |
| ------------- | ------------- |
|<img src="https://github.com/jose-artacho/Pokedex-KMP/assets/13620416/1d2a6d9d-c2ea-46f0-8e37-07638d18fb7b" width="50%">|<img src="https://github.com/jose-artacho/Pokedex-KMP/assets/13620416/1cae1f8f-74c8-4d7f-bfa8-fdde2a0d6162" width="50%">|
