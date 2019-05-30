The `domain` module contains only domain entities and gateway interface declaration.
Sometimes `domain` and `app-api` modules are joined together, but i'd prefer to keep the clients unaware of domain entities and rely on DTOs from `app-api` only.

**Note**: originally *Gateway* wording was used, but currently *Repository* wording is used more frequent (though there is some difference).
