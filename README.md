# RubiconAssignment-NedimTarakcija

INSTALLATION

- The app can be installed using Installation.apk file located in the folder.

- The app can be tested on online emulator: https://appetize.io/
  Installed app link: https://appetize.io/app/r3qvde4brne6d37ewuk1g9mma0?device=nexus5&scale=75&orientation=portrait&osVersion=7.1


NOTES

- Number of item that appears when searching is set to maximum of 20 items (only first page of results).

- Movie class and TvShow class could be one class because they currently contain same data but it's better to use two different classes so it's easier to make changes in the future.


POSSIBLE OPTIMIZATIONS

- Every time a new character is added to search or removed from search, the service is called. This could be optimized by remembering previous search results and displaying them without calling a service after character is removed.

- Instead of using 4 Result receivers, we could use one generic Result Receiver. Same goes for Fetching classes.
