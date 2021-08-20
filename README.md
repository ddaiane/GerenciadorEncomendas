# Gerenciador de encomendas e correspondências _(versão 0.2)_

**Desenvolvido por:** Daiane Marcon, [Mateus Vagner](https://github.com/mateusvagner) e [Caroline Scherer](https://github.com/carolineschererr)

Aplicação que implementa um sistema para controle de recebimento e entrega de correspondências e encomendas para a portaria de um condomínio.
Com o sistema é possível:
- cadastrar os moradores (na versão atual o sistema não permite o cadastro de mais de um morador com o exato mesmo nome),
- pesquisar e deletar cadastros de moradores,
- registrar recebimento de correspondências e encomendas pelo condomínio com nome do morador destinatário, nome do funcionário que recebeu a encomenda e empresa remetente da encomenda,
- registrar as retiradas de encomendas por moradores com nome de quem retirou e nome do funcionário que fez a entrega,
- registrar a data do recebimento no condomínio e a data de entrega ao destinatário (caso tenha sido retirada),
- pesquisar todas correspondências e encomendas a serem entregues para um determinado morador,
- listar todas movimentações de entrada e saída de um determinado dia e todas movimentações existentes no sistema.

Na atual versão não está implementado um banco de dados, tendo persistencia de dados apenas em memória durante a execução da aplicação.

_Aplicação Java desenvolvida como projeto final na disciplina de Linguagem de Programação II no curso superior de Tecnologia em Sistemas para Internet do IFRS-Poa_




