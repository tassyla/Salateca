### **UC2. Reservar Sala para Atividade**

Descreve o processo de reservar salas para o uso em atividades pontuais.

#### **Atores**
- Alocador

#### **Pré-condição**
- Não há

#### **Fluxo Básico**
1. Alocador informa:
   - Nome da atividade;
   - Data e horário de início e fim da alocação;
   - Número de participantes;
   - Necessidade de equipamentos (quantidade de computadores necessários e sistema operacional);
   - Acessibilidade.
2. Sistema filtra salas com capacidade maior ou igual ao número de participantes que atendem às necessidades de equipamento e acessibilidade, apresentando:
   - Código da sala;
   - Capacidade;
   - Informações de equipamento (quantidade de computadores, sistema operacional e técnico responsável);
   - Acessibilidade;
   - Existência de conflito.
3. Alocador escolhe uma sala.
4. Sistema salva a alocação da sala.

#### **Fluxos Alternativos**
- **FA1: Nenhuma sala atende às condições especificadas (Passo 1):**
  1. Sistema informa que não há salas disponíveis que atendam às especificações.
  2. Sistema retorna para Passo 1.

#### **Pós-condição**
- É criada uma alocação de sala para o horário da atividade especificada.
