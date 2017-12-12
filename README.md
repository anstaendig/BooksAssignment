# Books Assignment
Simple client that fetches volumes based on a text search and supports pagination. 

## Architecture
### Structure
This project implements the [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html). 

Each layer lives in it's own module for proper isolation.

### View
The view and presentation layers are implemented in a reactive manner. Data flow is unidirectional. The UI only renders a immutable state object that the presentation layer (ViewModel) creates by transformation events to actions, executes business logic and then creates based on results a new viewstate. This pattern is explained [here](https://www.youtube.com/watch?v=0IKHxjkgop4) in detail. Very similar to [Hannes Dorfmann's - Model-View-Intent](http://hannesdorfmann.com/android/mosby3-mvi-1).

## Libraries used
 - [Dagger](https://google.github.io/dagger/) for dependency injection
 - [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html) to preserve ViewModels on configuration change
 - [RxJava](https://github.com/ReactiveX/RxJava) for asynchronous data fetching/manipulation
 - [RxRelay](https://github.com/JakeWharton/RxRelay) to wrap subjects for error less event streams
 - [Retrofit](http://square.github.io/retrofit/) as a http client
 - [Picasso](http://square.github.io/picasso/) for image loading
 - [AssertJ](http://joel-costigliola.github.io/assertj/) for fluent assertions
 - [Mockito](http://site.mockito.org/) as a mocking framework

## Next steps
 - Persistence layer with Room for caching
 - Authorisation for personalised content
   - Favorites
   - Rating books
   - Shelves
 - Ui tests
