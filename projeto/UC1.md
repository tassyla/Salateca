
### **UC1. Reservar Sala para Disciplina**

Descreve o processo de reservar salas, pelo Alocador, para ministração de matérias com aulas recorrentes.

#### **Atores**
- Alocador (Professor, Funcionário da secretaria ou Responsável pela alocação)

#### **Pré-condição** 
- Alocador logado no sistema 

#### **Fluxo Básico**
1. Alocador informa código da disciplina e código da turma.
2. Sistema apresenta informações da turma:
    - Nome da disicplina;
    - Data de início e de fim;
    - Horários de aula (dia da semana e hora);
    - Número de alunos inscritos;
3. Sistema filtra salas do prédio do Alocador com capacidade maior ou igual ao número de alunos inscritos. 
4. Sistema apresenta código, capacidade e existência de conflito das salas filtradas.
5. Para cada horário de aula, Alocador escolhe uma sala.
6. Sistema salva alocação de salas.

#### **Fluxos Alternativos** 
- FA1: Turma não cadastrada no sistema (Passo 1):
    1. Sistema informa que a turma especificada não está cadastrada no sistema. 
    2. Sistema retorna para Passo 1.
   
- FA2: Disciplina não cadastrada no sistema (Passo 1):
    1. Sistema informa que a disciplina especificada não está cadastrada no sistema. 
    2. Sistema retorna para Passo 1.

- FA3: Nenhuma sala atende às condições especificadas (Passo 3)
    1. Sistema informa que não há salas disponíveis com as especificações. 
    2. Alocador edita informações do passo 1.
    3. Sistema retorna para Passo 3.
#### **Pós-condição**
- É criada uma alocação de sala para cada horário de aula da turma selecionada
