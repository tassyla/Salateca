### **UC1. Reservar Sala para Disciplina**

Descreve o processo de reservar salas para ministração de matérias com aulas recorrentes.

#### **Atores**
- Alocador

#### **Pré-condição**
- Não há

#### **Fluxo Básico**
1. Alocador informa o código da disciplina.
2. Sistema retorna turmas associadas a essa disciplina.
3. Alocador seleciona uma turma para alocação.
4. Sistema apresenta as seguintes informações da turma selecionada:
   - Código da turma;
   - Código e nome da disciplina;
   - Data de início e de fim;
   - Horários de aula (dia da semana e hora);
   - Número de alunos inscritos;
   - Recorrência (semanal/quinzenal);
   - Nome do professor;
   - Necessidade de equipamento (quantidade de computadores necessários e sistema operacional);
   - Acessibilidade.
5. Sistema filtra salas com capacidade maior ou igual ao número de alunos inscritos que atendem às necessidades de equipamento e acessibilidade da turma, apresentando:
   - Código da sala;
   - Capacidade;
   - Informações de equipamento (quantidade de computadores, sistema operacional e técnico responsável);
   - Acessibilidade;
   - Existência de conflito.
6. Para cada horário de aula, o Alocador escolhe uma sala.
7. Sistema salva as alocações de salas.

#### **Fluxos Alternativos**
- **FA1: Disciplina não cadastrada no sistema (Passo 1):**
  1. Sistema informa que a disciplina especificada não está cadastrada no sistema.
  2. Sistema retorna para Passo 1.

- **FA2: Nenhuma sala atende às condições especificadas (Passo 5):**
  1. Sistema informa que não há salas disponíveis que atendam às especificações.
  2. Sistema retorna para Passo 1.

#### **Pós-condição**
- É criada uma alocação de sala para cada horário de aula da turma selecionada.
