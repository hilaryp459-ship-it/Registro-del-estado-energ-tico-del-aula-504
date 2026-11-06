# Registro del estado energético del aula 504

Prototipo de consola en Java para el proyecto de **Ingeniería de Software I** (Corporación Universitaria Remington).
Permite registrar la programación académica del aula 504 y el estado observado del aula (ocupación, computadores, luminarias y aire acondicionado) y compararlos para señalar **posibles inconsistencias**.

> El sistema no mide consumo eléctrico ni confirma desperdicio de energía: solo documenta situaciones que podrían indicar un uso innecesario de los equipos.

## Integrantes

| Integrante | Entidad | Historias | Archivos |
|---|---|---|---|
| Hilary Pérez Zúñiga | PROGRAMACION_ACADEMICA | HU-01 a HU-06 (CU-15) | `ProgramacionAcademica.java`, `PilaProgramacion.java`, `GestorProgramacion.java` |
| Luijeimm Agudelo Godoy | REGISTRO_ESTADO y consultas | HU-07 a HU-14 (CU-16) | `RegistroEstado.java`, `PilaRegistro.java`, `GestorRegistro.java` |

Compartidos: `Main.java` (pantalla principal) y `Util.java` (lectura y validación).
Punto de contacto entre entidades: `GestorRegistro.comparar()` llama a `GestorProgramacion.hayBloquesEl()` y `GestorProgramacion.bloqueQueCubre()`.

## Cómo ejecutarlo

Requiere JDK 8 o superior. Desde esta carpeta (`Prototipo`):

```bash
javac *.java
java Main
```

## Uso

- Menús por número; cada opción muestra su CU / HU / RF.
- Formularios con `[G] Guardar  [L] Limpiar  [C] Cancelar`. Al guardar se validan todos los campos y, si algo falla, aparece una línea `MENSAJE:` con el texto de las Tablas 56 a 58 del documento.
- Consultas con `[B] Buscar  [C] Cancelar`; las comparaciones muestran una línea `RESULTADO:`.
- Formatos estrictos: hora `HH:MM` (08:15) y fecha `dd/mm/aaaa`.

## Estructuras y reglas

- Arreglos de tamaño fijo con contador (100 bloques, 200 registros).
- Pila propia por entidad para deshacer la última eliminación (sin `java.util.Stack`).
- RN-01: orden de comparación. RN-02: un bloque cubre la hora si `hora_inicio ≤ hora < hora_fin`. RN-03: los bloques del mismo día no se superponen (sí pueden ser contiguos).

## Archivos de datos

| Archivo | Formato por línea |
|---|---|
| `programacion.txt` | `id;dia;hora_inicio;hora_fin` |
| `registros.txt` | `id;ocupacion;computadores;luminarias;aire;fecha;hora;persona` (equipos en `true`/`false`) |

Se guardan en cada cambio y se cargan al iniciar. El programa los crea en la carpeta desde donde se ejecuta. Los archivos de datos del equipo van en esta misma carpeta del repositorio.
