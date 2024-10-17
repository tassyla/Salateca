### **UC2. Reservar Sala para Atividade**

Descreve o processo de reservar salas, pelo Alocador, para o uso em atividades pontuais.

#### **Atores**
- Alocador (Professor, Secretaria, Responsável pela alocação)

#### **Pré-condição** 
- Alocador logado no sistema 

#### **Fluxo Básico**
1. Alocador informa nome da atividade, data e horário de início e fim da alocação e número de participantes; 
2. Sistema filtra salas do prédio do Alocador com capacidade maior ou igual ao número de participantes especificado.
3. Sistema apresenta nome, capacidade e existência de conflito das salas filtradas.
4. Alocador escolhe uma sala.
5. Sistema salva alocação de salas.

#### **Fluxos Alternativos** 
- FA1: Nenhuma sala atende às condições especificadas (Passo 2)
    1. Sistema informa que não há salas disponíveis com as especificações. 
    2. Alocador edita informações do passo 1.
    3. Sistema retorna para Passo 2.
    
#### **Pós-condição**
- Horários de alocação da sala atualizados. 