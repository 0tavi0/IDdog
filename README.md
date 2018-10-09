# IDdog
App com uma lista de cachorros para cada categoria pré-definida


### Instalação
***

Clone ou faça o download como ZIP e importe para o AndroidStudio.
Android SDK versão 27, portanto, dependendo da sua instalação, você pode ser solicitado a fazer o download dessa versão do SDK durante a compilação. (Você também pode querer atualizar sua versão do Gradle e o plugin Android Gradle, caso você esteja usando uma versão mais antiga).

### Uso
***
A interface do aplicativo consiste em três telas:

* `LoginEmailActivity`, como o ponto de entrada no aplicativo, pede um email para poder recuperar um token de acesso.
* `MainActivity`, onde fica as tabs com a categorias de cada cachorro, em cada tab tem um lista de cachorro equivalente a cada categoria. 
* `DogDetailsActivity`, mostra foto do cachorro escolhido expandida

### Libs Utilizadas
***


* [Fresco](https://frescolib.org) -  Lib para carregamento de imagens e caching 
* [Retrofit](http://square.github.io/retrofit/) - Lib para requisições HTTP 

### Telas
***

![screenshots](https://github.com/0tavi0/IDdog/blob/master/screens/screen.png)

###Download
***

Você pode baixar o apk [aqui](https://github.com/0tavi0/IDdog/blob/master/screens/app-debug.apk) e instalá-lo em seu telefone. Alternativamente, você pode clonar o repositório e executá-lo em um emulador usando o AndroidStudio.