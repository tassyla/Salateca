# **USPolis**
Projeto didático baseado na plataforma USPolis, executado para a disciplina PCS3643 - Laboratório de Engenharia de Software I. 

Membros do grupo:
- Bárbara Bueno - NUSP 13679530
- Juliana Mitie - NUSP 13679544
- Tássyla Lima - NUSP 13684471

## **Casos de Uso**

<!-- --------------------------------------------------------------------------------------- -->

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
4. Sistema apresenta nome, capacidade e existência de conflito das salas filtradas.
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
- Horários de alocação da sala atualizados. 

<!-- --------------------------------------------------------------------------------------- -->

### **UC2. Reservar Sala para Atividade**

Descreve o processo de reservar salas, pelo Alocador, para o uso em atividades pontuais.

#### **Atores**
- Alocador (Professor, Secretaria, Responsável pela alocação)

#### **Pré-condição** 
- Alocador logado no sistema 

#### **Fluxo Básico**
1. Alocador informa nome da atividade, data e horário da alocação e capacidade da sala; 
2. Sistema filtra salas do prédio do Alocador com capacidade maior ou igual ao número de alunos especificado. 
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

<!-- --------------------------------------------------------------------------------------- -->

### **UC3. Gerencia Usuário**

Descreve a listagem, cadastro, atualização e remoção de usuários.

#### **Atores**
- Usuário do USPolis.

#### **Pré-condição** 
- Não há. 

#### **Fluxo Básico**




#### **Fluxos Alternativos** 
- FA1: Usuário solicita deleção do  perfil (Delete)
    1. Usuário seleciona opção de "Excluir Usuário";
    2. Sistema exibe mensagem de confirmação de exclusão de usuário; 
    3. Usuário confirma exclusão;
    4. Sistema remove registro do usuário;
    5. Sistema exibe mensagem de confirmação de exclusão;

- FA2: Usuário solicita edição da conta (Update)
    1. Usuário seleciona opção de "Editar Informações";
    2. Sistema apresenta as informações do Usuário; 
    3. Usuário seleciona informação a ser editada;
    4. Usuário modifica informação selecionada; 
    5. Sistema valida as informações; 
    6. Usuário confirma a atualização; 
    7. Sistema atualiza o registro; 
    8. Sistema exibe mensagem de confirmação da edição; 

- FA3: Usuário deseja criar nova conta (Create)
    1. Usuário seleciona opção de "Cadastrar Usuário";
    2. Sistema apresenta formulário para preenchimento das informações da nova conta; 
    3. Usuário preenche dados (nome, NUSP, e-mail, senha escolhida, departamento) e confirma criação da nova conta; 
    4. Sistema valida os dados e cria novo registro de usuário; 
    5. Sistema exibe mensagem de confirmação da criação de usuário; 




#### **Pós-condição**
- Usuário logado no sistema 

<!-- --------------------------------------------------------------------------------------- -->
### **UC4. Consulta Alocações**
Lista as informações de datas e horários de alocações existentes. Permite filtragem por sala, disciplina e prédio.

<!-- --------------------------------------------------------------------------------------- -->

### **UC5. Gerencia Sala**
Descreve a listagem, cadastro, atualização e remoção de salas.
Cada sala contém as informações:
    - identificador (nome da sala);
    - capacidade;
    - prédio;
    - datas e horários alocados;

<!-- --------------------------------------------------------------------------------------- -->
