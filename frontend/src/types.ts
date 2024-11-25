// src/types.ts
export type SalaDeAula = {
    id: number;
    codigo: string;
    disciplina: string;
    dataInicio: string;
    dataFim: string;
    horarios: string;
    numAlunos: number;
    frequencia: 'Semanal' | 'Quinzenal';
    professor: string;
    acessibilidade: boolean;
    computadores: boolean;
  };
  