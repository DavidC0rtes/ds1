## Guía de desarrollo y contribución al proyecto

1. Identificar la funcionalidad, error, en el que se va a trabajar.
2. Abrir un issue y llenarlo siguiendo la plantilla adecuada.
3. Crear nueva rama **a partir de la rama `develop`** titulada issue_[numero_del_issue]
4. También pueden hacer una rama para la historia de usuario en la que van a trabajar. Cualquiera de los dos métodos está bien.
5. Cuando considere que el trabajo está terminado realizar un pull request desde su rama hacia `develop` detallando los cambios hechos.

#### Notas
* Solo se hacen pull requests desde las ramas individuales hacia develop.
* Cada que creen un issue nuevo no olviden agregarlo al proyecto del sprint para que aparezca en el kanban.

### Estilo

Cada contribución al proyecto debe acloparse a una sencilla guía de estilo para asegurarnos de que todos los integrantes entiendan el código generado por los demás:
* Nombres de las clases inician en mayúscula, ej: `User.java`, `Ejemplo.java`
* Los nombres de las clases deben ser descriptivos e indicar a que parte del MVC hacen parte, ej: `ControlFactura.java`, `ControlRegister.java`, `RegisterGUI.java`, `FacturaModelo.java`
* Creen carpetas/paquetes nuevos solo para almacenar las clases de una funcionalidad.
* Nombres de paquetes/carpetas en minúsculas.
* Documentar casi todo lo que puedan, en especial la primera línea de todas las clases, dónde indiquen que hace la clase.
* [camelCase](https://es.wikipedia.org/wiki/Camel_case)
