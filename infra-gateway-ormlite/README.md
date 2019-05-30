The `infra-gateway-ormlite` module contains concrete gateway implementation, using `ORMLite` here.
Note DB `Note` entity is introduced here in order to be independent of domain entity.
Here domain entity and db entity separation pays off: we can use db annotations to keep domain entities clean of database details. Mapping is done also in this module (not in interactors): "database is a detail".
Also find the tests.
