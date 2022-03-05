### Razzies Awards

_Razzies é um prêmio cinematográfico humorístico dos Estados Unidos, concebido como uma paródia do Oscar pelo publicitário de Hollywood John Wilson._ - Wikipedia

#### Objetivo do sistema

Tomando como fonte de dados um arquivo CSV, o sistema guarda essas informações e transforma em um ranking.


### Como utilizar

Começe clonando o projeto:

``git clone xxxx golden-raspberry-awards && cd golden-raspberry-awards``

Com o projeto em mãos você já pode executa-lo com o [maven](https://maven.apache.org/install.html):

``mvn spring-boot:run``

**Porta padrão** de execução é 8081 porem pode ser alterada via variável de ambiente **APPLICATION_PORT**

#### Incluir mais dados
Para incluir novos arquivos basta incluir na pasta csv 

### Referência
 Definição da API; 
#### Awards

Retorna o produtor com maior intervalo entre 2 premios, e produtore com menor intervalo entre os premios.  

``GET /api/movie/min-and-max-awards``

#### Shell
````shell
curl --request GET -sL \
     --url 'http://localhost:8081/api/producer/min-and-max-awards'
````

### Testes
Para todas os testes do sistema utilize:

```mvn tests```