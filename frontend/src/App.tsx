import React, {useRef} from 'react';
import './App.css';
import { ChakraProvider } from '@chakra-ui/react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Menu from './components/Menu';
import ClassroomAllocation from './components/ClassroomAllocation';
import AllocationInformation from './components/AllocationInformation/AllocationInformation';
import ClassroomList from './components/CRUDClassroom/ClassroomList';
import ClassesList from './components/CRUDClasses/ClassesList';
import { SalaDeAula, Turma } from './types';

const turmasExemplo: Turma[] = [
  {
    id: 1,
    codigo: 'TURMA001',
    disciplina: 'Matemática',
    dataInicio: '2024-01-15',
    dataFim: '2024-06-15',
    horarios: 'Segunda e Quarta, 08:00 - 10:00',
    numAlunos: 30,
    frequencia: 'Semanal',
    professor: 'João Silva',
    acessibilidade: true,
    computadores: false,
  },
  {
    id: 2,
    codigo: 'TURMA002',
    disciplina: 'História',
    dataInicio: '2024-02-10',
    dataFim: '2024-07-10',
    horarios: 'Terça e Quinta, 10:00 - 12:00',
    numAlunos: 25,
    frequencia: 'Quinzenal',
    professor: 'Maria Souza',
    acessibilidade: false,
    computadores: true,
  },
];

const salasExemplo: SalaDeAula[] = [
  {
    codigo: 'GD-06',
    capacidade: 50,
    numeroComputadores: 50,
    sistemaOperacional: 'Linux', 
    tecnico: 'Michelet'
  },
  {
    codigo: 'D1-01',
    capacidade: 200,
    numeroComputadores: 0,
    sistemaOperacional: '', 
    tecnico: ''
  },
];


function App() {

  return (
    <ChakraProvider>
      <div className="App">
      <header className="App-header">
        <Router>
          <Menu /> 
          <div>
            <Routes>
              <Route path="/home" element={ <ClassroomAllocation/> } />
              <Route path="/teste" element={ <AllocationInformation/> } />
              <Route path="/salas" element={ <ClassroomList salas={salasExemplo} />} />
              <Route path="/turmas" element={ <ClassesList turmas={turmasExemplo} />} />
            </Routes>
          </div>
        </Router>

      </header>
    </div>
    </ChakraProvider>
  )
}

export default App;
