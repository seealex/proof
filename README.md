
Requer:
	[apache-maven-3.5.3](http://ftp.unicamp.br/pub/apache/maven/maven-3/3.5.3/binaries/apache-maven-3.5.3-bin.zip)
	Rodar comando:
	```
	mvn clean install
	```

# 1 Serviço Campanha:
  ### POST
	 - http://localhost:8080/campaigns
  - Para se criar uma campanha
  ```
  curl -X POST http://localhost:8080/campaigns -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{ "validityDate": "2018-10-10", "idHeartTeam": "5aaed03643ccc105bc7b1ce0", "name": "Campaign 1" }' 
  ```
  Response:
  ``` 
  Location: http://localhost:8080/campaigns/5aaf364443ccc12210cfbd6f
  Content-Length: 0
  Status: 201 Created  
  ```
  
  ### GET all
	 - http://localhost:8080/campaigns
  - Para Pegar todas as campanhas cadastradas até o momento com a data de vigência válida
  ```
   curl -X GET http://localhost:8080/campaigns -H 'cache-control: no-cache' -H 'content-type: application/json' 
  ```
  Response:
  ```
  [{"id":"5aaf364443ccc12210cfbd6f","name":"Campaign 1","idHeartTeam":"5aaed03643ccc105bc7b1ce0","validityDate":"2018-10-10"}]
  Content-Type: application/json;charset=UTF-8
  Status: 200 OK
  ```
  
  ### GET oneById
	 - http://localhost:8080/campaigns/{id}
  - Para Pegar uma campanha especifica
  ```
  curl -X GET http://localhost:8080/campaigns/5aaf364443ccc12210cfbd6f -H 'cache-control: no-cache' -H 'content-type: application/json' 
  ```
  Response:
  ```
  {"id":"5aaf364443ccc12210cfbd6f","name":"Campaign 1","idHeartTeam":"5aaed03643ccc105bc7b1ce0","validityDate":"2018-10-10"}
  Content-Type: application/json;charset=UTF-8
  Status: 200 OK
  ```
  
  ### PUT
	 - http://localhost:8080/campaigns/{id}
  - Para fazer UPDATE em uma campanha especifica
  ```
   curl -X PUT http://localhost:8080/campaigns/5aaf364443ccc12210cfbd6f -H 'cache-control: no-cache'  -H 'content-type: application/json'  -d '{"id":"5aaf364443ccc12210cfbd6f", "name":"Campaign Altered", "idHeartTeam":"5aaed03643ccc105bc7b1ce0", "validityDate":"2018-12-18"}'
  ```
  Response:
  ```
  Content-Length: 0
  Status: 204 No Content
  ```
  
  ### DELETE
	 - http://localhost:8080/campaigns/{id}
  - Para fazer deletar uma campanha especifica
  ```
  curl -X DELETE http://localhost:8080/campaigns/5aaf364443ccc12210cfbd6f -H 'cache-control: no-cache'
  ```
  Response:
  ```
  Status: 200 OK
  ```
# 2 Serviço Usuário Sócio Torcedor:
  ### POST
	 - http://localhost:8081/users/
  Caso Usuário Já exista:
  ```
  curl -X POST http://localhost:8081/users/ -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{ "fullName": "Breno", "email": "breno@gmail.com", "birthdate": "1990-10-18", "heartTeam": { "name": "Rio" } }' -i
  ```
  Response:
  ```
  Status: 400 Bad Request 
  Content-Type: application/json;charset=UTF-8
  {"code":"400","errors":null,"description":"Cadastro já foi efetuado"}
  ```
  Caso Usuário novo:
  ```
  curl -X POST http://localhost:8081/users/ -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{ "fullName": "Breno", "email": "alex123@gmail.com", "birthdate": "1990-10-18", "heartTeam": { "name": "Sampa" } }' -i
  ```
  Response:
  ```
  Location: http://localhost:8081/users/5aaf3c4e43ccc12860c239b2
  Content-Length: 0
  Status: 201 Created
  ```
  Caso usuário antigo que não tem associados:
  - Parte 1 Criando usuário
  ```
  curl -X POST http://localhost:8081/users/ -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{ "fullName": "Breno", "email": "alex23@gmail.com", "birthdate": "1990-10-18", "heartTeam": { "name": "Sergipe" } }'
  ```
  Response:
  ```
  Location: http://localhost:8081/users/5aaf3db043ccc12860c239b5
  Status: 201 Created
  ```
  - Parte 2 Criando a Campanha daquele time
   ```
  curl -X POST http://localhost:8080/campaigns/ -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{"validityDate": "2018-10-18", "idHeartTeam": "5aaf3d3243ccc12860c239b4", "name": "Sergipão"}'
  ```
  Response:
  ```
  "location": "http://localhost:8080/campaigns/5aaf3e2443ccc12210cfbd70"
  Status: 201 Created
  ```
  - Parte 3 Enviando usuário que já tinha sido criado anteriormente
  ```
  curl -X POST http://localhost:8081/users/ -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{ "fullName": "Breno", "email": "alex23@gmail.com", "birthdate": "1990-10-18", "heartTeam": { "name": "Sergipe" } }'
  ```
  Response:
  ```
  Content-Type: application/json;charset=UTF-8
  [{"id":"5aaf3e2443ccc12210cfbd70","name":"Sergipão","idHeartTeam":"5aaf3d3243ccc12860c239b4","validityDate":"2018-10-17"}]
  Status: 200 OK
  ```
  ### GET
	 - http://localhost:8081/users/{id}
  - Para se pegar um sócio usuário especifico por id
   ```
   curl -X GET http://localhost:8081/users/5aaf3db043ccc12860c239b5 -H 'cache-control: no-cache' -H 'content-type: application/json'
   ```
   Response:
  ```
   Content-Type: application/json;charset=UTF-8
   Status: 200 OK
	{"id":"5aaf3db043ccc12860c239b5","fullName":"Breno","email":"alex23@gmail.com","birthdate":"1990-10-18","heartTeam":{"id":"5aaf3d3243ccc12860c239b4","name":"Sergipe"},"campaignList":[{"id":"5aaf3e2443ccc12210cfbd70","name":"Sergipão","idHeartTeam":"5aaf3d3243ccc12860c239b4","validityDate":"2018-10-18"}]}
  ```
  
### 3 Stream vogal
	Pode ser executado apartir do projeto: **prooffirstvowel**
	Rodar comando:
	```
	java -jar prooffirstvowel-0.0.1-SNAPSHOT.jar aAbBABacafe
	```

### 4 Oque é DeadLock? 
	Bloqueio no banco de dados devido ele estar em meio a uma transação que ainda não foi finalizada; 
	Por exemplo você pode estar tentando dar update em um campo de uma tabela que sendo utilizada pelo banco de dados, 
	ele não deixa efetivar esta transação que você está tentando fazer gerando um DEADLOCK;

### 5 Streams 
	Pode-se dizer que uma execução de forma que no final teremos uma ordem dela, 
	já no ParallelStreams ele lança variás Threads para a execução não conseguimos garantir uma ordem exata com a "parallel", 
	porém cada uma se adequa melhor a cada situação 
	tem situação que se exige mais performance é desejavel utilizar a parallelstream 
	já que ela vai usar mais o poder dos novos chips de processamento, 
	lançado threads e consequentemente ganhando mais eficacia.
 
