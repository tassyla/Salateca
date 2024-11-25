import React, {useRef} from 'react';
import './App.css';
import { ChakraProvider } from '@chakra-ui/react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Menu from './components/Menu';
import ClassroomAllocation from './components/ClassroomAllocation';
import ClassInformationBox from './components/ClassInformationBox';
import ClassSelection from './components/ClassSelection';
import ClassroomList from './components/CRUDClassrooms/components/ClassroomList';
import { SalaDeAula } from './types';

const salasExemplo: SalaDeAula[] = [
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
              <Route path="/home" element={ <ClassroomAllocation/> } />
              <Route path="/salas" element={ <ClassroomList salas={salasExemplo} />} />
            </Routes>
          </div>
        </Router>

        
        <ClassInformationBox />
        <ClassSelection />

      </header>
    </div>
    </ChakraProvider>
  )
}

export default App;
