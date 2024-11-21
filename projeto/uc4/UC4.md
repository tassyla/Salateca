## **UC4. Gerenciar Sala**
Descreve a listagem, cadastro, atualização e remoção de salas.
#### **Atores**
Alocador
#### **Pré-condição**

#### **Fluxo Básico**
1. Sistema informa lista de salas informando código da sala e código;
2. O Alocador seleciona uma sala;
3. O Sistema apresenta as seguintes informações da sala selecionada: 
    - Capacidade da sala;
    - Informações de equipamento (número de computadores, sistema operacional e técnico responsável);
    - Acessibilidade;


#### **Fluxos Alternativos**
- FA1: Criar Sala 
    1. O Alocador solicita a criação de uma sala (Passo 2) e informa:
        - Código da sala;
        - Capacidade de alunos;
        - Equipamentos (número de computadores, sistema operacional, técnico responsável);
        - Acessibilidade;
    2. O Sistema cria sala;
- FA2: Editar Sala 
    1. O Alocador solicita a edição de uma sala (Passo 2); 
    2. Sistema exibe campos de edição das informações da Sala (capacidade de alunos, equipamentos e acessibilidade);
    3. O Alocador edita as informações e solicita confirmação da edição;
    4. O Sistema confirma a edição e atualiza as alocações dessa sala;
- FA3: Deletar Sala 
    1. O Alocador solicita a remoção de uma sala (Passo 2); 
    2. O Sistema remove a sala e todas as suas alocações do software; 

#### **Pós-condição**
