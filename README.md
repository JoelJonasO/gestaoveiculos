
# Projeto Gestão de Veículos

CRUD com a ideia de uma loja de Veículos.

## Stack utilizada

**Back-end:** Java 17, Sprint Boot, JPA e Hibernate, Lombok, Flyway e MySql


## Endpoints

- GET /veiculos -> Traz uma lista de veículos da base de dados
- GET /veiculos/filtrar -> Retorna lista de veículos filtrados por marca, ano e cor configurado na rota /veiculos/filtrar?marca={marca}&ano={ano}&cor={cor}
- GET /veiculos/{id} -> Retorna um veículo especifico usando o id
- POST /veiculos -> Salva o veículo passado no corpo da requisição
- PUT /veiculos/{id} -> Atualiza um veículo especifico passado no corpo da requisição
- PATCH /update-veiculo/marca -> Atualiza a marca apenas do veículo, recebe o id do veículo e marca no corpo da requisição
- DELETE /veiculos/{id} -> Exclui um veículo especifico 
