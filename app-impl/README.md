The `app-impl` module contains concrete implementations of use cases which interact with concrete gateway over interface.
Here we can see we don't depend on gateway implementation and can easily switch to another one.
Also we can easily test our domain logics - find tests for this.

**Note:** originally use case implementations (interactors) should have only *application* logics (like notifications, etc) and *domain* logics (that does not depend on applications) should be implemented in domain entities (*Note* instance here), but just for simplicity i'm fine with current code but keeping that in mind.
