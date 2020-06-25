# Asesoría Delegacional (ASDE)
## Planteamiento
Asesoría delegacional es una empresa que se dedica a ofrecer servicios, asesoramientos y gestoria sobre trámites ante las distintas dependencias de gobierno por mencionar algunas:
- Ventanilla Única Delegacional (VUD).
- Secretaria de Desarrollo Urbano y Vivienda (SEDUVI).
- Comisión Federal de Competencia Económica (COFECE).
- Secretaria de Finanzas.
- etc.

En el cual su flujo de trabajo es aun analógico puesto que la organización tanto de sus clientes, cotizaciones y servicios que maneja se llevan acabo mediante una agenda física personal.

### Problematica
- Toda la información de sus clientes (RFC, Dirección, Correo y representantes legales) las maneja a través de su agenda física y mediante su celular (para contacto).
- Cuando le solicitan una cotización se realiza mediante Power Point y se envia al correo del cliente solicitante, el problema es que se almacena en su computadora personal y aveces no sabe el lugar en el que lo guardo ademas de tener una plantilla en el que no le permite acomodar los elementos como el desea y realizar los calculos de manera manual.
- Ya que cada trámite cuenta con distintos requisitos le es complicado recordar a cuales pertenece cada uno.
- Al ingresar un trámite ante algúna dependencia y recogerlo posteriormente el usuario pierde total seguimiento de la vigencia del mismo, esto le perjudica ya que no sabe cuando notificarle al cliente una renovación si asi lo surgiera.

## Requisitos
- Tener un sistema que se pueda acceder donde sea en el cual pueda gestionar la información de todos sus clientes, en el pueda ver los datelles de cada uno.
- Organizar todos los servicios que maneja, incluyendo los trámites que puede realizar.
- Dar un seguimiento de los tramites que ingresa ante las instituciones públicas desde el momento en que se ingresan hasta cuando lo tiene en su mano el cliente.
- Permitir elaborar cotizaciones y enviarlos directamente al cliente al cual se emite.
- El sistema tambien debe notificar un mes antes de que el trámite este por vencer.

### Métodologia
Se usara una metodologia tradicional (cascada), este sistema debe cumplir con el SDLC (Estándar sobre el ciclo de vida del software) cumpliendo con el ISO 12207, se mencionan las fases más comunes ya que pueden variar según el autor (Pressman, Sommerville o Kendall):
- Análisis.
- Diseño.
- Desarrollo.
- Pruebas.
- Implementación.
- Mantenimiento.

## Análisis 
En el proceso de analisis comunmente se genera un Software Requirements Specification (SRS) el cual debe cumplir con el estandar IEEE 830, en esta fase se tiene una platica con el cliente con el fin de saber todas las necesidades que requiere que realice el sistema aunque tambien se recolecta toda la información necesaria de los usuarios del sistema permitiendo ver el flujo y esquemas de trabajo, los métodos más comunes para la recolección de información son:
- Observación.
- Encuestas.
- Entrevistas.
- Cuestionarios.
El seleccionar alguna depende de la carga de trabajo que tengan los usuarios, tiempo, accesibilidad y hasta honestidad por parte de ellos.
Una vez obtenidos 

Por medio de la entrevista con el cliente y la recolección de información podremos sacar tanto los requerimientos funcionales como los no funcionales, recordando que:
- Requerimientos funcionales:  Describen lo que el sistema debe hacer, interacción entre el usuario y el sistema asi como la interacción con otros sistemas en caso de necesitarlo.
- Requerimientos No funcionales: Se refieren a características como rendimiento, facilidad de uso, presupuestos, tiempo de entrega, documentación, seguridad, etc.

### Requerimientos funcionales.
- Para el modulo de **clientes** el usuario debera poder:
	
    - Agregar un nuevo cliente.
    - Ver todos los detalles de un cliente.
    - Modificar la información de un cliente en especifico.
    - Eliminar a un cliente en especifico.
    - Generar un reporte en formato PDF de la lista de clientes registrados.
    - Generar un reporte en formato PDF de los detalles de cierto cliente.
- Para el modulo de **trámites** el usuario debera poder:
	
    - Crear un nuevo trámite.
    - Ver en los detalles de un trámite que requisitos necesita para ser ingresados, asi como las firmas de algun especialista.
    - Modificar la información de un trámite en especifico, asi como sus requisitos y firmas.
    - Eliminar a un trámite en especifico.
    - Generar un reporte en formato PDF de la lista de tramites que el cliente puede realizar.
    - Generar un reporte en formato PDF de los detalles de cierto trámite.
- Para el modulo de **ingresos** el usuario debera poder:
	
    - Crear un nuevo ingreso (estableciendo la fecha de ingreso y la fecha en que puede ir a recogerlo).
    - Ver los detalles y estado en que se encuentra el ingreso.
    - El estado de un ingreso puede ser: INGRESADO, COMPLETADO, PREVENCION.
    - Modificar la información de un ingreso, permitiendo cambiar el estado.
    - Una vez que se recoja el trámite debera calcular la fecha de vigencia del mismo.
    - Eliminar un ingreso en especifico.
    - Generar un reporte en formato PDF de la lista de ingresos que el cliente puede realizar.
- Para el modulo de **cotizaciones** el usuario debera poder:
	
    - Crear una nueva cotizacion.
    - Al agregarse un nuevo elemento debera hacer el calculo en automático del total generado.
    - Ver en los detalles de una cotización.
    - Modificar la información de una cotización en especifico.
    - Generar un reporte en formato PDF de la lista de cotizaciones.
    - Generar un reporte en formato PDF de una cotización en especifico.
    - Enviar desde el sistema la cotización al cliente que lo solicita.
- Para el modulo de **seguridad** solo:
	- Deben poder acceder los usuarios permitidos por el administrador del sistema.
    - Para acceder se requerira de su correo y contraseña generada por el administrador del sistema.
    
### Requerimientos no funcionales.
- El sistema debe ser amigable.
- El sistema y su navegación deben ser faciles de usar.
- Debe de ser seguro.

### Diagráma de caso de uso
![useCase][use-case]


[use-case]: https://raw.githubusercontent.com/Cruz-Bdllo/asde/master/img-readme/use_case.png "Diagram Use Case"