<h1 align="center">My ToDo</h1>
🛠 Project Features

<ul>
  <li>Model-View-ViewModel (MVVM) with clean architecture pattern</li>
  <li>Room database</li>
  <li>LiveData for reactive programming</li> 
  <li>ViewModel for managing UI-related data</li>
  <li>Navigation for navigating between fragments</li>
  <li>Data Binding for binding data to the UI</li>
  <li>Hilt for dependency injection</li>
  <li>Material Design for UI components</li>
  <li>AndroidX libraries for compatibility and feature support</li>
  <li>Custom RecyclerView for displaying lists of notes</li>
  <li>Custom DialogFragment for displaying notifications</li>
  <li>Custom menu options for navigating between screens</li>
  <li>Android's built-in alarm manager for scheduling notifications</li>
</ul>

<h4 align="start">The project is an Android application that allows users to create, update, and delete notes. It also has a notification feature that allows users to set a reminder for a note.

The app is built using the Model-View-ViewModel (MVVM) architecture pattern. The data layer consists of a Room database that stores the notes, a NoteRepository that provides an abstraction layer between the database and the ViewModel, and a WorkManager for scheduling notification tasks.

The app has four main screens: All notes screen, Add note screen, Update note screen, and Notification screen. The All notes screen displays all notes in the database, allowing users to select a note to view or edit. The Add note screen allows users to create a new note by entering a title and body. The Update note screen allows users to edit an existing note's title and body. The Notification screen allows users to set a reminder for a specific note by selecting a date and time. 

The ViewModel layer consists of four ViewModels - AllNoteViewModel, AddNoteViewModel, UpdateViewModel, and NotificationViewModel. These ViewModels are responsible for providing the data to the views, handling user actions, and interacting with the repository. These ViewModels are responsible for providing the data to the views, handling user actions, and interacting with the repository.

The View layer consists of several fragments - AllNoteFragment, AddNoteFragment, UpdateFragment, and NotificationFragment. These fragments are responsible for displaying the data to the user, capturing user input, and navigating between different screens.

DiffUtil is another library used in the project. It is a utility class that calculates the difference between two lists and provides a list of update operations that can be used to efficiently update a RecyclerView adapter. It is used in the AllNoteAdapter to improve the performance of the RecyclerView when updating the list of notes.

The app uses Hilt for dependency injection and Jetpack Navigation for navigation between fragments. It also uses Data Binding for binding data to the UI, LiveData for reactive programming, and Coroutines for handling asynchronous tasks.

Overall, the app follows the best practices and guidelines of modern Android app development and demonstrates the use of popular Android libraries and technologies.
</h4>

![mytodogif](https://user-images.githubusercontent.com/96176710/224835897-f3b0bef3-6d35-4073-83f0-e080843802f2.gif)
