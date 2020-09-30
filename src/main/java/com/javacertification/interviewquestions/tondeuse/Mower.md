# La podadora

La empresa podaAhora ha decidido desarrollar una podadora o cortacésped automático, destinada a
superficies rectangulares.
El cortacésped se puede programar para cubrir toda la superficie.
La posición del cortacésped está representada por una combinación de coordenadas (x, y) y un
letra que indica la orientación según la notación cardinal inglesa (N, E, W, S). El cesped es
dividido en una cuadrícula para simplificar la navegación.
Por ejemplo, la posición del cortacésped puede ser "0, 0, N", lo que significa que está ubicado
en la esquina inferior izquierda del césped y hacia el norte.
Para controlar el cortacésped, se le envía una secuencia simple de letras. Cartas posibles
son "D", "G" y "A". "D" y "G" giran el cortacésped 90 ° hacia la derecha o izquierda
respectivamente, sin moverlo. "A" significa que el cortacésped se mueve hacia adelante un espacio en el
dirección que enfrenta, y sin cambiar su orientación.
Si la posición después del movimiento está fuera del césped, el cortacésped no se mueve,
mantiene su orientación y procesa el siguiente comando.
Suponemos que el cuadro directamente al norte de la posición (x, y) tiene las coordenadas (x,
y + 1).
Para programar el cortacésped, se proporciona un archivo de entrada construido de la siguiente manera:
* La primera línea corresponde a las coordenadas de la esquina superior derecha del césped, esas
desde la esquina inferior izquierda se supone que es (0,0)
* El resto del archivo le permite controlar todos los cortacéspedes que se han implementado. Cada
el cortacésped tiene dos líneas al respecto:
* La primera línea proporciona la posición inicial del cortacésped, así como su orientación. El
posición y orientación se proporcionan en forma de 2 números y una letra, separados
por un espacio
* La segunda línea es una serie de instrucciones que le indican al cortacésped que explore
césped Las instrucciones son una serie de caracteres sin espacios.
Cada cortacésped se mueve secuencialmente, lo que significa que el segundo cortacésped no
se mueve solo cuando el primero ha ejecutado completamente su serie de instrucciones.
Cuando un cortacésped completa una serie de instrucciones, comunica su posición y
Orientación
### OBJETIVO
Diseñe y escriba un programa en uno de los siguientes idiomas: Java, Scala, Kotlin,
JavaScript, mecanografiado, Python, Go (si desea utilizar otro idioma, valídelo bien
antes con tu reclutador)
Este programa necesitará implementar la especificación anterior y pasar la prueba a continuación.
### PRUEBA
El siguiente archivo se proporciona como entrada:<br/>
**5 5** <br/>
**1 2 N**<br/>
**GAGAGAGAA**
**3 3 E**<br/>
**AADAADADDA**<br/>
Esperamos el siguiente resultado (posición final de las segadoras):<br/>
**1 3 N**<br/>
**5 1 E**<br/>
**NB**: los datos de entrada se inyectan en forma de archivo. 