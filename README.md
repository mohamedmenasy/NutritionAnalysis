# Nutrition Analysis - Compose [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=mohamedmenasy_NutritionAnalysis&metric=alert_status)](https://sonarcloud.io/dashboard?id=mohamedmenasy_NutritionAnalysis)

Nutrition Analysis is an application designed to analyze any recipe and get a detailed nutrition data about it .

## Features📝

### Functional Requirements

- Users can enter all ingredients they want to analyze in a free text.
- Users can see a summary breakdown for entered ingredients in a list.
- Users can see total nutrition facts on daily basis for entered ingredients.
- Users can use the application in portrait and landscape modes.

## Screenshots📷

| ![](https://raw.githubusercontent.com/mohamedmenasy/NutritionAnalysis/main/Screenshots/Screenshot_1_portrait_light.jpg) | ![](https://raw.githubusercontent.com/mohamedmenasy/NutritionAnalysis/main/Screenshots/Screenshot_2_portrait_light.jpg) | ![](https://raw.githubusercontent.com/mohamedmenasy/NutritionAnalysis/main/Screenshots/Screenshot_3_portrait_light.jpg) |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| ![](https://raw.githubusercontent.com/mohamedmenasy/NutritionAnalysis/main/Screenshots/Screenshot_4_portrait_light.jpg) | ![](https://raw.githubusercontent.com/mohamedmenasy/NutritionAnalysis/main/Screenshots/Screenshot_2_portrait_dark.jpg) | ![](https://raw.githubusercontent.com/mohamedmenasy/NutritionAnalysis/main/Screenshots/Screenshot_3_portrait_dark.jpg) |
| ![](https://raw.githubusercontent.com/mohamedmenasy/NutritionAnalysis/main/Screenshots/Screenshot_1_landscape_dark.jpg) | ![](https://raw.githubusercontent.com/mohamedmenasy/NutritionAnalysis/main/Screenshots/Screenshot_2_landscape_dark.jpg) | ![](https://raw.githubusercontent.com/mohamedmenasy/NutritionAnalysis/main/Screenshots/Screenshot_3_landscape_dark.jpg) |
| ![](https://raw.githubusercontent.com/mohamedmenasy/NutritionAnalysis/main/Screenshots/Screenshot_1_landscape_light.jpg) | ![](https://raw.githubusercontent.com/mohamedmenasy/NutritionAnalysis/main/Screenshots/Screenshot_2_landscape_light.jpg) | ![](https://raw.githubusercontent.com/mohamedmenasy/NutritionAnalysis/main/Screenshots/Screenshot_3_landscape_light.jpg) |

## Demo📱

[![Demo Video](https://raw.githubusercontent.com/mohamedmenasy/NutritionAnalysis/main/Screenshots/youtube_thumbnail.jpg)](https://www.youtube.com/watch?v=2P3mqUBVdwg)

## Built With🛠

- [Kotlin](https://kotlinlang.org/) - Official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For loading data asynchronously.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - For building the UI.
- [Jetpack Navigation Component](https://developer.android.com/jetpack/compose/navigation) - For navigating between screens in Jetpack Compose.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries and extensions that help you design robust, testable, and maintainable apps.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
- [Dagger Hilt](https://dagger.dev/hilt/) - Hilt is a dependency injection library for Android.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.

## Running the app

##### Using android studio

Just import the project and hit `run` button.

##### Using command line 

```
$ adb shell am start -n 'com.mohamednabil.nutritionanalysis/com.mohamednabil.nutritionanalysis'
```

## Running the tests

##### Unit Tests

###### To run the app module tests 

```bash
./gradlew app:app:testDebug
```

###### To run the core module tests 

```bash
./gradlew app:core:testDebug
```

## Todo✅

- [ ] Increase the test coverage.
- [ ] Add Espresso tests.

## Author

- **Mohamed Nabil** - *Initial work* - [linkedin](<https://www.linkedin.com/in/mohamedmenasy/>)