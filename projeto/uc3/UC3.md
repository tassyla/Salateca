### **UC3. Gerenciar Turma**

Descreve a listagem, cadastro, atualização e remoção de turmas.

#### **Atores**
- Alocador

#### **Pré-condição**
- Não há

#### **Fluxo Básico**
1. Sistema informa lista de turmas, exibindo:
   - Código da turma;
   - Código e nome da disciplina.
2. Alocador seleciona uma turma.
3. Sistema apresenta as seguintes informações da turma selecionada:
   - Código da turma;
   - Código e nome da disciplina;
   - Data de início e de fim;
   - Horários de aula (dia da semana e hora);
   - Número de alunos inscritos;
   - Recorrência (semanal/quinzenal);
   - Nome do professor;
   - Necessidade de equipamento (quantidade de computadores necessários e sistema operacional);
   - Acessibilidade.

#### **Fluxos Alternativos**
- **FA1: Criar Turma**  
  1. Alocador solicita a criação de uma turma (Passo 2) e informa:
     - Código da turma;
     - Código e nome da disciplina;
     - Data de início e de fim;
     - Horários de aula (dia da semana e hora);
     - Número de alunos inscritos;
     - Recorrência (semanal/quinzenal);
     - Nome do professor;
     - Necessidade de equipamento (quantidade de computadores necessários e sistema operacional);
     - Acessibilidade.
  2. Sistema cria a turma.

- **FA2: Editar Turma**  
  1. Alocador solicita a edição de uma turma (Passo 2).  
  2. Sistema exibe campos de edição das informações da turma:
     - Data de início e fim;
     - Horários de aula;
     - Número de alunos inscritos;
     - Recorrência;
     - Nome do professor;
     - Necessidade de equipamentos.
  3. Alocador edita as informações e solicita confirmação da edição.  
  4. Sistema confirma a edição e atualiza as alocações dessa turma.

- **FA3: Deletar Turma**  
  1. Alocador solicita a remoção de uma turma (Passo 2).  
  2. Sistema remove a turma e todas as suas alocações do software.

#### **Pós-condição**
- Turma atualizada, criada ou removida no sistema.
