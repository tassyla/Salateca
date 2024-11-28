import internal from "stream";

// src/types.ts
export type SalaDeAula = {
    codigo: string; 
    capacidade: number; 
    numeroComputadores: number; 
    sistemaOperacional: string; 
    tecnico: string; 
  };

  export type Turma = {
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
  