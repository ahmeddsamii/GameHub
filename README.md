# GameHub

A robust Android application for browsing video games by genre, powered by the **RAWG API**.

## Tech Stack
**Language:** Kotlin
**UI:** Jetpack Compose
**Architecture:** MVVM (Model-View-ViewModel)
**Async/Reactive:** Kotlin Coroutines + Flow
**Networking:** Ktor
**Dependency Injection:** Koin Annotations (using KSP Annotations)
**Local Database:** Room (Offline Caching)
**Testing:** JUnit + MockK


## Architecture Choice
The project follows **Clean Architecture** principles to ensure a separation of concerns and high testability.
1. **Domain Module:** A pure Kotlin module containing Entities, Repository interfaces, and Use Cases.
2. **Data Module:** Handles data logic, coordinating between the Ktor network client and the Room database.
3. **Presentation Module:** Manages UI state using ViewModels and renders the interface with Jetpack Compose.

**Rationale:** This decoupling ensures that business logic remains independent of the UI or data frameworks, making the application scalable and easy to maintain.

## Features Implemented
**Games List Screen:** Displays name, image, and rating for each game
**Pagination:** Automatically loads additional results as the user scrolls
**Local Search:** Filters already-loaded games in-memory without additional API calls
**Genre Selection:** Fetches genres directly from the API to allow users to browse by category
**Game Details Screen:** Shows name, image, release date, rating, and description
**Offline Support:** Implemented an **offline-first strategy** using Room to cache results for offline viewing
**State Management:** Distinct handling for Loading, Error (with retry), and Empty states

---

## How to Run

### 1. Prerequisites
* Android Studio.
* **Kotlin 2.0.21** (consistent with project build files).

### 2. API Key Setup
1. Get an API key from [RAWG.io](https://rawg.io/apidocs).
2. Paste it in Local.properties (API_KEY="YOUR-API-KEY")

### 3. Build and Install
1. Clone the repository
2. Open the project in Android Studio and sync Gradle.
3. Select the `app` module and click **Run**.

### 4. Running Tests
* [**Use Case Tests:** Right-click the `src/test` folder in the **Domain** module and select **Run 'Tests**.
* Tests are implemented using **MockK** and **JUnit**.
