# Bluesoft: Desafio Desenvolvedor Java Backend

#### Antes de iniciar o projeto, leia com atenção todos os tópicos.

---

## Proposta do desafio:

A proposta deste desafio é conhecer suas habilidades de resolver problemas de negócio por meio de programação.
Estamos enviando para você um arquivo zip contendo um projeto de uma aplicação Java Web (Spring Boot, Hibernate e AngularJs) com o Frontend já desenvolvido.

Seu desafio será implementar o Backend e integrar com o Frontend.

## O problema

Você deve desenvolver um sistema para realizar **pedidos** a partir de uma lista de **produtos**, conforme a imagem:

![](https://bluesoft-sp.s3-sa-east-1.amazonaws.com/download/desafio-bluesoft/novo-pedido.png)

Os produtos **já estão cadastrados** e possuem 2 atributos: GTIN (código de barra) e nome.

Quando o usuário digitar a quantidade de cada produto e clicar no botão Enviar, o sistema deverá chamar a API https://egf1amcv33.execute-api.us-east-1.amazonaws.com/dev/produto/{gtin} informando o GTIN de cada produto. O retorno da API será uma lista de **fornecedores** e dentro de cada fornecedor uma lista de **preços** de acordo com a **quantidade mínima** para compra. Não é obrigatório informar a quantidade para todos os produtos.

Veja um exemplo do retorno da API externa para o GTIN 7894900011517:
```json
[
    {
        "nome": "Fornecedor 1",
        "cnpj": "56.918.868/0001-20",
        "precos": [
            {
                "preco": 6.89,
                "quantidade_minima": 1
            },
            {
                "preco": 5.89,
                "quantidade_minima": 10
            }
        ]
    },
    {
        "nome": "Fornecedor 2"
        "cnpj": "37.563.823/0001-35",
        "precos": [
            {
                "preco": 6.8,
                "quantidade_minima": 1
            },
            {
                "preco": 6,
                "quantidade_minima": 10
            }
        ],
    }
]
```

O sistema deve selecionar o melhor fornecedor para compra de cada produto considerando o menor preço que atenda a quantidade mínima de compra. Em seguida, deverá agrupar os produtos de um mesmo fornecedor e criar um pedido para cada um. O resultado será algo semelhante a imagem:

![](https://bluesoft-sp.s3-sa-east-1.amazonaws.com/download/desafio-bluesoft/pedido-criado-sucesso.png)

Caso nenhum fornecedor atenda a quantidade mínima de qualquer um dos produtos, o sistema deverá retornar uma mensagem informando o usuário e não deve criar nenhum pedido:

![](https://bluesoft-sp.s3-sa-east-1.amazonaws.com/download/desafio-bluesoft/pedido-erro.png)

O pedido deve ter um fornecedor e uma lista dos itens comprados. Caso o fornecedor retornado pela API ainda não esteja cadastrado, o sistema deverá incluí-lo para poder vincular com o pedido.

O sistema também deverá possuir uma rota para listar todos os pedidos criados até o momento.

## Integração com Frontend

Para integrar o backend com o frontend, troque as rotas no arquivo `src/main/resources/static/app/service/PedidoService.js` pelas rotas que você criou.

## Reforçando

- É essencial que o código tenha ao menos testes de unidade para a regra de negócio;
- Evite colocar regras de negócio no Controller, não vemos isto como uma boa prática;
- Procuramos seguir boas práticas de programação. Sendo assim, mesmo que seja uma aplicação simples, aplique todas as boas práticas que você usaria em um sistema real. Aplique [Clean Code](https://www.youtube.com/watch?v=Pjoi_JxXAcM) e princípios [SOLID](https://www.youtube.com/watch?v=DmrHBUeo0NE).
- Seja objetivo no código, não precisamos que você mostre tudo que sabe mas que faça bem feito o que desenvolver.
